/***
 * Class to model the class and subclasses of Event
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 1, 2023
 * Last Date Modified: February 8, 2023 
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Test{
    public static void main(String[]args) throws InvalidDateTimeException{
        Scanner keyboard = new Scanner(System.in);
        Event[] events = new Event[50];
        //inilized the elements of events
        int numOfNulls = readEvents(events, "events.txt");
        //create menu for user to interact with array above
        boolean bringBackUpMenu = true;
        int choice = 0;
        do{
            //Display menu
            try{
                System.out.println("Select an opertion:");
                System.out.println("1: view all events");
                System.out.println("2: search event by description");
                System.out.println("3: search eventS by date");
                System.out.println("4: sort by date and time");
                System.out.println("5: exit");
                choice = keyboard.nextInt();
                //input validation
                if(choice < 1 || choice > 5){
                    throw new Exception("number out of bounds");
                }
                keyboard.nextLine();
            }
            catch(Exception e){
                System.out.println("You did not enter a valid number");
                keyboard.nextLine();
            }
            //decipher input
            //1
            if(choice == 1){
                printEvents(events, numOfNulls);
            //2
            }else if(choice == 2){
                System.out.println("Enter a description");
                String descr = keyboard.nextLine();
                int index = findEvent(events, descr, numOfNulls);
                if(index == -1){
                    System.out.println("No event found with description:" + descr);
                }else{
                    String out = events[index].toString();
                    System.out.printf("Event found:\n%s", out);
                }
            //3
            }else if(choice == 3){
                System.out.println("Enter a date - format mm/dd/yyyy:");
                try{
                    String date = keyboard.nextLine();
                    Event[] dateList = findEvents(events, date, numOfNulls);
                    String elementOne = dateList[0].getDescription();
                    if(elementOne.equals("none")){
                        System.out.println("No event found at " + date);
                    }else{
                        System.out.println(dateList.length + " events found at " + date);
                        printEvents(dateList, dateList.length);
                    }
                }
                catch(InvalidDateTimeException e){
                    System.out.println(e.getMessage());
                }
            //4
            }else if(choice == 4){
                sortEvents(events, numOfNulls);
                printEvents(events, numOfNulls);
            //5
            }else if(choice == 5){
                bringBackUpMenu = false;
            }
        }while(bringBackUpMenu == true);
    
    }

    /***
     * Method for printing the elements in an array of type event
     * @param   list for holding the reference to an event array
     * @param   numOfNulls holds the value of populated elements in the Event[]list
     * no return value, just string printed out to terminal
     */
    public static void printEvents(Event[] list, int numOfNulls){
        String headline = ("Type\t\tDescription\tLocation\tDate\t\tTime\tContact/Host\t\tGuests\n");
        System.out.println(headline);
        for(int i = 0; i < numOfNulls; i++){
            if(list[i] instanceof Meeting){
                System.out.printf("Meeting\t\t");
            }else{
                System.out.printf("Appointment\t");
            }
            Event e = list[i]; 
            System.out.println(e);
        }
    }

    /***
     * Method for sorting the elements in an array of type event in order by date and them time
     * @param   list for holding the reference to an event array
     * @param   numOfNulls holds the value of populated elements in the Event[]list
     * no return value, the array is passed by reference here
     */
    public static void sortEvents(Event[] list, int numOfNulls){
        //sort by date
        for(int i=1; i<numOfNulls; i++){
            int j=i;
            Date currentVal = list[i].getDate();
            Event currentEvent = list[i];
            while(j>0 && (currentVal.toString()).compareTo((list[j-1].getDate()).toString())<0){
                list[j] = list[j-1];
                j--;
            }
            list[j] = currentEvent;
        }
        //loop over again and now sort by time
        for(int i=1; i<numOfNulls; i++){
            int j=i;
            Date currentVal = list[i].getDate();
            Event currentEvent = list[i];
            //check if the dates are equal
            if(j>0 && (currentVal.toString()).equals((list[j-1].getDate()).toString())){
                String currentTime = list[i].getTime().toString();
                //if they are equal, see which time is greater
                while(j>0 && currentTime.compareTo(list[j-1].getTime().toString())<0){
                    list[j] = list[j-1];
                    j--;
                }
            }
            list[j] = currentEvent;
        
        }
    }

    /***
     * Method looks for an event with a matching description 
     * @param   Event holds the reference to an an event array
     * @param   descr holds the description string entered by the user
     * @param   numOfNulls holds the value of populated elements in the Event[]list
     * @return the index of the event that has a matching description or -1 if there was not one found
     */ 
    public static int findEvent(Event[] list, String descr, int numOfNulls){
        for(int i = 0; i < numOfNulls; i++){
            if(list[i].getDescription().equals(descr)){
                return i;
            }
        }
        return -1;
    }

    /***
     * Method takes events from an array that have a desired searched for date and moves them into another array that gets returned
     * @param   list holds the reference to an Event array
     * @param   date for the date entered by the user that is searched for
     * @param   numOfNulls holds the value of populated elements in the Event[]list
     * @return a list with only the events that have the date that was searched for
     */
    public static Event[] findEvents(Event[]list, String date, int numOfNulls) throws InvalidDateTimeException{
        int length = 0;
        //check if date is correct format
        Date dCompareMe = new Date(date);
        //find out how many dates exist that match... store in length
        for(int i = 0; i < numOfNulls; i++){
            Date date1 = list[i].getDate();

            if(date1.toString().equals(dCompareMe.toString())){
                length++;
            }
        }
        //if no matches exist return empty array
        if(length == 0){
            Event[] loserList = new Event[1];
            loserList[0] = new Event();
            return loserList;
        }
        //if matches exist create array of length length
        Event[] dateList = new Event[length];
       
       //find the matches and add that to the dateList
        int j = 0;
        for(int i = 0; i < numOfNulls; i++){
            Date date1 = list[i].getDate();
            if(date1.toString().equals(dCompareMe.toString())){
                dateList[j] = list[i];
                j++;
            }
        }
        return dateList;
    }
    /***
     * Method looks for a file and looks to add the information in that file to an Event array. 
     * @param   list holds the reference to an Event array
     * @param   filename holds the name of a file this method reads
     * @return  the number of elements populated in the array
     */
    public static int readEvents(Event[]list, String filename){
        File file = new File(filename);
        int count = 0;
        
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
                    list[count] = new Meeting(description, location, date, time, contact, guest);
                    count++;
                }else if(typeOfFile.equals("appointment")){
                    String description = readFile.nextLine();
                    String location = readFile.nextLine();
                    Date date = new Date(readFile.nextLine());
                    Time time = new Time(readFile.nextLine());
                    String contact = readFile.nextLine();
                    list[count] = new Appointment(description, location, date, time, contact);
                    count++;
                }
            }
            readFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(InvalidDateTimeException e){
            System.out.println(e.getMessage());
        }
        return count;

    }
}

