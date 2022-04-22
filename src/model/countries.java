package model;
/**This class holds the objects and behaviors for countries*/
public class countries {

    private int countryID;
    private String countryName;

    //Constructor
    /**Constructor for the countries Class. This will assign default values to the country values.
     @param countryID the country ID
     @param countryName the country name
     */
    public countries(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    //Getters
    /**This is the getCountryID method. This will return the ID that is assigned to a specific country.
     @return Returns the ID of a country.
     */
    public int getCountryID(){
        return countryID;
    }

    /**This is the getCountryName method. This will return the countryName that is assigned to a specific country.
     @return Returns the countryName of a country.
     */
    public String getCountryName(){
        return countryName;
    }

    /**This is the toString method for the countries class. This overrides the toString Method that automatically occurs when the combo boxes have the setItems method.
     @return Returns the countryName of a country.
     */
    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (countryName);
    }
}
