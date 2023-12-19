/***
 * Class to model the entity PrintRequest.java
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 9, 2023
 * Last Date Modified: March 12, 2023
 */
public class PrintRequest implements Comparable<PrintRequest>{
    //data members
    private int id;
    private String group;
    private long size;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes id and size to 0 and group to the lowest priority print request group "batch"
	 */
    public PrintRequest(){
        id = 0;
        group = "batch";
        size = 0;
    }
    /***
	 * Constructor with three parameters
	 * @param	id for the id of the request
	 * @param	group for the priority group of request
	 * @param	size for the size of the file 
     */
    public PrintRequest(int id, String group, long size){
        this.id = id;
        this.group = group;
        this.size = size;
    }
    /***
	 * Getter for the id of a printRequest
	 * no parameters
	 * @return	the value of the data member id
	 */
    public int getID(){return id;}
    /***
	 * Getter for the group of a printRequest
	 * no parameters
	 * @return	the value of the data member group
	 */
    public String getGroup(){return group;}
    /***
	 * Getter for the size of a printRequest
	 * no parameters
	 * @return	the value of the data member size
	 */
    public long getSize(){return size;}
    /***
	 * Setter for the id of an printReuqest
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setID(int id){this.id = id;}
    /***
	 * Setter for the group of an printReuqest
	 * @param	group to set the data member group
	 * no return value
	 */
    public void setGroup(String group){this.group = group;}
    /***
	 * Setter for the size of an printReuqest
	 * @param	size to set the data member size
	 * no return value
	 */
    public void setSize(long size){this.size = size;}
    /***
	 * Method to set the size of the file to a formatted string with either KB GB or MB respectively based on the size of the file
	 * @param   s holds the size of the file
	 * @return the formatted string
	 */
    public static String convertByte(long s){
        double bb = (double)s;
        if(s >= 1000000000){
            bb = bb/1000000000;
            return String.format("%.1fGB", bb);
        }else if(s >= 1000000){
            bb /= 1000000;
            return String.format("%.1fMB", bb);
        }else if(s >= 1000){
            bb /= 1000;
            return String.format("%.1fKB", bb);
        }else{
            return String.format("%dB", bb);
        }
    }
    /***
	 * Method to set the "time to print" into a formatted string in the format of dd:hh:mm:ss; d = day, h = hour, etc.
	 * @param   s holds the size of the file
	 * @return the formatted string
	 */
    public String calculateCompletionTime(long s){
        String seconds = "00";
        String minutes = "00";
        String hours = "00";
        String days = "00";
        int dayNum = 0;
        int hourNum = 0;
        int minuteNum = 0;
        int secondNum = 0;
        double printTime = Math.round(((double)s) / 10000);
        if(printTime >= 86400){
            dayNum = ((int)printTime)/86400;
            if(dayNum < 10){
                days = "0" + dayNum;
            }else{
                days = dayNum + "";
            }
            printTime = printTime - dayNum * 86400;
        }
        if(printTime >= 3600){
            hourNum = ((int)printTime)/3600;
            if(hourNum < 10){
                hours = "0" + hourNum;
            }else{
                hours = hourNum + "";
            }
            printTime = printTime - hourNum * 3600;
        }   
        if(printTime >= 60){
            minuteNum = ((int)printTime)/60;
            
            if(minuteNum < 10){
                minutes = ("0" + minuteNum);
            }else{
                minutes = (minuteNum + "");
            }
        }
        secondNum = ((int)printTime) - (minuteNum * 60);
        if(secondNum < 10){
            seconds = ("0" + secondNum);
        }else{
            seconds = (secondNum + "");
        }
        return String.format("%s:%s:%s:%s", days, hours, minutes, seconds);
    }
    /***
	 * Method to get the printRequest information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String printMeBytes = convertByte(size);//format long into byte string
        String printMeTime = calculateCompletionTime(size);//format long into time string

        return String.format("%-10d\t%-10s\t%-10s\t%-10s", id, group, printMeBytes, printMeTime);
    }
    /***
	 * Method to help the compareTo method to decipher the desired order of root, admin, user, and then batch. 
	 * no param
	 * @return the index at which the group data member equals an element in the priority array
	 */
    public int getPriority(){
        String[] priorities = {"root", "admin", "user", "batch"};
        for(int i = 0; i < priorities.length; i++){
            if(priorities[i].equals(group)){
                return i;
            }          
        }
        return -1;
    }
    /***
	 * Method for comparing the priority of print requests, sorts them based on which person sent the request
	 * @param   pr is of type PrintRequest
	 * @return  0 if equal; a negative number if this.pr is less important, or a positive if vice versa
	 */
    public int compareTo(PrintRequest pr){
        return this.getPriority() - pr.getPriority();
    }
}