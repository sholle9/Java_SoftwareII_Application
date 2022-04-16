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
import model.appointments;
import model.contacts;
import model.users;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
    public ComboBox<appointments> appointmentTypeCb;

    @FXML
    public ComboBox<contacts> contactCb;

    @FXML
    public ComboBox<users> userCb;

    Stage stage;
    Parent scene;

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

    @FXML
    void onActionAppointmentTypeGoBtn (ActionEvent event){

    }

    @FXML
    void onActionContactGoBtn (ActionEvent event){

    }

    @FXML
    void onActionUserGoBtn (ActionEvent event){

    }

    @FXML
    void onActionBackBtn (ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();//cast the window as a stage
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
        stage.setScene(new Scene(scene));
        stage.show();//actually causes the stage to appear
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList <appointments> allAppointments = appointmentList();
        ObservableList<users> allUsers = userList();//call the userList method which will pull from the database
        ObservableList<contacts>allContacts = contactList();//call the contactList method which will pull from the database


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

        //appointmentTypeTableView combo box that will set the combo box list
        appointmentTypeCb.setItems(allAppointments);

        //contactTableView combo box that will set the combo box list
        contactCb.setItems(allContacts);

        //userTableView combo box that will set the combo box list
        userCb.setItems(allUsers);


    }
}
