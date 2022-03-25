package model;

import java.time.LocalDateTime;

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
    public int getCustomerID(){
        return customerID;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getAddress(){
        return address;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
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

    public int getDivisionID(){
        return divisionID;
    }

    public String getFirstName(String fullName){
        return fullName.split(" (?!.* )")[0];
    }

    public String getLastName(String fullName){
        return fullName.split(" (?!.* )")[1];
    }

    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (customerName);
    }
}
