package model;

public class contacts {

    private int contactID;
    private String contactName;
    private String email;

    public contacts(int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    //Getters
    public int getContactID(){
        return contactID;
    }

    public String getContactName(){
        return contactName;
    }

    public String getEmail(){
        return email;
    }

    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (contactName);
    }
}
