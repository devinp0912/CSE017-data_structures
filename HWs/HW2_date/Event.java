/***
 * Class to model the entity Event
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 1, 2023
 * Last Date Modified: February 8, 2023 
 */
public class Event{
    // data members
    private String description;
    private String location;
    private Date date;
    private Time time;
    /***
     * default constructor 
     * no parameters
     * initilizes description, location, date, and time with "none"
     */
    public Event() throws InvalidDateTimeException{
        description = "none";
        location = "none";
        this.date = new Date();
        this.time = new Time();
    }
    /***
     * constructor with 4 parameters
     * @param   description for the description of event
     * @param   location for the location of event
     * @param   date for the date of event
     * @param   time for the time of event
     */
    public Event(String description, String location, Date date, Time time){ 
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
    }
    /***
	 * Getter for the description of a event
	 * @param	no parameters
	 * @return	the value of the data member description
	 */
    public String getDescription(){
        return description;
    }
    /***
	 * Getter for the location of a event
	 * @param	no parameters
	 * @return	the value of the data member location
	 */
    public String getLocation(){
        return location;
    }
    /***
	 * Getter for the date of a event
	 * @param	no parameters
	 * @return	the value of the data member date
	 */
    public Date getDate(){
        return date;
    }
    /***
	 * Getter for the time of a event
	 * @param	no parameters
	 * @return	the value of the data member time
	 */
    public Time getTime(){
        return time;
    }
    /***
	 * Setter for the description of a event
	 * @param	description to set the data member description
	 * no return value
	 */
    public void setDescription(String description){
        this.description = description;
    }
    /***
	 * Setter for the location of a event
	 * @param	location to set the data member location
	 * no return value
	 */
    public void setLocation(String location){
        this.location = location;
    }
    /***
	 * Setter for the date of a event
	 * @param	date to set the data member date
	 * no return value
	 */
    public void setDate(String date) throws InvalidDateTimeException{
        this.date = new Date(date);
    }
    /***
	 * Setter for the time of a event
	 * @param	time to set the data member time
	 * no return value
	 */
    public void setTime(String time) throws InvalidDateTimeException{
        this.time = new Time(time);
    }
    /***
	 * Method to get the Event information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%s\t%s\t%s\t%s\t", description, location, date, time);
    }
}