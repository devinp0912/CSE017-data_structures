/***
 * Class to model the class and subclasses of Event through polymorphism
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 1, 2023
 * Last Date Modified: February 1, 2023 
 */

import java.util.Scanner;

public class Test{
    public static void main(String[]args){
        Scanner keyboard = new Scanner(System.in);
        //create and initlize an array of type Event with subclasses
        Event[] list = new Event[10];
        list[0] = new Appointment("Dentist", "Lehigh Dentists", "01/26/2023", "10:30", "Beth Clark");
        list[1] = new Meeting("Science Club", "PA-220", "02/03/2023", "16:30", "Will Johns", 25);
        list[2] = new Meeting("Movie Club", "PA-220", "01/31/2023", "17:00", "Grace Williams", 10);
        list[3] = new Appointment("Instructor", "PA-254", "01/30/2023", "11:15", "Mark Jones");
        list[4] = new Meeting("Provost", "Memorial Building", "05/05/2023", "14:30", "Lisa Zuppe", 100);
        list[5] = new Meeting("Group work", "Zoom", "02/07/2023", "18:45", "Jack Taylor", 5);
        list[6] = new Appointment("Doctor", "Lehigh Doctors", "04/22/2023", "13:45", "Kathy Bell");
        list[7] = new Meeting("Programming Club", "PA-220", "03/15/2023", "19:00", "Billy Steward", 20);
        list[8] = new Appointment("Advising", "PA-252", "03/11/2023", "12:15", "Sharon Kraft");
        list[9] = new Appointment("Bank Account", "Wells Fargo", "03/25/2023", "10:30", "Sarah Thomson");

        //create menu for user to interact with array above
        boolean bringBackUpMenu = true;
        int choice = 0;
        do{
            //Display menu
            try{
            System.out.println("Select an opertion:");
            System.out.println("1: view all events");
            System.out.println("2: search event by description");
            System.out.println("3: search eventS by location");
            System.out.println("4: sort");
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
                printEvents(list);
            //2
            }else if(choice == 2){
                System.out.println("Enter a description");
                String descr = keyboard.nextLine();
                int index = findEvent(list, descr);
                if(index == -1){
                    System.out.println("No event found with description:" + descr);
                }else{
                    String out = list[index].toString();
                    System.out.printf("Event found:\n%s", out);
                }
            //3
            }else if(choice == 3){
                System.out.println("Enter a location");
                String location = keyboard.nextLine();
                Event[] locationList = findEvents(list, location);
                if((locationList[0].getDescription()).equals("none")){
                    System.out.println("No event found at " + location);
                }else{
                    System.out.println(locationList.length + " events found at " + location);
                    printEvents(locationList);
                }
            //4
            }else if(choice == 4){
                sortEvents(list);
                printEvents(list);
            //5
            }else if(choice == 5){
                bringBackUpMenu = false;
            }
        }while(bringBackUpMenu == true);
    
    }
    /***
     * Method for printing the elements in an array of type event
     * @param   list for holding the reference to an event array
     * no return value, just string printed out to terminal
     */
    public static void printEvents(Event[] list){
        for(Event e: list){
            System.out.println(e);
        }
    }
    /***
     * Method for sorting the elements in an array of type event in order by location
     * @param   list for holding the reference to an event array
     * no return value, the array is passed by reference here
     */
    public static void sortEvents(Event[] list){
        for(int i=1; i<list.length; i++){
            int j=i;
            String currentVal = list[i].getLocation();
            Event currentEvent = list[i];
            while(j>0 && currentVal.compareTo(list[j-1].getLocation())<0){
                list[j] = list[j-1];
                j--;
            }
            list[j] = currentEvent;
        }
    }
    /***
     * Method looks for an event with a matching description 
     * @param   Event holds the reference to an an event array
     * @param   descr holds the description string entered by the user
     * @return the index of the event that has a matching description or -1 if there was not one found
     */ 
    public static int findEvent(Event[] list, String descr){
        for(int i = 0; i < list.length; i++){
            if(list[i].getDescription().equals(descr)){
                return i;
            }
        }
        return -1;
    }
    /***
     * Method takes events from an array that have a desired searched for location and moves them into another array that gets returned
     * @param   list holds the reference to an Event array
     * @param   location for the location entered by the user that is searched for
     * @return a list with only the events that have the location that was searched for
     */
    public static Event[] findEvents(Event[]list, String location){
        int length = 0;
        for(int i = 0; i < list.length; i++){
            if(list[i].getLocation().equals(location)){
                length++;
            }
        }
        if(length == 0){
            Event[] loserList = new Event[1];
            loserList[0] = new Event();
            return loserList;
        }
        
        Event[] locoList = new Event[length];
       
        int j = 0;
        for(int i = 0; i < list.length; i++){
            if(list[i].getLocation().equals(location)){
                locoList[j] = list[i];
                j++;
            }
        }
        return locoList;
    }
}