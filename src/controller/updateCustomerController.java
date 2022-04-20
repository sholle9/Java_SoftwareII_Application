package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
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
import javafx.scene.control.*;
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

    //Observable list method for getting the data from the database for the GUI stateProv combo box
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

    //This creates a list of only the states that have countryID 1 for the US
    public ObservableList<firstLevelDivision> statesList(){
        ObservableList<firstLevelDivision> statesList = FXCollections.observableArrayList();
        try{
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectDivision = "SELECT * FROM first_level_divisions WHERE Country_ID = 1";//Select statement

            DBQuery.setPreparedStatement(conn, selectDivision);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            firstLevelDivision states;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                states = new firstLevelDivision(rs.getInt("Division_ID"),//collects info from database based on column names from database
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                statesList.add(states);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }

        return statesList;
    }

    //This creates a list of only the providences that have countryID 2 for the UK
    public ObservableList<firstLevelDivision> ukList(){
        ObservableList<firstLevelDivision> ukList = FXCollections.observableArrayList();
        try{
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectDivision = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";//Select statement

            DBQuery.setPreparedStatement(conn, selectDivision);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            firstLevelDivision ukProv;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                ukProv = new firstLevelDivision(rs.getInt("Division_ID"),//collects info from database based on column names from database
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                ukList.add(ukProv);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }

        return ukList;
    }

    //This creates a list of only the providences that have countryID 3 for the Canada
    public ObservableList<firstLevelDivision> canadaList(){
        ObservableList<firstLevelDivision> canadaList = FXCollections.observableArrayList();
        try{
            Connection conn = JDBC.getConnection();//Gets connection to database
            String selectDivision = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";//Select statement

            DBQuery.setPreparedStatement(conn, selectDivision);//sets prepared statement to be the select statement
            PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

            ps.execute();//executes the prepared statement

            ResultSet rs = ps.getResultSet();//this is the result set of the prepared statement

            firstLevelDivision canadaProv;
            while(rs.next()){//rs.next() goes to the next item or line of the result set rs
                canadaProv = new firstLevelDivision(rs.getInt("Division_ID"),//collects info from database based on column names from database
                        rs.getString("Division"),
                        rs.getInt("Country_ID")
                );
                canadaList.add(canadaProv);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }

        return canadaList;
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
        int divisionID = selectedCustomer.getDivisionID();//creates local variable for the division id of the selectedCustomer

        customerIdTxt.setText(String.valueOf(selectedCustomer.getCustomerID()));
        firstNameTxt.setText(selectedCustomer.getFirstName(customerData.getCustomerName()));
        lastNameTxt.setText(selectedCustomer.getLastName(customerData.getCustomerName()));
        addressTxtA.setText(selectedCustomer.getAddress());
        postalCodeTxt.setText(selectedCustomer.getPostalCode());
        phoneNumberTxt.setText(selectedCustomer.getPhoneNumber());

        //This for loop goes through the observable list created for the stateProvCB in the initializable
        for(firstLevelDivision division : stateProvCb.getItems()){
            if(divisionID == division.getDivisionID()){
                stateProvCb.setValue(division);//sets the value of the combo box to the division id that matches the selectedCustomer
                int countryID = division.getCountryID();

                //Nested for loop to use the corresponding countryID found in the division list
                for(countries country : countryCb.getItems()){
                    if(countryID == country.getCountryID()){
                        countryCb.setValue(country); //sets the combo box to the country value that corresponds to the countryID found in the division list
                        break;
                    }
                }
                break;
            }
        }

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete the customer and all appointments with this customer. If you wish to delete this customer, press OK.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            try {
                Connection conn = JDBC.getConnection();//Connect to database
                String deleteAppointmentStatement = "DELETE FROM appointments WHERE Customer_ID = ?";//? are place holders indexed at 1
                String deleteCustomerStatement = "DELETE FROM customers WHERE Customer_ID = ?";//? are place holders indexed at 1

                DBQuery.setPreparedStatement(conn, deleteAppointmentStatement);//Create PreparedStatement
                PreparedStatement ps2 = DBQuery.getPreparedStatement();//Retrieving PreparedStatement
                DBQuery.setPreparedStatement(conn, deleteCustomerStatement);//Create PreparedStatement
                PreparedStatement ps1 = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

                String customerID = customerIdTxt.getText();

                //key-value mapping for the 1 ?
                ps2.setString(1, customerID);
                ps1.setString(1, customerID);

                ps2.execute();
                ps1.execute();//Execute PreparedStatement

                //Check row(s) affected
                if (ps2.getUpdateCount() > 0) {
                    System.out.println(ps2.getUpdateCount() + " row(s) affected for Appointments!");
                } else {
                    System.out.println("No change!");
                }
                if (ps1.getUpdateCount() > 0) {
                    System.out.println(ps1.getUpdateCount() + " row(s) affected for Customers!");
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
    void onActionSaveUpdatedCustomer(ActionEvent event) throws IOException {

        //Shows user an error dialog box if there is a field not filled out
        if(firstNameTxt.getText().isBlank() || lastNameTxt.getText().isBlank() || addressTxtA.getText().isBlank() || stateProvCb.getSelectionModel().isEmpty()  || countryCb.getSelectionModel().isEmpty() || postalCodeTxt.getText().isBlank() || phoneNumberTxt.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please fill-in each field.");
            alert.showAndWait();

            return;
        }
        else {
            try {
                Connection conn = JDBC.getConnection();//Connect to database
                String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Division_ID = ? WHERE Customer_ID = ?";//? are place holders indexed at 1

                DBQuery.setPreparedStatement(conn, updateStatement);//Create PreparedStatement
                PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

                String customerName, newAddress, newPostalCode, newPhone, customerID;
                LocalDateTime lastUpdate;
                int newDivisionID;

                //Get user input for updateCustomer text and combo boxes
                customerName = firstNameTxt.getText() + " " + lastNameTxt.getText();
                newAddress = addressTxtA.getText();
                newPostalCode = postalCodeTxt.getText();
                newPhone = phoneNumberTxt.getText();
                lastUpdate = LocalDateTime.now();
                newDivisionID = stateProvCb.getValue().getDivisionID();
                customerID = customerIdTxt.getText();

                //key-value mapping for the 3 ?'s
                ps.setString(1, customerName);
                ps.setString(2, newAddress);
                ps.setString(3, newPostalCode);
                ps.setString(4, newPhone);
                ps.setTimestamp(5, Timestamp.valueOf(lastUpdate));
                ps.setInt(6, newDivisionID);
                ps.setString(7, customerID);

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

    @FXML
    void onActionCountry(ActionEvent event) {
        ObservableList<firstLevelDivision> allDivisions = divisionList();//call the divisionList method which will pull from the database

        ObservableList<firstLevelDivision> usStates = statesList();//call the statesList which will only pull division names with countryID 1
        ObservableList<firstLevelDivision> ukProvs = ukList();//call the ukList which will only pull division names with countryID 2
        ObservableList<firstLevelDivision> canadaProvs = canadaList();//call the canadaList which will only pull division names with countryID 3


        int x = countryCb.getValue().getCountryID();//sets x to the countryID that the user selects

        //if-else statements to filter the stateProvCb by the corresponding country
        if (x == 1) {
            stateProvCb.setItems(usStates);
        } else if (x == 2) {
            stateProvCb.setItems(ukProvs);
        } else if (x == 3) {
            stateProvCb.setItems(canadaProvs);
        } else {
            stateProvCb.setItems(allDivisions);
        }
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
