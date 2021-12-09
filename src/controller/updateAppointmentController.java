package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class updateAppointmentController {

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
    private ComboBox<?> contactCb;

    @FXML
    private DatePicker startDateDp;

    @FXML
    private ComboBox<?> startTimeCb;

    @FXML
    private DatePicker endDateDp;

    @FXML
    private ComboBox<?> endTimeCb;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField userIdTxt;

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
    void onActionDeleteAppointment(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSaveUpdatedAppointment(ActionEvent event) throws IOException {

        stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentCalendar.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void initialize() {

    }
}
