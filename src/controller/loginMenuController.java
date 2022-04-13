package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.*;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
                            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();//cast the window as a stage
                            scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
                            stage.setScene(new Scene(scene));
                            stage.show();//actually causes the stage to appear

                            break;
                        }
                        //this is when the password input and password in the database do not match
                        //AN ERROR MESSAGE WILL BE DISPLAYED
                        else if (password != passwordDB) {
                            //System.out.println("Incorrect password");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle(rb.getString("errorTitle"));
                            alert.setContentText(rb.getString("incorrectPassword"));
                            alert.showAndWait();

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
                //System.out.println("Incorrect username");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("errorTitle"));
                alert.setContentText(rb.getString("incorrectUsername"));
                alert.showAndWait();
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

        //This displays the location of the system in a label
        ZoneId timeZone = ZoneId.systemDefault();
        timeZoneLb.setText(String.valueOf(timeZone));


    }
}
