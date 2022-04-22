package model;
/**This class holds the objects and behaviors for contacts*/
public class contacts {

    private int contactID;
    private String contactName;
    private String email;

    /**Constructor for the contacts Class. This will assign default values to the contact values.
    @param contactID the contact ID
    @param contactName the contact name
    @param email the contact email
     */
    public contacts(int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    //Getters
    /**This is the getContactID method. This will return the ID that is assigned to a specific contact.
     @return Returns the ID of a contact.
     */
    public int getContactID(){
        return contactID;
    }

    /**This is the getContactName method. This will return the contactName that is assigned to a specific contact.
     @return Returns the contactName of a contact.
     */
    public String getContactName(){
        return contactName;
    }

    /**This is the getEmail method. This will return the email that is assigned to a specific contact.
     @return Returns the email of a contact.
     */
    public String getEmail(){
        return email;
    }

    /**This is the toString method for the contacts class. This overrides the toString Method that automatically occurs when the combo boxes have the setItems method.
     @return Returns the contactName of a contact.
     */
    @Override //this overrides the toString Method that automatically occurs when the combo boxes have the setItems method
    public String toString(){
        return (contactName);
    }
}
