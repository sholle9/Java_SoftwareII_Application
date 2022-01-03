package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private ComboBox<?> startTimeCb;

    @FXML
    private ComboBox<?> endTimeCb;

    @FXML
    private ComboBox<customers> customerIdCb;

    @FXML
    private ComboBox<users> userIdCb;

    @FXML
    private ComboBox<?> startDateCb;

    @FXML
    private ComboBox<?> endDateCb;

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
            String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";//? are place holders indexed at 1

            DBQuery.setPreparedStatement(conn, insertStatement);//Create PreparedStatement
            PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

            String title;
            String description;
            String location;
            String type;
            String start;
            String end;
            int customerID;
            int userID;
            int contactID;

            //Get user input for addCustomer text and combo boxes
            title = titleTxt.getText();
            description = descriptionTxtA.getText();
            location = locationTxt.getText();
            type = typeTxt.getText();
            start = "2022-01-03 00:00:00" ;
            end = "2022-01-03 00:00:00";
            customerID = customerIdCb.getValue().getCustomerID();
            userID = userIdCb.getValue().getUserID();
            contactID = contactCb.getValue().getContactID();

            //key-value mapping for the 9 ?'s
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setString(5,start);
            ps.setString(6,end);
            ps.setInt(7,customerID);
            ps.setInt(8,userID);
            ps.setInt(9,contactID);

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
                        rs.getString("User_Name"));
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
                        rs.getString("Create_Date"),
                        rs.getString("Created_By"),
                        rs.getString("Last_Update"),
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

    @FXML
    void initialize() {
        ObservableList<users> allUsers = userList();//call the userList method which will pull from the database
        ObservableList<contacts>allContacts = contactList();//call the contactList method which will pull from the database
        ObservableList<customers>allCustomers = customerList();//call the customerList method which will pull from the database

        userIdCb.setItems(allUsers);//this sets the combo box list but see model users for the override of the toString method

        customerIdCb.setItems(allCustomers);//this sets the combo box list but see model customers for the override of the toString method
        customerIdCb.setVisibleRowCount(5);//visible list will display 5 items at a time

        contactCb.setItems(allContacts);//this sets the combo box list but see model contacts for the override of the toString method

    }
}

