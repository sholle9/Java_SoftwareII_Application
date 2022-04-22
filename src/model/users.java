package model;

import java.time.LocalDate;
import java.time.LocalTime;
/**This class holds the objects and behaviors for users*/
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
    /**Constructor for the users Class. This will assign default values to the user values.
     @param userID the user id
     @param userName the username for the user
     @param password the password associated with a user
     @param createDate the date the user was created
     @param createTime the time the user was created
     @param createdBy the user that created the user info
     @param lastUpdateDate the date the user info was most recently updated
     @param lastUpdateTime the time the user info was most recently updated
     @param lastUpdatedBy the user that last updated the user info
     */
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
    /**This is the getUserID method. This will return the ID that is assigned to a specific user.
     @return Returns the ID of a user.
     */
    public int getUserID(){

        return userID;
    }

    /**This is the getUserName method. This will return the userName that is assigned to a specific user.
     @return Returns the userName of a user.
     */
    public String getUserName(){

        return userName;
    }

    /**This is the getPassword method. This will return the password that is assigned to a specific user.
     @return Returns the password of a user.
     */
    public String getPassword(){
        return password;
    }

    /**This is the getCreateDate method. This will return the createDate that is assigned to a specific user.
     @return Returns the createDate of a user.
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**This is the getCreateTme method. This will return the createTime that is assigned to a specific user.
     @return Returns the createTime of a user.
     */
    public LocalTime getCreateTime() {
        return createTime;
    }

    /**This is the getCreatedBy method. This will return the createdBy that is assigned to a specific user.
     @return Returns the createdBy of a user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**This is the getLastUpdateDate method. This will return the lastUpdateDate that is assigned to a specific user.
     @return Returns the lastUpdateDate of a user.
     */
    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**This is the getLastUpdateTme method. This will return the lastUpdateTime that is assigned to a specific user.
     @return Returns the lastUpdateTime of a user.
     */
    public LocalTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**This is the getLastUpdatedBy method. This will return the lastUpdatedBy that is assigned to a specific user.
     @return Returns the lastUpdatedBy of a user.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**This is the toString method for the users class. This overrides the toString Method that automatically occurs when the combo boxes have the setItems method.
     @return Returns the userName of a user.
     */
    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (userName);
    }

}
