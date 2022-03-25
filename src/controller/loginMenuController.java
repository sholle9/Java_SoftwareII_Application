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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.customers;
import model.users;

public class loginMenuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    //Global variables used to deal with scene switching
    Stage stage;
    Parent scene;



    @FXML
    void onActionCloseProgram(ActionEvent event)  {

        JDBC.closeConnection();
        System.exit(0);


    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {

        //This gets the input from the text boxes for username and password
        String userName = usernameTxt.getText();
        String password = passwordTxt.getText();


        //so long as the username textbox has characters, it will begin the try catch
        if(userName.isBlank() == false){
            try{
                Connection conn = JDBC.getConnection();//Gets connection to database
                String selectCustomers = "SELECT Password FROM users WHERE User_Name = ?";//Select statement

                DBQuery.setPreparedStatement(conn, selectCustomers);//sets prepared statement to be the select statement
                PreparedStatement ps = DBQuery.getPreparedStatement();//creates prepared statement ps

                //this will complete the selectStatement with the username from the text box input for the WHERE clause
                ps.setString(1,userName);

                ps.execute();//executes the prepared statement

                ResultSet rs = ps.getResultSet();

                //this will begin the search and label of the correct password for the username, if found
                while (rs.next()) {
                    String passwordDB = rs.getString("Password");

                    //so long as the input password matches the database password and the username text box is not empty, this will navigate to the appointmentCalendar
                    if (password.equals(passwordDB) && userName.isBlank() == false) {
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();//cast the window as a stage
                        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
                        stage.setScene(new Scene(scene));
                        stage.show();//actually causes the stage to appear

                        break;
                    }
                    //this is when the password input and password in the database do not match, and the username text box is not empty
                    //AN ERROR MESSAGE WILL BE DISPLAYED
                    else if(password != passwordDB && userName.isBlank() == false){
                        System.out.println("Incorrect password");

                        break;
                    }
                    //when the username text box is empty once the loop has begun
                    //AN ERROR MESSAGE WILL BE DISPLAYED
                    else {
                        System.out.println("Incorrect username");
                    }

                }

            }
            catch (Exception e){
                System.out.println(e.getMessage()); //getMessage will print out the exception found
            }

        }
        else{
            System.out.println("Incorrect username");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
