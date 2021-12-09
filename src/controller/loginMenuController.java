package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        System.exit(0);


    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();//cast the window as a stage
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));//stage(location) the button should go to
        stage.setScene(new Scene(scene));
        stage.show();//actually causes the stage to appear

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
