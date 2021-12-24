package model;

public class customers {

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;


    //Constructor
    public customers(int customerID, String customerName, String address, String postalCode, String phoneNumber, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID){

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

    public String getCreateDate(){
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
    }

    public int getDivisionID(){
        return divisionID;
    }
}
