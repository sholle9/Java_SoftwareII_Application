package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
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
import model.*;

public class updateAppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private static appointments selectedAppointment;

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
    private ComboBox<LocalDate> startDateCb;

    @FXML
    private ComboBox<LocalTime> startTimeCb;

    @FXML
    private ComboBox<LocalDate> endDateCb;

    @FXML
    private ComboBox<LocalTime> endTimeCb;

    @FXML
    private ComboBox<customers> customerIdCb;

    @FXML
    private ComboBox<users> userIdCb;

    Stage stage;
    Parent scene;

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

    //This observable list populates times from 8am to 10pm EST in military time in 15 minute increments
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

    //This observable list populates dates from 2020-1-1 till end of 2023
    ObservableList<LocalDate> startDateList(){
        ObservableList<LocalDate>startDateList = FXCollections.observableArrayList();
        LocalDate start = LocalDate.of(2020,1,1);
        LocalDate end = LocalDate.of(2023,12,31);

        while (start.isBefore(end.plusDays(1))){
            start = start.plusDays(1);
            startDateList.add(start);
        }
        return startDateList;
    }

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

    @FXML
    void onActionCancelToCalendar(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws IOException {
        int selectedAppointmentID = selectedAppointment.getAppointmentID();
        String selectedAppointmentType = selectedAppointment.getType();
        String confirmMessage = String.format("This will permanently delete the appointment with ID: %d and Type: %s. If you wish to delete this appointment, press OK.", selectedAppointmentID, selectedAppointmentType);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(confirmMessage);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Connection conn = JDBC.getConnection();//Connect to database
                String deleteAppointmentStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";//? are place holders indexed at 1

                DBQuery.setPreparedStatement(conn, deleteAppointmentStatement);//Create PreparedStatement
                PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement


                int appointmentID = selectedAppointment.getAppointmentID();

                //key-value mapping for the 1 ?
                ps.setInt(1, appointmentID);

                ps.execute();//Execute PreparedStatement

                //Check row(s) affected
                if (ps.getUpdateCount() > 0) {
                    System.out.println(ps.getUpdateCount() + " row(s) affected for Appointments!");
                } else {
                    System.out.println("No change!");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage()); //getMessage will print out the exception found
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    @FXML
    void onActionSaveUpdatedAppointment(ActionEvent event) throws IOException {

        try{
            Connection conn = JDBC.getConnection();//Connect to databaseString
            String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";//? are place holders indexed at 1


            DBQuery.setPreparedStatement(conn, updateStatement);//Create PreparedStatement
            PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

            int appointmentID;
            String title;
            String description;
            String location;
            String type;
            LocalDateTime start;
            LocalDateTime end;
            LocalDateTime lastUpdate;
            String lastUpdatedBy;
            int customerID;
            int userID;
            int contactID;

            //Get user input for updateAppointment text and combo boxes
            appointmentID = selectedAppointment.getAppointmentID();
            title = titleTxt.getText();
            description = descriptionTxtA.getText();
            location = locationTxt.getText();
            type = typeTxt.getText();
            start = LocalDateTime.of(startDateCb.getSelectionModel().getSelectedItem(), startTimeCb.getSelectionModel().getSelectedItem()) ;
            end = LocalDateTime.of(endDateCb.getSelectionModel().getSelectedItem(), endTimeCb.getSelectionModel().getSelectedItem());
            lastUpdate = LocalDateTime.now();
            lastUpdatedBy = userIdCb.getValue().getUserName();
            customerID = customerIdCb.getValue().getCustomerID();
            userID = userIdCb.getValue().getUserID();
            contactID = contactCb.getValue().getContactID();

            //key-value mapping for the 12 ?'s
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(lastUpdate));
            ps.setString(8,lastUpdatedBy);
            ps.setInt(9,customerID);
            ps.setInt(10,userID);
            ps.setInt(11,contactID);
            ps.setInt(12, appointmentID);

            for (appointments app : appointmentList()) {

                //when the start time of an appointment falls within an already existing appointment time
                if ((start.isAfter(app.getStartDateTime()) || start.isEqual(app.getStartDateTime())) && start.isBefore(app.getEndDateTime()) && app.getAppointmentID() != appointmentID) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The time and/or date selected for the start of this appointment overlaps with another appointment. Please select another time or date.");
                    alert.showAndWait();

                    return;
                }
                //when the end time of an appointment falls with in an already existing appointment time
                else if (end.isAfter(app.getStartDateTime()) && (end.isBefore(app.getEndDateTime()) || end.isEqual(app.getEndDateTime())) && app.getAppointmentID() != appointmentID) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The time and/or date selected for the end of this appointment overlaps with another appointment. Please select another time or date.");
                    alert.showAndWait();

                    return;
                }
                //when the appointment overlaps another appointment entirely
                else if ((start.isBefore(app.getStartDateTime()) || start.isEqual(app.getStartDateTime())) && (end.isAfter(app.getEndDateTime()) || end.isEqual(app.getEndDateTime())) && app.getAppointmentID() != appointmentID) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("The time and/or date selected for the start and end of this appointment overlaps with another appointment. Please select another time or date.");
                    alert.showAndWait();

                    return;
                }


            }

            ps.execute();//Execute PreparedStatement

            //Check row(s) affected
            if (ps.getUpdateCount() > 0){
                System.out.println(ps.getUpdateCount() + " row(s) affected!");
            }
            else {
                System.out.println("No change!");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    //this will initialize appointment data selected from appointmentCalendarController
    public void appointmentDataTransfer(appointments appointmentData){
        selectedAppointment = appointmentData;

        //creates local variables for the combo boxes for loop for the selectedAppointment
        int contactID = selectedAppointment.getContactID();
        int userID = selectedAppointment.getUserID();
        int customerID = selectedAppointment.getCustomerID();
        LocalDate startDate = selectedAppointment.getStateDate();
        LocalDate endDate = selectedAppointment.getEndDate();
        LocalTime startTime = selectedAppointment.getStartTime();
        LocalTime endTime = selectedAppointment.getEndTime();

        //Sets the text boxes with the selectedAppointment
        appointmentIdTxt.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        titleTxt.setText(selectedAppointment.getTitle());
        descriptionTxtA.setText(selectedAppointment.getDescription());
        locationTxt.setText(selectedAppointment.getLocation());
        typeTxt.setText(selectedAppointment.getType());

        //This for loop goes through the observable list created for the contactCb in the initializable
        for(contacts contact : contactCb.getItems()){
            if(contactID == contact.getContactID()){
                contactCb.setValue(contact);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }

        }

        //This for loop goes through the observable list created for the userIdCb in the initializable
        for(users user : userIdCb.getItems()){
            if(userID == user.getUserID()){
                userIdCb.setValue(user);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }

        }

        //This for loop goes through the observable list created for the customerIdCb in the initializable
        for(customers customer : customerIdCb.getItems()){
            if(customerID == customer.getCustomerID()){
                customerIdCb.setValue(customer);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }

        }

        //This for loop goes through the observable list to set the combo box with the selectedAppointment startDate
        for(int i = 0; i < startDateList().size(); i++){
            int j = startDateList().indexOf(startDate);
            if(i == j){
                startDateCb.setValue(startDate);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }
        }

        //This for loop goes through the observable list to set the combo box with the selectedAppointment endDate
        for(int i = 0; i < startDateList().size(); i++){
            int j = startDateList().indexOf(endDate);
            if(i == j){
                endDateCb.setValue(endDate);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }
        }

        //This for loop goes through the observable list to set the combo box with the selectedAppointment startTime
        for(int i = 0; i < startTimeList().size(); i++){
            int j = startTimeList().indexOf(startTime);
            if(i == j){
                startTimeCb.setValue(startTime);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }
        }

        //This for loop goes through the observable list to set the combo box with the selectedAppointment endTime
        for(int i = 0; i < startTimeList().size(); i++){
            int j = startTimeList().indexOf(endTime);
            if(i == j){
                endTimeCb.setValue(endTime);//sets the value of the combo box to the division id that matches the selectedCustomer
                break;
            }
        }


    }

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
