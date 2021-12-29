package model;

public class countries {

    private int countryID;
    private String countryName;

    //Constructor
    public countries(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    //Getters
    public int getCountryID(){
        return countryID;
    }

    public String getCountryName(){
        return countryName;
    }


    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (countryName);
    }
}
