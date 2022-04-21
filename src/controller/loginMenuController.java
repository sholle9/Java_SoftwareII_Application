package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
import java.util.Locale;
import java.util.Objects;
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
import lambdaExpressionInterfaces.loginMenu;
import model.appointments;
import model.users;

public class loginMenuController implements Initializable {

    @FXML
    public Label timeZoneLb;

    @FXML
    public Label timeZoneTitleLb;

    @FXML
    public Label userNameLb;

    @FXML
    public Label passwordLb;

    @FXML
    public TitledPane loginTitlePane;

    @FXML
    public Button closeBtn;

    @FXML
    public Button loginBtn;

    @FXML
    ResourceBundle ResourceBundle;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    //Global variables used to deal with scene switching
    Stage stage;
    Parent scene;


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

    //Observable list method for getting the data from the database
    public ObservableList<users> usersList(){
        ObservableList <users> usersList = FXCollections.observableArrayList();
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
                        rs.getString("Last_Updated_By")

                );
                usersList.add(user);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()); //getMessage will print out the exception found
        }
        return usersList;

    }

    @FXML
    void onActionCloseProgram(ActionEvent event)  {

        JDBC.closeConnection();
        System.exit(0);


    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {

        String filename = "Files/login_activity.txt";//Filename variable
        FileWriter fwriter = new FileWriter(filename, true); //creates a filewriter object
        PrintWriter outputFile = new PrintWriter(fwriter); //creates and opens file

        //finds the resource bundles with rb and match them with the default language of the system
        ResourceBundle rb = ResourceBundle.getBundle("rb", Locale.getDefault());

        //depending on the language of the system, it will draw from the resource bundle with the appropriate language
        if(Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {

            //This gets the input from the text boxes for username and password
            String userName = usernameTxt.getText();
            String password = passwordTxt.getText();

            String correctUserName = null;

            //this will reassign the correctUserName to the username that corresponds to the db
            for (users user : usersList()) {
                if (Objects.equals(userName, user.getUserName())) {
                    correctUserName = userName;
                    break;
                }
            }

            //so long as the correctUserName is reassigned, it will begin the try catch
            if (correctUserName != null) {
                try {
                    Connection conn = JDBC.getConnection();//Gets connection to database
                    String selectCustomers = "SELECT Password FROM users WHERE User_Name = ?";//Select statement

                    DBQuery.setPreparedStatement(conn, selectCustomers);//sets prepared statement to be the select statement
                    PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

                    //this will complete the selectStatement with the username from the text box input for the WHERE clause
                    ps.setString(1, userName);

                    ps.execute();//executes the prepared statement

                    ResultSet rs = ps.getResultSet();

                    //this will begin the search and label of the correct password for the username, if found
                    while (rs.next()) {
                        String passwordDB = rs.getString("Password");


                        //so long as the input password matches the database password this will navigate to the appointmentCalendar
                        if (password.equals(passwordDB)) {
                            //writes to the login_activity.txt
                            outputFile.println("Username: " + correctUserName + " Date: " + LocalDate.now() + " Time: " + LocalTime.now() + " Login Successful!");

                            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();//cast the window as a stage
                            scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
                            stage.setScene(new Scene(scene));
                            stage.show();//actually causes the stage to appear

                            //closes the activity_login.txt file
                            outputFile.close();

                            ObservableList<appointments> allAppointments = appointmentList();//calls the appointmentList method for the observable list allAppointments

                            int i = 0;

                            //for loop to go through each appointment to see if there are appointments within 15 minutes of pressing the login button
                            for(appointments app : allAppointments){
                                if(app.getStateDate().isEqual(LocalDate.now())){
                                    if(app.getStartTime().equals(LocalTime.now()) || (app.getStartTime().isAfter(LocalTime.now()) && app.getStartTime().isBefore(LocalTime.now().plusMinutes(15))) || app.getStartTime().equals(LocalTime.now().plusMinutes(15))){
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Upcoming Appointment(s)");
                                        alert.setContentText("Upcoming appointment with ID: " + app.getAppointmentID() + ", Date: " + app.getStateDate() + ", and Time : " + app.getStartTime() + " to " + app.getEndTime() + ".");
                                        alert.showAndWait();
                                    }
                                    else{
                                        i++;
                                    }
                                }
                                else{
                                    i++;
                                }

                            }

                            if(i == allAppointments.size()){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Upcoming Appointment(s)");
                                alert.setContentText("No upcoming appointments.");
                                alert.showAndWait();
                            }


                            break;
                        }
                        //this is when the password input and password in the database do not match
                        //AN ERROR MESSAGE WILL BE DISPLAYED
                        else if (password != passwordDB) {
                            //writes to the login_activity.txt
                            outputFile.println("Username: " + userName + " Date: " + LocalDate.now() + " Time: " + LocalTime.now() + " Login Unsuccessful!");

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle(rb.getString("errorTitle"));
                            alert.setContentText(rb.getString("incorrectPassword"));
                            alert.showAndWait();

                            //closes the activity_login.txt file
                            outputFile.close();

                            break;
                        }

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage()); //getMessage will print out the exception found
                }

            }
            //when the correctUserName is not reassigned because the input in the userNameTxt does not match the usernames found in the db
            //AN ERROR MESSAGE WILL BE DISPLAYED
            else {
                //writes to the login_activity.txt
                outputFile.println("Username: " + userName + " Date: " + LocalDate.now() + " Time: " + LocalTime.now() + " Login Unsuccessful!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("errorTitle"));
                alert.setContentText(rb.getString("incorrectUsername"));
                alert.showAndWait();

                //closes the activity_login.txt file
                outputFile.close();
            }

        }





    }

    @Override
    public void initialize(URL url, ResourceBundle ResourceBundle) {

        //finds the resource bundles with rb and match them with the default language of the system
        ResourceBundle rb = ResourceBundle.getBundle("rb", Locale.getDefault());

        //depending on the language of the system, it will draw from the resource bundle with the appropriate language
        if(Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            loginTitlePane.setText(rb.getString("loginTitle"));
            userNameLb.setText(rb.getString("usernameLabel"));
            passwordLb.setText(rb.getString("passwordLabel"));
            timeZoneTitleLb.setText(rb.getString("timeZoneLabel"));
            closeBtn.setText(rb.getString("closeButton"));
            loginBtn.setText(rb.getString("loginButton"));
        }



        //Lambda Expression that displays the location of the system in a label
        loginMenu timeZone = () -> ZoneId.systemDefault();
        timeZoneLb.setText(String.valueOf(timeZone.timeZone()));


    }
}
