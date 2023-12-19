/***
 * Class to model the entity Time
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 23, 2023
 * Last Date Modified: February 26, 2023 
 */
public class Time implements Comparable<Time>{
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
        if(time.matches("0[0-9]:[0-5][0-9]") || time.matches("1[0-9]:[0-5][0-9]")){
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
        if(minutes == 0){
            return String.format("%d:00", hours);
        }
        return String.format("%d:%d", hours, minutes);
    }
    /***
	 * Method for comparing two time objects through the Comparable Interface
	 * @param   t is of type Date
	 * @return  0 if are equal, -1 if the first time occurs after the second, and 1 for vice versa 
	 */
    public int compareTo(Time t){
        if(this.hours < t.hours){
            return -1;
        }else if(this.hours > t.hours){
            return 1;
        }else{// the hours are equal to check minutes
            if(this.minutes < t.minutes){
                return -1;
            }else if(this.minutes > t.minutes){
                return 1;
            }else{
                return 0; // they are equal
            }
        }
    }
}