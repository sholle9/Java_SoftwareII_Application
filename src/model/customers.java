package model;

import java.time.LocalDateTime;
/**This class holds the objects and behaviors for customers*/
public class customers {

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;


    //Constructor
    /**Constructor for the customers Class. This will assign default values to the customer values.
     @param customerID the customer id
     @param customerName the customer name
     @param address the customer address
     @param postalCode the postal code associated with the customer
     @param phoneNumber the phone number associated with the customer
     @param createDate the date and time the customer was created
     @param createdBy the user that created the customer info
     @param lastUpdate that date and time the customer info was most recently updated
     @param lastUpdatedBy the user that last updated the customer info
     @param divisionID the firstLevelDivision ID that is associated with the customer
     */
    public customers(int customerID, String customerName, String address, String postalCode, String phoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int divisionID){

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    //Getters
    /**This is the getCustomerID method. This will return the ID that is assigned to a specific customer.
     @return Returns the ID of a customer.
     */
    public int getCustomerID(){
        return customerID;
    }

    /**This is the getCustomerName method. This will return the customerName that is assigned to a specific customer.
     @return Returns the customerName of a customer.
     */
    public String getCustomerName(){
        return customerName;
    }

    /**This is the getAddress method. This will return the address that is assigned to a specific customer.
     @return Returns the address of a customer.
     */
    public String getAddress(){
        return address;
    }

    /**This is the getPostalCode method. This will return the postalCode that is assigned to a specific customer.
     @return Returns the postalCode of a customer.
     */
    public String getPostalCode(){
        return postalCode;
    }

    /**This is the getPhoneNumber method. This will return the phoneNumber that is assigned to a specific customer.
     @return Returns the phoneNumber of a customer.
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**This is the getCreateDate method. This will return the createDate that is assigned to a specific customer.
     @return Returns the createDate of a customer.
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**This is the getCreateBy method. This will return the createdBy that is assigned to a specific customer.
     @return Returns the createdBy of a customer.
     */
    public String getCreatedBy(){
        return createdBy;
    }

    /**This is the getLastUpdate method. This will return the lastUpdate that is assigned to a specific customer.
     @return Returns the lastUpdate of a customer.
     */
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    /**This is the getLastUpdatedBy method. This will return the lastUpdatedBy that is assigned to a specific customer.
     @return Returns the lastUpdatedBy of a customer.
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /**This is the getDivisionID method. This will return the divisionID that is assigned to a specific customer.
     @return Returns the divisionID of a customer.
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**This is the getFirstName method. This will return the first part of the fullName string up to the characters ?, !, ., *, or space.
     @return The first part of the fullName string.
     */
    public String getFirstName(String fullName){
        return fullName.split(" (?!.* )")[0];
    }

    /**This is the getFirstName method. This will return the second part of the fullName string starting from the characters ?, !, ., *, or space.
     @return The second part of the fullName string.
     */
    public String getLastName(String fullName){
        return fullName.split(" (?!.* )")[1];
    }

    /**This is the toString method for the customers class. This overrides the toString Method that automatically occurs when the combo boxes have the setItems method.
     @return Returns the customerName of a customer.
     */
    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (customerName);
    }
}
