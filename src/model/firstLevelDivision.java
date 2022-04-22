package model;
/**This class holds the objects and behaviors for firstLevelDivision*/
public class firstLevelDivision {

    private int divisionID;
    private String divisionName;
    private int countryID;

    //Constructor
    /**Constructor for the firstLevelDivision Class. This will assign default values to the firstLevelDivision values.
     @param divisionID the firstLevelDivision ID
     @param divisionName the firstLevelDivision name
     @param countryID the country ID that is associated with the firstLevelDivision
     */
    public firstLevelDivision(int divisionID, String divisionName, int countryID){

        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }




    //Getters
    /**This is the getDivisionID method. This will return the divisionID that is assigned to a specific firstLevelDivision.
     @return Returns the ID of a firstLevelDivision.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**This is the getDivisionName method. This will return the divisionName that is assigned to a specific firstLevelDivision.
     @return Returns the divisionName of a firstLevelDivision.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**This is the getCountryID method. This will return the countryID that is assigned to a specific firstLevelDivision.
     @return Returns the countryID of a firstLevelDivision.
     */
    public int getCountryID() {
        return countryID;
    }

    /**This is the toString method for the firstLevelDivision class. This overrides the toString Method that automatically occurs when the combo boxes have the setItems method.
     @return Returns the divisionName of a firstLevelDivision.
     */
    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (divisionName);
    }
}
