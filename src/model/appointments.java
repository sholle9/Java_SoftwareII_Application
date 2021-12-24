package model;

import helper.DBQuery;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class appointments {


    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private int contactID;
    private String type;
    private String start;
    private String end;
    //private String createDate;
    //private String createdBy;
    //private String lastUpdate;
    //private String lastUpdatedBy;
    private int customerID;
    private int userID;


    //Constructor
    public appointments(int appointmentID, String title, String description, String location,int contactID, String type,String start, String end, /*String createDate,String createdBy,String lastUpdate, String lastUpdatedBy,*/  int customerID, int userID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactID =contactID;
        this.type = type;
        this.start = start;
        this.end = end;
        //this.createDate = createDate;
        //this.createdBy = createdBy;
        //this.lastUpdate = lastUpdate;
        //this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;

    }


    //Getters
    public int getAppointmentID() {

        return appointmentID;
    }

    public String getTitle(){

        return title;
    }

    public String getDescription(){

        return description;
    }

    public String getLocation(){

        return location;
    }

    public int getContactID(){
        return contactID;
    }

    public String getType(){
        return type;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    /*public String getCreateDate(){
        return createDate;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public String getLastUpdate(){
        return lastUpdate;
    }

    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }*/

    public int getCustomerID(){
        return customerID;
    }

    public int getUserID(){
        return userID;
    }


}
