/***
 * Class to model the entity Date
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 8, 2023 
 */
public class Date{
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
        month = 0;
        day = 0;
        year = 0;
    }
    /***
     * constructor with 1 parameters
     * @param   date holds a formatted string that if isn't in the format of mm/dd/yyyy throws InvalidDateTimeException
     */
    public Date(String date) throws InvalidDateTimeException{
        if(date.matches("[0-1][0-9]/[1-3][0-9]/[0-9][0-9][0-9][0-9]")){
            int m1 = (int) (date.charAt(0)-'0') * 10;
            int m2 = (int) (date.charAt(1)-'0');
            this.month = m1+m2;
            int d1 = (int) (date.charAt(3)-'0') * 10;
            int d2 = (int) (date.charAt(4)-'0');
            this.day = d1+d2;
            int y1 = (int) (date.charAt(6)-'0') *1000;
            int y2 = (int) (date.charAt(7)-'0') *100;
            int y3 = (int) (date.charAt(8)-'0') *10;
            int y4 = (int) (date.charAt(9)-'0');
            this.year = y1+y2+y3+y4;
        }else{
            throw new InvalidDateTimeException();
        }
    }
    /***
	 * Getter for the month of a Date
	 * @param	no parameters
	 * @return	the value of the data member month
	 */
    public int getMonth(){
        return month;
    }
    /***
	 * Getter for the day of a Date
	 * @param	no parameters
	 * @return	the value of the data member day
	 */
    public int getDay(){
        return day;
    }
    /***
	 * Getter for the year of a Date
	 * @param	no parameters
	 * @return	the value of the data member year
	 */
    public int getYear(){
        return year;
    }
    /***
	 * Setter for the month of a Date
	 * @param	m holds the value of a month
	 * no return value
	 */
    public void setMonth(int m){
        month = m;
    }
    /***
	 * Setter for the day of a Date
	 * @param	d holds the value of a day
	 * no return value
	 */
    public void setDay(int day){
        this.day = day;
    }
    /***
	 * Setter for the year of a Date
	 * @param	y holds the value of a year
	 * no return value
	 */
    public void setYear(int year){
        this.year = year;
    }
    /***
	 * Method to get the Date information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%d/%d/%d", month, day, year);
    }
}