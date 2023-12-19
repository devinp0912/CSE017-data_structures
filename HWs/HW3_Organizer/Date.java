/***
 * Class to model the entity Date
 * @author GIVEN TO US FROM COURSESITE
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
public class Date implements Comparable<Date>{
    //date members
    private int month;
    private int day;
    private int year;
    /***
     * default constructor 
     * no parameters
     * initilizes month day and year with 0
     */
    public Date(){
        month = 1;
        day = 1;
        year = 1970;
    }
    /***
     * constructor with 1 parameters
     * @param   date holds a formatted string that if isn't in the format of mm/dd/yyyy throws InvalidDateTimeException
     */
    public Date(String date) throws InvalidDateTimeException {
        if(date.matches("\\d{2}/\\d{2}/\\d{4}")){
            String[] items = date.split("/");
            month = Integer.parseInt(items[0]);
            if(month < 1 || month > 12)
                throw new InvalidDateTimeException("Invalid month. Month should be from 1 to 12.");
            day = Integer.parseInt(items[1]);
            if(day < 1 || day > 31)
                throw new InvalidDateTimeException("Invalid day. Day should be from 1 to 31.");
            year = Integer.parseInt(items[2]);
            if(year < 1970 || year > 2030)
                throw new InvalidDateTimeException("Invalid year. Month should be from 1970 to 2030.");
        }
        else
          throw new InvalidDateTimeException("Invalid Date Format (mm/dd/yyyy)");
    }
    /***
	 * Getter for the month of a Date
	 * @param	no parameters
	 * @return	the value of the data member month
	 */
    public int getMonth() { return month;}
    /***
	 * Getter for the day of a Date
	 * @param	no parameters
	 * @return	the value of the data member day
	 */
    public int getDay() { return day;}
    /***
	 * Getter for the year of a Date
	 * @param	no parameters
	 * @return	the value of the data member year
	 */
    public int getYear() { return year;}
    /***
	 * Setter for the month of a Date
	 * @param	m holds the value of a month
	 * no return value
	 */
    public void setMonth(int m) throws InvalidDateTimeException{
        if(m < 1 || m > 12)
            throw new InvalidDateTimeException("Invalid month. Month should be from 1 to 12.");
        month = m;
    }
    /***
	 * Setter for the day of a Date
	 * @param	d holds the value of a day
	 * no return value
	 */
    public void setDay(int d) throws InvalidDateTimeException{
        if(d < 1 || d > 31)
           throw new InvalidDateTimeException("Invalid day. Day should be from 1 to 31.");
        day = d;
    }
    /***
	 * Setter for the year of a Date
	 * @param	y holds the value of a year
	 * no return value
	 */
    public void setYear(int y) throws InvalidDateTimeException{
        if(y < 1970 || y > 2030)
                throw new InvalidDateTimeException("Invalid year. Year should be from 1970 to 2030.");
        year = y;
    }
    /***
	 * Method to get the Date information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%02d/%02d/%04d", month, day, year);
    }
    /***
	 * Method to compare dates by chronological order
	 * @param   date holds the date being compared 
	 * @return  an integer, a positive number if this date is after the param and vice versa. 0 if they are equal
	 */
    public int compareTo(Date date){
        if(this.getYear() == date.getYear()){
            if(this.getMonth() == date.getMonth()){
                return this.getDay() - date.getDay();
            }
            return this.getMonth() - date.getMonth();
        }
        return this.getYear() - date.getYear();
    }
}