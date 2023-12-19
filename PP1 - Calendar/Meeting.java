/***
 * Class to model the entity Meeting
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 1, 2023
 * Last Date Modified: February 8, 2023 
 */
public class Meeting extends Event{
    //data members
    private String host;
    private int guest;

    /***
     * default constructor 
     * no parameters
     * initilizes description, location, date, time, and host with "none" and guest with 0
     */
    public Meeting() throws InvalidDateTimeException{
        super();
        host = "none";
        guest = 0;
    }

    /***
     * constructor with 6 parameters
     * @param   description for the description of meeting
     * @param   location for the location of meeting
     * @param   date for the date of meeting
     * @param   time for the time of meeting
     * @param   host for the host of meeting
     * @param   guest for number of guests of meeting
     */
    public Meeting(String description, String location, Date date, Time time, String host, int guest){
        super(description, location, date, time);
        this.host = host;
        this.guest = guest;
    }

    /***
	 * Getter for the host of a meeting
	 * @param	no parameters
	 * @return	the value of the data member host
	 */
    public String getHost(){
        return host;
    }
    /***
	 * Getter for the guest of a meeting
	 * @param	no parameters
	 * @return	the value of the data member guest
	 */
    public int getGuest(){
        return guest;
    }
    /***
	 * Setter for the host of a meeting
	 * @param	host for changing the value of data member host
	 * no return value
	 */
    public void setHost(String host){
        this.host = host;
    }
    /***
	 * Setter for the guest of a meeting
	 * @param	guest for setting the value of data member guest 
	 * no return value
	 */
    public void setGuest(int guest){
        this.guest = guest;
    }
    /***
	 * Method to get the Meeting information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out = super.toString();
        return String.format("%s\t%s\t\t%d\n", out, host, guest);
    }
}