package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
import model.appointments;
import model.countries;
import model.customers;
import model.firstLevelDivision;
/**This class is the controller for the addCustomer.fxml view*/
public class addCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    /** This is the onAction for the addCustomer Cancel Button.
     When the cancel button is clicked, the user is redirected to the appointmentCalendar without saving the inputted info.
     */
    @FXML
    void onActionCancelToCalendar(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This is the onAction method for the Save button.
     When the Save button is clicked, the information entered in the text fields (except the id field) are inserted into the customers table in the database.
     An IOException occurs if no values or incorrect values are entered. The try/catch method is used to create an error message asking for correct values.
     If/else statements are used to make sure that fields are filled out.
     */
    @FXML
    void onActionSaveNewCustomer(ActionEvent event) throws IOException {

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
                Connection conn = JDBC.getConnection();//Connect to databaseString
                String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";//? are place holders indexed at 1

                DBQuery.setPreparedStatement(conn, insertStatement);//Create PreparedStatement
                PreparedStatement ps = DBQuery.getPreparedStatement();//Retrieving PreparedStatement

                String customerName;
                String address;
                String postalCode;
                String phoneNumber;
                LocalDateTime createDate;
                String createdBy;
                LocalDateTime lastUpdated;
                String lastUpdatedBy;
                int divisionID;

                //Get user input for addCustomer text and combo boxes
                customerName = firstNameTxt.getText() + " " + lastNameTxt.getText();
                address = addressTxtA.getText();
                postalCode = postalCodeTxt.getText();
                phoneNumber = phoneNumberTxt.getText();
                createDate = LocalDateTime.now();
                createdBy = "script";
                lastUpdated = LocalDateTime.now();
                lastUpdatedBy = "script";
                divisionID = stateProvCb.getValue().getDivisionID();

                //key-value mapping for the 5 ?'s
                ps.setString(1, customerName);
                ps.setString(2, address);
                ps.setString(3, postalCode);
                ps.setString(4, phoneNumber);
                ps.setTimestamp(5, Timestamp.valueOf(createDate));
                ps.setString(6, createdBy);
                ps.setTimestamp(7, Timestamp.valueOf(lastUpdated));
                ps.setString(8, lastUpdatedBy);
                ps.setInt(9, divisionID);

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

    /**Observable list method for getting the data form the first_level_divisions table from the database.
     This will fill the ObservableList divisionList with the info from the first_level_divisions table and return the ObservableList divisionList.
     @return Returns the ObservableList divisionList.
     */
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

    /**Observable list method for getting the data form the first_level_divisions table from the database.
     This will fill the ObservableList statesList with the info from the first_level_divisions table WHERE Country_ID = 1 and adds the division to the ObservableList statesList.
     @return Returns the ObservableList statesList.
     */
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

    /**Observable list method for getting the data form the first_level_divisions table from the database.
     This will fill the ObservableList ukList with the info from the first_level_divisions table WHERE Country_ID = 2 and adds the division to the ObservableList ukList.
     @return Returns the ObservableList ukList.
     */
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

    /**Observable list method for getting the data form the first_level_divisions table from the database.
     This will fill the ObservableList canadaList with the info from the first_level_divisions table WHERE Country_ID = 3 and adds the division to the ObservableList canadaList.
     @return Returns the ObservableList canadaList.
     */
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

    /**Observable list method for getting the data form the countries table from the database.
     This will fill the ObservableList countryList with the info from the countries table and adds the country to the ObservableList countryList.
     @return Returns the ObservableList countryList.
     */
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

    /**This is the onAction method for the country combo box.
     When a country is selected, the stateProv combo box is populated with the list of states or providences that associate with the country ID.
     In the future, make it so that the stateProv combo box doesn't clear its result if the selected state or providence that matches the country.
     */
    @FXML
    void onActionCountry(ActionEvent event) {
        ObservableList<firstLevelDivision> allDivisions = divisionList();//call the divisionList method which will pull from the database

        ObservableList<firstLevelDivision> usStates = statesList();//call the statesList which will only pull division names with countryID 1
        ObservableList<firstLevelDivision> ukProvs = ukList();//call the ukList which will only pull division names with countryID 2
        ObservableList<firstLevelDivision> canadaProvs = canadaList();//call the canadaList which will only pull division names with countryID 3


        int x = countryCb.getValue().getCountryID();//sets x to the countryID that the user selects

        //if-else statements to filter the stateProvCb by the corresponding country
        if(x == 1) {
            stateProvCb.setItems(usStates);
        }
        else if (x == 2){
            stateProvCb.setItems(ukProvs);
        }
        else if(x == 3){
            stateProvCb.setItems(canadaProvs);
        }
        else{
            stateProvCb.setItems(allDivisions);
        }




    }

    /** This is the addCustomerController initialize method.
     This method is created when the application is launched.
     This method ensures that the combo boxes for adding customer stateProv and country are populated.
     */
    @FXML
    void initialize() {

        ObservableList<firstLevelDivision> allDivisions = divisionList();//call the divisionList method which will pull from the database
        ObservableList<countries>allCountries = countryList();//call the countryList method which will pull from the database

        stateProvCb.setItems(allDivisions);//this sets the combo box list but see model firstLevelDivisions for the override of the toString method
        stateProvCb.setVisibleRowCount(5);//visible list will display 5 items at a time


        countryCb.setItems(allCountries);//this sets the combo box list but see model countries for the override of the toString method





    }
}
