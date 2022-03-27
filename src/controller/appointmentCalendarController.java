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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.appointments;
import model.customers;

public class appointmentCalendarController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<appointments> appointmentTableView;

    @FXML
    private TableColumn<?, ?> appointmentIdCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private TableColumn<?, ?> apptCustomerIdCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private RadioButton weeklyViewRBtn;

    @FXML
    private RadioButton monthlyViewRBtn;

    @FXML
    private TableView<customers> customerTableView;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> postalCodeCol;

    @FXML
    private TableColumn<?, ?> phoneNumberCol;

    @FXML
    private TableColumn<?, ?> createDateCol;

    @FXML
    private TableColumn<?, ?> createdByCol;

    @FXML
    private TableColumn<?, ?> lastUpdateCol;

    @FXML
    private TableColumn<?, ?> lastUpdatedByCol;

    @FXML
    private TableColumn<?, ?> divisionIdCol;

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionLogout(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/loginMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateAppointment.fxml"));
        Parent appointmentTableViewParent = loader.load();
        Scene appointmentTableViewScene = new Scene(appointmentTableViewParent);

        //Access the updateAppointmentController to call the appointmentDataTransfer method
        updateAppointmentController controller = loader.getController();
        controller.appointmentDataTransfer(appointmentTableView.getSelectionModel().getSelectedItem());


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/updateAppointment.fxml"));
        stage.setScene(appointmentTableViewScene);
        stage.show();

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
        Parent customerTableViewParent = loader.load();
        Scene customerTableViewScene = new Scene(customerTableViewParent);

        //Access the updateCustomerController to call the customerDataTransfer method
        updateCustomerController controller = loader.getController();
        controller.customerDataTransfer(customerTableView.getSelectionModel().getSelectedItem());


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/updateCustomer.fxml"));
        stage.setScene(customerTableViewScene);
        stage.show();

    }
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

    //Observable list method for getting the data from the database for the GUI appointment table
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
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getTimestamp("Create_Date").toLocalDateTime(),
                        rs.getString("Created_By"),
                        rs.getTimestamp("Last_Update").toLocalDateTime(),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID")
                );
                customerList.add(customer);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return customerList;

    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<appointments> allAppointments = appointmentList();//calls the appointmentList method for the observable list allAppointments
        ObservableList<customers> allCustomers = customerList();//calls the customerList method for the observable list allCustomers

        appointmentTableView.setItems(allAppointments);//uses the allAppointments observable list for the appointmentTableView
        customerTableView.setItems(allCustomers);//uses the allCustomers observable list for the customerTableView


        //this sets the column information by using the appointments model getters that will return the info from the corresponding items in the observable list allAppointments
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));


        //this sets the column information by using the customer model getters that will return the info from the corresponding items in the observable list allCustomers
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));


    }

}