/***
 * Class to model the entity Time
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 6, 2023
 * Last Date Modified: March 30, 2023 
 */
public class Time implements Comparable<Time>{
    //data members
    private int hours;
    private int minutes;
    /***
     * default constructor 
     * no parameters
     * initilizes hours and minutes with 0
     */
    public Time(){
        hours = minutes = 0;
    }
    /***
     * constructor with 1 parameters
     * @param   time holds a formatted string that if isn't in the format of hh:mm throws InvalidDateTimeException
     */
    public Time(String time) throws InvalidDateTimeException{
        String[] items = time.split(":");
        try{
            int h = Integer.parseInt(items[0]);
            int m = Integer.parseInt(items[1]);
            if(h < 0 || h > 23)
                throw new InvalidDateTimeException("Invalid hours. Hours should be from 0 to 23.");
            if(m < 0 || m > 59)
                throw new InvalidDateTimeException("Invalid minutes. Minutes should be from 0 to 59.");
            hours = h;
            minutes = m;
        }
        catch(NumberFormatException e){
            System.out.println("Error: hours or minutes are not numbers.");
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
    public void setHours(int h) throws InvalidDateTimeException{
        if(h < 0 || h > 23)
                throw new InvalidDateTimeException("Invalid hours. Hours should be from 0 to 23.");
        hours = h;
    }
    /***
	 * Setter for the minutes of a Time
	 * @param	m holds the value to set minutes to
	 * no return value
	 */
    public void setMinutes(int m) throws InvalidDateTimeException{
        if(m < 0 || m > 59)
                throw new InvalidDateTimeException("Invalid minutes. Minutes should be from 0 to 59.");
        minutes = m;
    }
    /***
	 * Method to get the Time information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%02d:%02d", hours, minutes);
    }
    /***
     * Method to compare time objects based on there chronological order
     * @param   time holds the other time value you want to compare to this.time
     * @return an int; 0 if equal; or a +/- number depending on whether the first or second time is first
     */
    public int compareTo(Time time){
        if(this.getHours() == time.getHours()){
            return this.getMinutes() - time.getMinutes();
        }
        return this.getHours() - time.getHours();
    }
    /***
     * Method that checks if 2 time objects are the same
     * @param   obj holds the reference to the time obj
     * @return T/F if they are equal
     */
    public boolean equals(Object obj){
        if(obj instanceof Time){
            Time time = (Time) obj;
            return this.hours == time.hours && 
                   this.minutes == time.minutes;
        }
        return false;
    }
    /***
     * Method that when called increments the time by 1 minute in the correct format
     * no param
     * no return value
     */
    public void tick(){
        ++minutes;
        if(minutes == 60){
            minutes = 0;
            ++hours;
            if(hours == 24){
                hours = 0;
            }
        }
    }
    /***
     * Method that determines how far apart 2 times are in minutes
     * @param   t holds a time to compare to this.time
     * @return the difference between 2 times in minutes
     */
    public int diff(Time t){
        if(this.hours == t.hours){
            return Math.abs(this.minutes - t.minutes);
        }else{
            int t1 = (this.hours * 60) + this.minutes;
            int t2 = (t.hours * 60) + t.minutes;
            return Math.abs(t1 - t2);
        }
    }
}