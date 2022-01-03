package model;

public class users {
    private int userID;
    private String userName;

    //Constructor
    public  users (int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    //Getters
    public int getUserID(){
        return userID;
    }

    public String getUserName(){
        return userName;
    }

    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (userName);
    }
}
