package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

import helper.DBQuery;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.appointments;
import model.contacts;
import model.customers;
import model.users;
/**This class is the controller for the addAppointments.fxml view.*/
public class addAppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextArea descriptionTxtA;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private ComboBox<contacts> contactCb;

    @FXML
    private ComboBox<LocalTime> startTimeCb;

    @FXML
    private ComboBox<LocalTime> endTimeCb;

    @FXML
    private ComboBox<customers> customerIdCb;

    @FXML
    private ComboBox<users> userIdCb;

    @FXML
    private ComboBox<LocalDate> startDateCb;

    @FXML
    private ComboBox<LocalDate> endDateCb;

    Stage stage;
    Parent scene;

    /** This is the onAction for the addAppointment Cancel Button.
     When the cancel button is clicked, the user is redirected to the appointmentCalendar without saving the inputted info.
     */
    @FXML
    void onActionCancelToCalendar(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**Observable list method for getting the data form the appointments table from the database.
     This will fill the ObservableList appointmentList with the info from the appointments table and return the ObservableList appointmentList.
     @return Returns the ObservableList appointmentList.
     */
    //Observable list method for getting the data from the database
    public ObservableList<appointments> appointmentList(){
        ObservableList <appointments> appointmentList = FXCollections.observableArrayList();
        try {
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectAppointments = "SELECT * FROM appointments";//Select statement

            DBQuery.setPreparedStatement(conn, selectAppointments);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            appointments appointment;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                appointment = new appointments(rs.getInt("Appointment_ID"),//collects info from database based on column names from database
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getInt("Contact_ID"),
                        rs.getString("Type"),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTime("Start").toLocalTime(),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getTime("End").toLocalTime(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID")
                );
                appointmentList.add(appointment);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return appointmentList;

    }

    /** This is the onAction method for the Save button.
     When the Save button is clicked, the information entered in the text fields (except the id field) are inserted into the appointments table in the database.
     An IOException occurs if no values or incorrect values are entered. The try/catch method is used to create an error message asking for correct values.
     If/else statements are used to make sure that date and time are within the business hours and at a future/ current time.
     */
    @FXML
    void onActionSaveNewAppointment(ActionEvent event) throws IOException {

        //Shows user an error dialog box if there is a field not filled out
        if(titleTxt.getText().isBlank() || descriptionTxtA.getText().isBlank() || locationTxt.getText().isBlank() || contactCb.getSelectionModel().isEmpty() || typeTxt.getText().isBlank() || startDateCb.getSelectionModel().isEmpty() || startTimeCb.getSelectionModel().isEmpty() || endDateCb.getSelectionModel().isEmpty() || endTimeCb.getSelectionModel().isEmpty() || customerIdCb.getSelectionModel().isEmpty() || userIdCb.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please fill-in each field.");
            alert.showAndWait();

            return;
        }
        else {
            try {
                Connection conn = JDBC.getConnection();//Connect to databaseString
                String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By,Last_Update,Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//? are place holders indexed at 1

                DBQuery.setPreparedStatement(conn, insertStatement);//Create PreparedStatement
                PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

                String title;
                String description;
                String location;
                String type;
                LocalDateTime start;
                LocalDateTime end;
                LocalDateTime createDate;
                String createdBy;
                LocalDateTime lastUpdate;
                String lastUpdatedBy;
                int customerID;
                int userID;
                int contactID;


                //Get user input for addCustomer text and combo boxes
                title = titleTxt.getText();
                description = descriptionTxtA.getText();
                location = locationTxt.getText();
                type = typeTxt.getText();
                start = LocalDateTime.of(startDateCb.getSelectionModel().getSelectedItem(), startTimeCb.getSelectionModel().getSelectedItem());
                end = LocalDateTime.of(endDateCb.getSelectionModel().getSelectedItem(), endTimeCb.getSelectionModel().getSelectedItem());
                createDate = LocalDateTime.now();
                createdBy = userIdCb.getValue().getUserName();
                lastUpdate = LocalDateTime.now();
                lastUpdatedBy = userIdCb.getValue().getUserName();
                customerID = customerIdCb.getValue().getCustomerID();
                userID = userIdCb.getValue().getUserID();
                contactID = contactCb.getValue().getContactID();

//            TimeZone utcZoneId = TimeZone.getTimeZone("UTC");
//            ZonedDateTime startTimeZ = ZonedDateTime.of(LocalDate.now(), start.toLocalTime(),ZoneId.systemDefault());
//            ZonedDateTime computerStart = startTimeZ.withZoneSameInstant(ZoneId.of(utcZoneId));
//            ZonedDateTime officeEnd = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22,0),officeZoneId.toZoneId());
//            ZonedDateTime computerEnd = officeEnd.withZoneSameInstant(ZoneId.from(LocalDateTime.now().atZone(ZoneId.systemDefault())));

                //key-value mapping for the 13 ?'s
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setString(3, location);
                ps.setString(4, type);
                ps.setTimestamp(5, Timestamp.valueOf(start));
                ps.setTimestamp(6, Timestamp.valueOf(end));
                ps.setTimestamp(7, Timestamp.valueOf(createDate));
                ps.setString(8, createdBy);
                ps.setTimestamp(9, Timestamp.valueOf(lastUpdate));
                ps.setString(10, lastUpdatedBy);
                ps.setInt(11, customerID);
                ps.setInt(12, userID);
                ps.setInt(13, contactID);

                //the for loop will initialize if the LocalDateTime start is current or after current LocalDateTime and the end LocalDateTime is the same or after the start
                if ((start.isEqual(LocalDateTime.now()) || start.isAfter(LocalDateTime.now()) && (end.isEqual(start) || end.isAfter(start)))) {
                    for (appointments app : appointmentList()) {

                        //when the start time of an appointment falls within an already existing appointment time
                        if ((start.isAfter(app.getStartDateTime()) || start.isEqual(app.getStartDateTime())) && start.isBefore(app.getEndDateTime())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setContentText("The time and/or date selected for the start of this appointment overlaps with another appointment. Please select another time or date.");
                            alert.showAndWait();

                            return;
                        }
                        //when the end time of an appointment falls with in an already existing appointment time
                        else if (end.isAfter(app.getStartDateTime()) && (end.isBefore(app.getEndDateTime()) || end.isEqual(app.getEndDateTime()))) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setContentText("The time and/or date selected for the end of this appointment overlaps with another appointment. Please select another time or date.");
                            alert.showAndWait();

                            return;
                        }
                        //when the appointment overlaps another appointment entirely
                        else if ((start.isBefore(app.getStartDateTime()) || start.isEqual(app.getStartDateTime())) && (end.isAfter(app.getEndDateTime()) || end.isEqual(app.getEndDateTime()))) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setContentText("The time and/or date selected for the start and end of this appointment overlaps with another appointment. Please select another time or date.");
                            alert.showAndWait();

                            return;
                        }


                    }


                }
                //when that date is the current date it requires the time to be the same or after the current time
                else if (startDateCb.getSelectionModel().getSelectedItem().isEqual(LocalDate.now()) && (startTimeCb.getSelectionModel().getSelectedItem().equals(LocalTime.now()) == false && startTimeCb.getSelectionModel().getSelectedItem().isAfter(LocalTime.now()) == false)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The start time must be the same or after the current time.");
                    alert.showAndWait();

                    return;
                }
                //when the end date is before or after the start date error box displays
                else if (endDateCb.getSelectionModel().getSelectedItem().isEqual(startDateCb.getSelectionModel().getSelectedItem()) == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The end date must be the same as the start date.");
                    alert.showAndWait();

                    return;
                }
                //when the end date is => the start date, but the end time < start time an error box displays
                else if ((endDateCb.getSelectionModel().getSelectedItem().isEqual(startDateCb.getSelectionModel().getSelectedItem())) && (endTimeCb.getSelectionModel().getSelectedItem().isAfter(startTimeCb.getSelectionModel().getSelectedItem()) == false && endTimeCb.getSelectionModel().getSelectedItem().equals(startTimeCb.getSelectionModel().getSelectedItem()) == false)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The end time must be the same or after the start time.");
                    alert.showAndWait();

                    return;
                }

                ps.execute();//Execute PreparedStatement

                //Check row(s) affected
                if (ps.getUpdateCount() > 0) {
                    System.out.println(ps.getUpdateCount() + " row(s) affected!");
                } else {
                    System.out.println("No change!");
                }


            } catch (Exception e) {
                System.out.println(e.getMessage()); //getMessage will print out the exception found
            }
        }

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**Observable list method for getting the data form the contacts table from the database.
     This will fill the ObservableList contactList with the info from the contacts table and return the ObservableList contactList.
     @return Returns the ObservableList contactList.
     */
    //Observable list method for getting the data from the database for the GUI contact combo box
    public ObservableList<contacts> contactList(){
        ObservableList <contacts> contactList = FXCollections.observableArrayList();
        try {
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectContacts = "SELECT * FROM contacts";//Select statement

            DBQuery.setPreparedStatement(conn, selectContacts);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            contacts contact;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                contact = new contacts(rs.getInt("Contact_ID"),//collects info from database based on column names from database
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                contactList.add(contact);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return contactList;

    }

    /**Observable list method for getting the data form the users table from the database.
     This will fill the ObservableList userList with the info from the users table and return the ObservableList userList.
     @return Returns the ObservableList userList.
     */
    //Observable list method for getting the data from the database for the GUI user combo box
    public ObservableList<users> userList(){
        ObservableList <users> userList = FXCollections.observableArrayList();
        try {
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectUsers = "SELECT * FROM users";//Select statement

            DBQuery.setPreparedStatement(conn, selectUsers);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            users user;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                user = new users(rs.getInt("User_ID"),//collects info from database based on column names from database
                        rs.getString("User_Name"),
                        rs.getString("Password"),
                        rs.getDate("Create_Date").toLocalDate(),
                        rs.getTime("Create_Date").toLocalTime(),
                        rs.getString("Created_By"),
                        rs.getDate("Last_Update").toLocalDate(),
                        rs.getTime("Last_Update").toLocalTime(),
                        rs.getString("Last_Updated_By"));
                userList.add(user);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return userList;

    }

    /**Observable list method for getting the data form the customers table from the database.
     This will fill the ObservableList customerList with the info from the customers table and return the ObservableList customerList.
     @return Returns the ObservableList customerList.
     */
    //Observable list method for getting the data from the database for the GUI contact combo box
    public ObservableList<customers> customerList(){
        ObservableList <customers> customerList = FXCollections.observableArrayList();
        try {
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectCustomers = "SELECT * FROM customers";//Select statement

            DBQuery.setPreparedStatement(conn, selectCustomers);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            customers customer;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                customer = new customers(rs.getInt("Customer_ID"),//collects info from database based on column names from database
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_code"),
                        rs.getString("Phone"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_updated_By"),
                        rs.getInt("Division_ID"));
                customerList.add(customer);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return customerList;

    }

    /**This is the startTimeList ObservableList Method.
     This observableList populates times from 8am to 10pm in EST in military time in 15 minute increments and adds them to the ObservableList startTimeList.
     @return Returns the observableList startTimeList.
     */
    //This observable list populates times from 8am to 10pm in EST in military time in 15 minute increments
    ObservableList<LocalTime> startTimeList() {

        TimeZone officeZoneId = TimeZone.getTimeZone("US/Eastern");
        ZonedDateTime officeStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0),officeZoneId.toZoneId());
        ZonedDateTime computerStart = officeStart.withZoneSameInstant(ZoneId.from(LocalDateTime.now().atZone(ZoneId.systemDefault())));
        ZonedDateTime officeEnd = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22,0),officeZoneId.toZoneId());
        ZonedDateTime computerEnd = officeEnd.withZoneSameInstant(ZoneId.from(LocalDateTime.now().atZone(ZoneId.systemDefault())));

        ObservableList <LocalTime> startTimeList = FXCollections.observableArrayList();
        while (computerStart.isBefore(computerEnd.plusSeconds(1))){
            startTimeList.add(computerStart.toLocalTime());
            computerStart = computerStart.plusMinutes(15);
        }
        return startTimeList;
    }

    /**This is the startDateList ObservableList Method.
     This observableList populates dates from today till end of 2023 and adds them to the ObservableList startDateList.
     @return Returns the observableList startDateList.
     */
    //This observable list populates dates from today till end of 2023
    ObservableList<LocalDate> startDateList(){
        ObservableList<LocalDate>startDateList = FXCollections.observableArrayList();
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.of(2023,12,31);

        while (start.isBefore(end.plusDays(1))){
            startDateList.add(start);
            start = start.plusDays(1);
        }
        return startDateList;
    }

    /** This is the addAppointmentController initialize method.
     This method is created when the application is launched.
     This method ensures that the combo boxes for adding appointment times, dates, contact, customerID, and userID are populated.
     */
    @FXML
    void initialize() {
        ObservableList<users> allUsers = userList();//call the userList method which will pull from the database
        ObservableList<contacts>allContacts = contactList();//call the contactList method which will pull from the database
        ObservableList<customers>allCustomers = customerList();//call the customerList method which will pull from the database
        ObservableList<LocalTime>startTimes = startTimeList();//calls the startTimeList method
        ObservableList<LocalDate>startDates = startDateList();//calls the startDateList method

        //Populates list of LocalTime for the startTime and endTime cb
        startTimeCb.setItems(startTimes);
        endTimeCb.setItems(startTimes);

        //Populates list of LocalDate for startDate and endDate cb
        startDateCb.setItems(startDates);
        endDateCb.setItems(startDates);

        userIdCb.setItems(allUsers);//this sets the combo box list but see model users for the override of the toString method

        customerIdCb.setItems(allCustomers);//this sets the combo box list but see model customers for the override of the toString method
        customerIdCb.setVisibleRowCount(5);//visible list will display 5 items at a time

        contactCb.setItems(allContacts);//this sets the combo box list but see model contacts for the override of the toString method

    }
}

