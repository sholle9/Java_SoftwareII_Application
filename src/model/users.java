package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class users {
    private int userID;
    private String userName;
    private String password;
    private LocalDate createDate;
    private LocalTime createTime;
    private String createdBy;
    private LocalDate lastUpdateDate;
    private LocalTime lastUpdateTime;
    private String lastUpdatedBy;

    //Constructor
    public  users (int userID, String userName, String password, LocalDate createDate, LocalTime createTime, String createdBy, LocalDate lastUpdateDate, LocalTime lastUpdateTime, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateTime = lastUpdateTime;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    //Getters
    public int getUserID(){

        return userID;
    }

    public String getUserName(){

        return userName;
    }

    public String getPassword(){
        return password;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalTime getCreateTime() {
        return createTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public LocalTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (userName);
    }

}
