/***
 * Class to model the entity Appointment
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 1, 2023
 * Last Date Modified: February 8, 2023 
 */
public class Appointment extends Event{
    //data member
    private String contact;
    
    /***
     * default constructor 
     * no parameters
     * initilizes description, location, date, time, and contact with "none"
     */
    public Appointment(){
        super();
        contact = "none";
    }
     /***
     * constructor with 5 parameters
     * @param   description for the description of appointment
     * @param   location for the location of appointment
     * @param   date for the date of appointment
     * @param   time for the time of appointment
     * @param   contact for the contact of appointment
     */
    public Appointment(String description, String location, Date date, Time time, String contact){
        super(description, location, date, time);
        this.contact = contact;
    }
    /***
	 * Getter for the contact of a appointment
	 * @param	no parameters
	 * @return	the value of the data member contact
	 */
    public String getContact(){
        return contact;
    }
    /***
	 * Setter for the contact of a appointment
	 * @param	contact sets the value of the data member contact
	 * no return value
	 */
    public void setContact(String contact){
        this.contact = contact;
    }
    /***
	 * Method to get the Appointment information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out = super.toString();
        return String.format("%s\t%s\n", out, contact);
    }

}