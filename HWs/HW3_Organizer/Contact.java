/***
 * Class to model the entity Contact
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
public class Contact implements Comparable<Contact>{
    //data members
    private String name;
    private String phone;
    private String email;
    /***
     * constructor with 3 parameters
     * @param   name holds the name of the contact
     * @param   phone holds the phone number of the contact
     * @param   email holds the email of the contact
     */
    public Contact(String name, String phone, String email){
        this.name = name;
        this.phone= phone;
        this.email = email;
    }
    /***
	 * Getter for the name of a contact
	 * @param	no parameters
	 * @return	the value of the data member name
	 */
    public String getName(){
        return name;
    }
    /***
	 * Getter for the phone of a contact
	 * @param	no parameters
	 * @return	the value of the data member phone
	 */
    public String getPhone(){
        return phone;
    }
    /***
	 * Getter for the email of a contact
	 * @param	no parameters
	 * @return	the value of the data member email
	 */
    public String getEmail(){
        return email;
    }
    /***
	 * Setter for the name of a contact
	 * @param	name holds the value of the new name
	 * no return value
	 */
    public void setName(String name){
        this.name = name;
    }
    /***
	 * Setter for the phone of a contact
	 * @param	phone holds the value of the new phone
	 * no return value
	 */
    public void setPhone(String phone){
        this.phone = phone;
    }
    /***
	 * Setter for the email of a contact
	 * @param	email holds the value of the new email
	 * no return value
	 */
    public void setEmail(String email){
        this.email = email;
    }
    /***
	 * Method to get the contact information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-20s%-20s%s", name, phone, email);
    }
    /***
	 * Method checks if 2 contact objects have equal data members
	 * @param   o holds an object
	 * @return boolean value if they are equal T/F
	 */
    public boolean equals(Object o){
        if(o instanceof Contact){
            Contact c = (Contact)o;
            if((c.name).equals(this.name) && c.phone.equals(this.phone) && (c.email).equals(this.email)){
                return true;
            }
        }
        return false;
    }
    /***
	 * Method to compare names of contacts so to put them in alphabetical order
	 * @param   c holds a contact object 
	 * @return  an integer, a positive number if this contact is after the param and vice versa. 0 if they are equal
	 */
    public int compareTo(Contact c){
        return (this.name.compareTo(c.getName()));
    }
}