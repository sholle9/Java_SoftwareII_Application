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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.countries;
import model.customers;
import model.firstLevelDivision;

public class updateCustomerController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private static customers selectedCustomer;
    private static firstLevelDivision selectedCustomerDivision;
    private static countries selectedCustomerCountry;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private ComboBox<firstLevelDivision> stateProvCb;

    @FXML
    private ComboBox<countries> countryCb;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private TextArea addressTxtA;

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
    void onActionDeleteCustomer(ActionEvent event) throws IOException {

        try {
            Connection conn = JDBC.getConnection();//Connect to database
            String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";//? are place holders indexed at 1

            DBQuery.setPreparedStatement(conn, deleteStatement);//Create PreparedStatement
            PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

            String customerID = customerIdTxt.getText();



//key-value mapping for the 1 ?
            ps.setString(1,customerID);

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

    @FXML
    void onActionSaveUpdatedCustomer(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    public ObservableList<firstLevelDivision> divisionList(){
        ObservableList<firstLevelDivision> divisionList = FXCollections.observableArrayList();
        try{
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectDivision = "SELECT * FROM first_level_divisions";//Select statement

            DBQuery.setPreparedStatement(conn, selectDivision);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            firstLevelDivision division;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                division = new firstLevelDivision(rs.getInt("Division_ID"),//collects info from database based on column names from database
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                divisionList.add(division);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }

        return divisionList;
    }

    //Observable list method for getting the data from the database for the GUI country combo box
    public ObservableList<countries> countryList(){
        ObservableList <countries> countryList = FXCollections.observableArrayList();
        try {
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectCustomers = "SELECT * FROM countries";//Select statement

            DBQuery.setPreparedStatement(conn, selectCustomers);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            countries country;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                country = new countries(rs.getInt("Country_ID"),//collects info from database based on column names from database
                        rs.getString("Country"));
                countryList.add(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return countryList;

    }

    //this will initialize customer data selected from appointmentCalendarController
    public void customerDataTransfer(customers customerData){
        selectedCustomer = customerData;

        customerIdTxt.setText(String.valueOf(selectedCustomer.getCustomerID()));
        firstNameTxt.setText(selectedCustomer.getFirstName(customerData.getCustomerName()));
        lastNameTxt.setText(selectedCustomer.getLastName(customerData.getCustomerName()));
        addressTxtA.setText(selectedCustomer.getAddress());
        postalCodeTxt.setText(selectedCustomer.getPostalCode());
        phoneNumberTxt.setText(selectedCustomer.getPhoneNumber());
        //stateProvCb.setSelectionModel(selectedCustomer.getDivisionID());

    }



    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<firstLevelDivision> allDivisions = divisionList();//call the divisionList method which will pull from the database
        ObservableList<countries>allCountries = countryList();//call the countryList method which will pull from the database

        stateProvCb.setItems(allDivisions);//this sets the combo box list but see model firstLevelDivisions for the override of the toString method
        stateProvCb.setVisibleRowCount(5);//visible list will display 5 items at a time


        countryCb.setItems(allCountries);//this sets the combo box list but see model countries for the override of the toString method


    }
}
