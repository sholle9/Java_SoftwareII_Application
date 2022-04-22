package controller;

import helper.DBQuery;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lambdaExpressionInterfaces.reports;
import model.appointments;
import model.contacts;
import model.users;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.ResourceBundle;
/**This class is the controller for the reports.fxml view*/
public class reportsController implements Initializable {

    @FXML
    public Label countLbl;

    @FXML
    public TableView appointmentTypeTableView;

    @FXML
    public TableColumn appIdTypeCol;

    @FXML
    public TableColumn titleAppTypeCol;

    @FXML
    public TableColumn appTypeCol;

    @FXML
    public TableColumn descriptionAppTypeCol;

    @FXML
    public TableColumn startAppTypeCol;

    @FXML
    public TableColumn endAppTypeCol;

    @FXML
    public TableColumn customerIdAppTypeCol;

    @FXML
    public TableView contactTableView;

    @FXML
    public TableColumn contactAppIdCol;

    @FXML
    public TableColumn contactTitleCol;

    @FXML
    public TableColumn contactTypeCol;

    @FXML
    public TableColumn contactDescriptCol;

    @FXML
    public TableColumn contactStartCol;

    @FXML
    public TableColumn contactEndCol;

    @FXML
    public TableColumn contactCustIdCol;

    @FXML
    public TableView userTableView;

    @FXML
    public TableColumn userAppIdCol;

    @FXML
    public TableColumn userStartCol;

    @FXML
    public TableColumn userEndCol;

    @FXML
    public TableColumn userCustIdCol;

    @FXML
    public ComboBox<String> appointmentTypeCb;

    @FXML
    public ComboBox<contacts> contactCb;

    @FXML
    public ComboBox<users> userCb;

    @FXML
    public ComboBox monthCb;

    Stage stage;
    Parent scene;


    /**Observable list method for getting the data form the appointments table from the database.
     This will fill the ObservableList appointmentList with the info from the appointments table and return the ObservableList appointmentList.
     @return Returns the ObservableList appointmentList.
     */
    //Observable list method for getting the data from the database for the GUI appointment table
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

    /** This is the onAction for the Go button for typeAppointments table.
     When this Go button is clicked, the typeAppointment Table is filtered by the selections made in the type and month combo boxes.
     In the future, have this allow users to filter the table by either type or month and have a clear function for the combo boxes.
     <p><b>
     The Lambda expression to set countLbl text called from the interface reports.
     */
    @FXML
    void onActionAppointmentTypeGoBtn (ActionEvent event){

        //Creates an empty ObservableList
        ObservableList<appointments> typeAppointments = FXCollections.observableArrayList();

        //goes through appointmentList and adds to typeAppointments the appointments that match the type selected in the type cb
        for(appointments app : appointmentList()){
            if(appointmentTypeCb.getSelectionModel().getSelectedItem().equals(app.getType()) && monthCb.getSelectionModel().getSelectedItem().equals(app.getStateDate().getMonth())){
                typeAppointments.add(app);
            }
        }

        //sets the Table View with the list in typeAppointments
        appointmentTypeTableView.setItems(typeAppointments);

        // Lambda expression to set countLbl text
        reports reportsCount = () -> countLbl.setText("Total: " + typeAppointments.size());
        reportsCount.reportsCount();

    }

    /** This is the onAction for the Go button for contact table.
     When this Go button is clicked, the contact Table is filtered by the selections made in the contact combo boxes.
     */
    @FXML
    void onActionContactGoBtn (ActionEvent event){
        //Creates an empty list
        ObservableList<appointments>contactAppointments = FXCollections.observableArrayList();

        //goes through all the appointments in the observablelist appointmentList and adds the appointments that match the contact selected in the combo box to the contactAppointments list
        for(appointments app : appointmentList()){
            if(app.getContactID() == contactCb.getSelectionModel().getSelectedItem().getContactID()){
                contactAppointments.add(app);
            }
        }

        //displays the appointments from the contactAppointments list
        contactTableView.setItems(contactAppointments);
    }

    /** This is the onAction for the Go button for user table.
     When this Go button is clicked, the user Table is filtered by the selections made in the user combo boxes.
     */
    @FXML
    void onActionUserGoBtn (ActionEvent event){

        //Creates an empty list
        ObservableList<appointments>userAppointments = FXCollections.observableArrayList();

        //goes through all the appointments in the observablelist appointmentList and adds the appointments that match the user selected in the combo box to the userAppointments list
        for(appointments app : appointmentList()){
            if(app.getUserID() == userCb.getSelectionModel().getSelectedItem().getUserID()){
                userAppointments.add(app);
            }
        }

        //displays the appointments from the userAppointments list
        userTableView.setItems(userAppointments);
    }

    /** This is the onAction for the reports Back Button.
     When the Back button is clicked, the user is redirected to the appointmentCalendar.
     */
    @FXML
    void onActionBackBtn (ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();//cast the window as a stage
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
        stage.setScene(new Scene(scene));
        stage.show();//actually causes the stage to appear
    }

    /**This is the monthList ObservableList Method.
     Creates a method that returns a list of months with the month data type.
     @return Returns the observableList monthList.
     */
    //Creates a method that returns a list of months with the month data type
    ObservableList<Month> monthList(){
        ObservableList<Month>monthList = FXCollections.observableArrayList();
        Month start = Month.JANUARY;
        Month end = Month.DECEMBER ;

        int i = 0;
        while (i < end.getValue()){
            monthList.add(start);
            start = start.plus(1);
            i++;
        }
        return monthList;
    }

    /** This is the reportsController initialize method.
     This method is created when the application is launched.
     This method ensures that the combo boxes for filtering by type and month, contact, and users are populated.
     <p><b>
     The Lambda expression to set countLbl text called from the interface reports.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        ObservableList <appointments> allAppointments = appointmentList();
        ObservableList<users> allUsers = userList();//call the userList method which will pull from the database
        ObservableList<contacts>allContacts = contactList();//call the contactList method which will pull from the database
        ObservableList <Month> monthsList = monthList();//calls the monthList method

        appointmentTypeTableView.setItems(allAppointments);
        contactTableView.setItems(allAppointments);
        userTableView.setItems(allAppointments);

        //appointmentTypeTableView
        //this sets the column information by using the appointments model getters that will return the info from the corresponding items in the observable list allAppointments
        appIdTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //contactTableView
        //this sets the column information by using the appointments model getters that will return the info from the corresponding items in the observable list allAppointments
        contactAppIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        contactEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        contactCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //userTableView
        //this sets the column information by using the appointments model getters that will return the info from the corresponding items in the observable list allAppointments
        userAppIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        userStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        userEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        userCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //creates empty observableList
        ObservableList<String> allTypeAppointments = FXCollections.observableArrayList();

        //goes through allAppointments list and only adds types to allTypeAppointments if it does not already contain the String
        for (appointments app : allAppointments){
            if(!allTypeAppointments.contains(app.getType())){
                allTypeAppointments.add(app.getType());
            }
        }

        //appointmentTypeTableView combo box that will set the combo box list
        appointmentTypeCb.setItems(allTypeAppointments);

        //sets the months combo box with the months list
        monthCb.setItems(monthsList);

        //contactTableView combo box that will set the combo box list
        contactCb.setItems(allContacts);

        //userTableView combo box that will set the combo box list
        userCb.setItems(allUsers);

        // Lambda expression to set countLbl text
        reports reportsCount = () -> countLbl.setText("Total: " + allAppointments.size());
        reportsCount.reportsCount();

    }
}
