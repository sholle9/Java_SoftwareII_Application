package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

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
import model.contacts;
import model.customers;
import model.users;

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

    @FXML
    void onActionCancelToCalendar(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSaveNewAppointment(ActionEvent event) throws IOException {

        try{
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
            start = LocalDateTime.of(startDateCb.getSelectionModel().getSelectedItem(), startTimeCb.getSelectionModel().getSelectedItem()) ;
            end = LocalDateTime.of(endDateCb.getSelectionModel().getSelectedItem(), endTimeCb.getSelectionModel().getSelectedItem());
            createDate = LocalDateTime.now();
            createdBy = userIdCb.getValue().getUserName();
            lastUpdate = LocalDateTime.now();
            lastUpdatedBy = userIdCb.getValue().getUserName();
            customerID = customerIdCb.getValue().getCustomerID();
            userID = userIdCb.getValue().getUserID();
            contactID = contactCb.getValue().getContactID();

            //key-value mapping for the 13 ?'s
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(createDate));
            ps.setString(8,createdBy);
            ps.setTimestamp(9, Timestamp.valueOf(lastUpdate));
            ps.setString(10,lastUpdatedBy);
            ps.setInt(11,customerID);
            ps.setInt(12,userID);
            ps.setInt(13,contactID);

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

    //This observable list populates times from 8am to 10pm in military time in 15 minute increments
    ObservableList<LocalTime> startTimeList() {
        ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            start = start.plusMinutes(15);
            startTimeList.add(start);
        }
        return startTimeList;
    }

    //This observable list populates dates from today till end of 2023
    ObservableList<LocalDate> startDateList(){
        ObservableList<LocalDate>startDateList = FXCollections.observableArrayList();
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.of(2023,12,31);

        while (start.isBefore(end.plusDays(1))){
            start = start.plusDays(1);
            startDateList.add(start);
        }
        return startDateList;
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

