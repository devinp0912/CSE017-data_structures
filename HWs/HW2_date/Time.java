/***
 * Class to model the entity Time
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 8, 2023 
 */
public class Time{
    //date members
    private int hours;
    private int minutes;
    /***
     * default constructor 
     * no parameters
     * initilizes hours and minutes with 0
     */
    public Time(){
        hours = 0;
        minutes = 0;
    }
    /***
     * constructor with 1 parameters
     * @param   time holds a formatted string that if isn't in the format of hh:mm throws InvalidDateTimeException
     */
    public Time(String time) throws InvalidDateTimeException{
        if(time.matches("0[0-9]:[1-5][0-9]") || time.matches("1[0-9]:[1-5][0-9]")){
            //get hours
            int h1 = (int) (time.charAt(0)-'0') * 10;
            int h2 = (int) (time.charAt(1)-'0');
            this.hours = h1 + h2;
            int m1 = (int) (time.charAt(3)-'0') * 10;
            int m2 = (int) (time.charAt(4)-'0');
            this.minutes = (m1) + m2;
        }else{
            throw new InvalidDateTimeException();
        }
    }
    /***
	 * Getter for the hours of a Time
	 * @param	no parameters
	 * @return	the value of the data member hours
	 */
    public int getHours(){
        return hours;
    }
    /***
	 * Getter for the minutes of a Time
	 * @param	no parameters
	 * @return	the value of the data member minutes
	 */
    public int getMinutes(){
        return minutes;
    }
    /***
	 * Setter for the hours of a Time
	 * @param	h holds the value to set hours to
	 * no return value
	 */
    public void setHours(int h){
        hours = h;
    }
    /***
	 * Setter for the minutes of a Time
	 * @param	m holds the value to set minutes to
	 * no return value
	 */
    public void setMinutes(int m){
        minutes = m;
    }
    /***
	 * Method to get the Time information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        if(hours <= 9){
            return String.format("0%d:%d", hours, minutes);
        }
        return String.format("%d:%d", hours, minutes);
    }
}