package model;

public class firstLevelDivision {

    private int divisionID;
    private String divisionName;
    private int countryID;

    //Constructor
    public firstLevelDivision(int divisionID, String divisionName, int countryID){

        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }




    //Getters
    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryID() {
        return countryID;
    }

    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (divisionName);
    }
}
