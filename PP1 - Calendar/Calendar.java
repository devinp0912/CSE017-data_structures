/***
 * Class to model the entity Calendar
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 23, 2023
 * Last Date Modified: February 26, 2023 
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class Calendar implements Cloneable{
    //data members
    private Event[] events;
    private int count;
    /***
     * default constructor 
     * no parameters
     * initilizes count to 0 and events to an empty array
     */
    public Calendar(){
        count = 0;
        events = new Event[50];
    } 
    /***
     * constructor with 1 parameters, calls readEvents to initlize the array with data
     * @param   filename holds the name of a file  
     */
    public Calendar(String filename){
        count = 0;
        events = new Event[50];
        readEvents(filename);
        
    }
    /***
	 * Method for reading the data from a file, throws FileNotFoundException if such occurs
	 * @param   filename is the name of a file that will get searched for
	 * no return value
     */
    private void readEvents(String filename){
        File file = new File(filename);
        try{
            Scanner readFile = new Scanner(file);
            while(readFile.hasNext()){
                String typeOfFile = readFile.nextLine();
                if(typeOfFile.equals("meeting")){
                    String description = readFile.nextLine();
                    String location = readFile.nextLine();
                    Date date = new Date(readFile.nextLine());
                    Time time = new Time(readFile.nextLine());
                    String contact = readFile.nextLine();
                    int guest = readFile.nextInt();
                    events[count] = new Meeting(description, location, date, time, contact, guest);
                    count++;
                }else if(typeOfFile.equals("appointment")){
                    String description = readFile.nextLine();
                    String location = readFile.nextLine();
                    Date date = new Date(readFile.nextLine());
                    Time time = new Time(readFile.nextLine());
                    String contact = readFile.nextLine();
                    events[count] = new Appointment(description, location, date, time, contact);
                    count++;
                }
            }
            readFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(InvalidDateTimeException e){
            System.out.println("Cannot print from "+filename);
        }
    }
    /***
	 * Method for saving the data in the array events to a file filename; Also closes the file when done
	 * @param   filename is the name of the file;
	 * no return value
     */ 
    public void saveEvents(String filename){
        File file = new File(filename);
        int i = 0;
        try{
            
            PrintWriter writeToFile = new PrintWriter(file);
            while(i < count){
                if(events[i] instanceof Meeting){
                    if(i!=0){
                        writeToFile.println();
                    }
                    writeToFile.println("meeting");
                    writeToFile.println(events[i].getDescription());
                    writeToFile.println(events[i].getLocation());
                    writeToFile.println(events[i].getDate().toString());
                    writeToFile.println(events[i].getTime().toString());
                    writeToFile.println(((Meeting)events[i]).getHost());
                    writeToFile.print(((Meeting)events[i]).getGuest());
                    i++;
                }else{
                    if(i!=0){
                        writeToFile.println();
                    }
                    writeToFile.println("appointment");
                    writeToFile.println(events[i].getDescription());
                    writeToFile.println(events[i].getLocation());
                    writeToFile.println(events[i].getDate().toString());
                    writeToFile.println(events[i].getTime().toString());
                    writeToFile.print(((Appointment)events[i]).getContact());
                    i++;
                }
            }
            writeToFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to " + filename);
        }
    }
    
    /***
	 * Method for searching through the array events for a event with a desired description
	 * @param   description is the user entered description the method searches for
	 * @return  a singular event with that found description when found, or a empty appointment
     *          object that the main handles when returned by checking its description == "none";
	 */
    public Event findEvent(String description){
        for(int i = 0; i < count; i++){
            if(events[i].getDescription().equals(description)){
                
                return events[i];
            }
        }
        Event e = new Appointment();
        return e;
    }
    /***
	 * Method for searching through the array events for all the events with the desired date
	 * @param   date is the user entered date the method searches for
	 * @return  an array of events with shallow copies of all the events with matching dates, or a empty appointment
     *          in an array of length 1 that the main handles when returned by checking its description == "none";
	 */
    public Event[] findEvents(String date){
        int numOfMatches = 0;
        for(int i = 0; i < count; i++){
            //System.out.println(events[i].getDate().toString());
            if((events[i].getDate().toString()).equals(date)){
                numOfMatches++;
            }
        }
        //System.out.println(numOfMatches);
        if(numOfMatches == 0){
            Event[] e = new Event[1];
            e[0] = new Appointment();//default constructor... description = "none";
            return e; 
        }
        Event[] e = new Event[numOfMatches];
        int j = 0;
        for(int i = 0; i < count; i++){
            if((events[i].getDate().toString()).equals(date)){
                e[j] = events[i];
                j++;
            }
        }
        return e;
    }
    /***
	 * Method for adding events to array of the calendar
	 * @param   Event is add to the array
	 * @return  true if it was added and false if the array is full and therefore was not added
	 */
    public boolean addEvent(Event e){
        if(events.length > count){
            events[count] = e;
            count++;
            return true;
        }else{
            return false;
        }
    }
    /***
	 * Method for removing events in the array of the calendar
	 * @param   description ... if an event has a description that matches this, that event will be removed
	 * @return  true if it was removed and false if no event was found;
	 */
    public boolean removeEvent(String description){
        for(int i = 0; i < count; i++){
            if((events[i].getDescription()).equals(description)){
                for(int j = i; j < count-1; j++){
                    events[j] = events[j+1];
                }
                count--;
                return true;
            }
        }
        return false;
    }
    /***
	 * Method for printing the data in the array 
	 * no param
	 * no return value
	 */
    public void viewEvents(){
        System.out.printf("%-15s%-20s%-22s%-15s%-10s%-20s%-10s\n\n", "Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests");
        for(int i = 0; i < count; i++){
            if(events[i] instanceof Meeting){
                Meeting m = (Meeting)events[i];
                System.out.printf("%-15s%-20s%-22s%-15s%-10s%-20s%-10s\n", "Meeting", m.getDescription(), m.getLocation(), m.getDate(), m.getTime(), m.getHost(), m.getGuest());
            }else{
                Appointment m = (Appointment)events[i];
                System.out.printf("%-15s%-20s%-22s%-15s%-10s%-20s\n", "Appointment", m.getDescription(), m.getLocation(), m.getDate(), m.getTime(), m.getContact());
            }
        }
        System.out.println();
    }
    
    /***
	 * Method for sorting the events in calendar in chronological order
	 * no param
	 * no return value
	 */
    public void sortEvents(){
        java.util.Arrays.sort(events, 0, count);
    }
    
}