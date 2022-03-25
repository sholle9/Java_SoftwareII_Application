package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class appointments {


    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private int contactID;
    private String type;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDateTime startDateTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private LocalDateTime endDateTime;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;


    //Constructor
    public appointments(int appointmentID, String title, String description, String location,int contactID, String type, LocalDate startDate, LocalTime startTime, LocalDateTime startDateTime, LocalDate endDate, LocalTime endTime, LocalDateTime endDateTime, LocalDateTime createDate,String createdBy,LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactID =contactID;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.startDateTime = startDateTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.endDateTime = endDateTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
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

    public LocalTime getStartTime(){

        return startTime;
    }

    public LocalDate getStateDate(){
        return startDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalTime getEndTime(){

        return endTime;
    }

    public LocalDate getEndDate() {
        return  endDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    public int getCustomerID(){

        return customerID;
    }

    public int getUserID(){

        return userID;
    }


}
