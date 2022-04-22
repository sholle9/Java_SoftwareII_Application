package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
/**This class holds the objects and behaviors for appointments*/
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

    /**Constructor for the Appointment Class. This will assign default values to the Appointment values.
     @param appointmentID the appointment id
     @param title the appointment title
     @param description the appointment description
     @param location the appointment location
     @param contactID the contact ID associated with the appointment
     @param type the appointment type
     @param startDate the appointment start date
     @param startTime the appointment start time
     @param startDateTime the appointment combined start date and time
     @param endDate the appointment end date
     @param endTime the appointment end time
     @param endDateTime the appointment combined end date and time
     @param createDate the date and time the appointment was created
     @param createdBy the user that created the appointment
     @param lastUpdate that date and time the appointment was most recently updated
     @param lastUpdatedBy the user that last updated the appointment
     @param customerID the customer ID that is associated with the appointment
     @param userID the user ID that is associated with the appointment
     */
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
    /**This is the getAppointmentID method. This will return the ID that is assigned to a specific appointment.
     @return Returns the ID of an appointment.
     */
    public int getAppointmentID() {

        return appointmentID;
    }

    /**This is the getTitle method. This will return the title that is assigned to a specific appointment.
     @return Returns the title of an appointment.
     */
    public String getTitle(){

        return title;
    }

    /**This is the getDescription method. This will return the description that is assigned to a specific appointment.
     @return Returns the description of an appointment.
     */
    public String getDescription(){

        return description;
    }

    /**This is the getLocation method. This will return the location that is assigned to a specific appointment.
     @return Returns the location of an appointment.
     */
    public String getLocation(){

        return location;
    }

    /**This is the getContactID method. This will return the contact ID that is associated with a specific appointment.
     @return Returns the contactID of an appointment.
     */
    public int getContactID(){

        return contactID;
    }

    /**This is the getType method. This will return the type that is assigned to a specific appointment.
     @return Returns the type of an appointment.
     */
    public String getType(){

        return type;
    }

    /**This is the getStartTime method. This will return the startTime that is assigned to a specific appointment.
     @return Returns the startTime of an appointment.
     */
    public LocalTime getStartTime(){

        return startTime;
    }

    /**This is the getStartDate method. This will return the startDate that is assigned to a specific appointment.
     @return Returns the startDate of an appointment.
     */
    public LocalDate getStateDate(){
        return startDate;
    }

    /**This is the getStartDateTime method. This will return the startDateTime that is assigned to a specific appointment.
     @return Returns the startDateTime of an appointment.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**This is the getEndTime method. This will return the endTime that is assigned to a specific appointment.
     @return Returns the endTime of an appointment.
     */
    public LocalTime getEndTime(){

        return endTime;
    }

    /**This is the getEndDate method. This will return the endDate that is assigned to a specific appointment.
     @return Returns the endDate of an appointment.
     */
    public LocalDate getEndDate() {
        return  endDate;
    }

    /**This is the getEndDateTime method. This will return the endDateTime that is assigned to a specific appointment.
     @return Returns the endDateTime of an appointment.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**This is the getCreateDate method. This will return the createDate that is assigned to a specific appointment.
     @return Returns the createDate of an appointment.
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**This is the getCreatedBy method. This will return the createBy that is assigned to a specific appointment.
     @return Returns the createBy of an appointment.
     */
    public String getCreatedBy(){
        return createdBy;
    }

    /**This is the getLastUpdate method. This will return the lastUpdate that is assigned to a specific appointment.
     @return Returns the lastUpdate of an appointment.
     */
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    /**This is the getLastUpdatedBy method. This will return the lastUpdatedBy that is assigned to a specific appointment.
     @return Returns the lastUpdatedBy of an appointment.
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /**This is the getCustomerID method. This will return the customerID that is associated with a specific appointment.
     @return Returns the customerID of an appointment.
     */
    public int getCustomerID(){

        return customerID;
    }

    /**This is the getUserID method. This will return the userID that is associated with a specific appointment.
     @return Returns the userID of an appointment.
     */
    public int getUserID(){

        return userID;
    }


}
