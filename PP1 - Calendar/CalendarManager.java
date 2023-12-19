/***
 * Test Class to model the functionality of Calendar and related classes and interfaces
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 23, 2023
 * Last Date Modified: February 26, 2023 
 */
import java.util.Scanner;
import java.util.InputMismatchException;
 
public class CalendarManager{
    public static void main(String[]args){
        Scanner keyboard = new Scanner(System.in);
        Calendar calendar = new Calendar("events.txt");
        //create menu for user to interact with array above
        boolean bringBackUpMenu = true;
        int choice = 0;
        do{
            try{
                //display menu
                System.out.println("Select an operation:");
                System.out.println("1: view all events");
                System.out.println("2: search event by description");
                System.out.println("3: search events by date");
                System.out.println("4: add a new event");
                System.out.println("5: remove an existing event");
                System.out.println("6: sort events by date and time");
                System.out.println("7: exit");
                choice = keyboard.nextInt();
                //decifer choice
                switch(choice){
                    case 1: //print the array out
                        calendar.viewEvents();
                        break;
                    case 2: //search for an occurance of a event with a user entered description 
                        keyboard.nextLine();
                        System.out.println("Enter a description:");
                        String d = keyboard.nextLine();
                        Event e = calendar.findEvent(d);
                        if(e.getDescription() == "none"){
                            System.out.println("No event with description " + d + " was found.");
                        }else{
                            System.out.printf("Event found: %s\n", e.toString());
                        }
                        break;
                    case 3: //search for all events that occur on a certain date
                        keyboard.nextLine();
                        System.out.println("Enter a date:");
                        String mmddyyyy = keyboard.nextLine();
                        Event[] eventList = calendar.findEvents(mmddyyyy);
                        if(eventList.length == 1){
                            if((eventList[0].getDescription()).equals("none")){
                                System.out.println("No events found.");
                                break;
                            }
                        }
                        System.out.println(eventList.length + " events found:");
                        for(int i = 0; i < eventList.length; i++){
                            System.out.print(eventList[i].toString());
                        }
                        System.out.println();
                        break;
                    case 4: // add a new event
                        keyboard.nextLine();
                        System.out.println("Enter the type (appointment/meeting):");
                        String type = keyboard.nextLine();
                        try{
                            if(type.equals("meeting")){
                                System.out.println("Enter the description:");
                                String description = keyboard.nextLine();
                                System.out.println("Enter the location:");
                                String location = keyboard.nextLine();
                                System.out.println("Enter the date (mm/dd/yyyy):");
                                String date = keyboard.nextLine();
                                Date actualDate = new Date(date);
                                System.out.println("Enter the time (hh:mm):");
                                String time = keyboard.nextLine();
                                Time actualTime= new Time(time);
                                System.out.println("Enter the name of the host:");
                                String host = keyboard.nextLine();
                                System.out.println("Enter the # of guests:");
                                int guest = keyboard.nextInt();
                                Event e1 = new Meeting(description, location, actualDate, actualTime, host, guest);
                                if(calendar.addEvent(e1)){
                                    System.out.printf("Event added successfully.\n\n");
                                }else{
                                    System.out.printf("Calendar full. Event could not be added.\n\n");
                                }
                                System.out.println();
                            }else if(type.equals("appointment")){
                                System.out.println("Enter the description:");
                                String description = keyboard.nextLine();
                                System.out.println("Enter the location:");
                                String location = keyboard.nextLine();
                                System.out.println("Enter the date (mm/dd/yyyy):");
                                String date = keyboard.nextLine();
                                Date actualDate = new Date(date);
                                System.out.println("Enter the time (hh:mm):");
                                String time = keyboard.nextLine();
                                Time actualTime = new Time(time);
                                System.out.println("Enter the name of the contact:");
                                String contact = keyboard.nextLine();
                                Event e2 = new Appointment(description, location, actualDate, actualTime, contact);
                                if(calendar.addEvent(e2)){
                                    System.out.printf("Event added successfully.\n\n");
                                }else{
                                    System.out.printf("Calendar full. Event could not be added.\n\n");
                                }
                            }else{
                                throw new Exception("Not a valid type");
                            }
                        }catch(InvalidDateTimeException error){
                            error.getMessage();
                        }catch(Exception error2){
                            System.out.println("not a valid type");
                        }
                        break;
                    case 5://remove an event by matching description
                        keyboard.nextLine();
                        System.out.println("Enter a description:");
                        String description = keyboard.nextLine();
                        if(calendar.removeEvent(description)){
                            System.out.println("Event found and removed successfully");
                        }else{
                            System.out.println("Event not found.");
                        }
                        break;
                    case 6://sort by date
                        calendar.sortEvents();
                        break;
                    case 7://safe the events
                        calendar.saveEvents("events.txt");
                        bringBackUpMenu = false;
                        break;
                    default:
                        throw new InputMismatchException(); 
                }
            }
            catch(InputMismatchException e){
                System.out.printf("You did not enter a valid choice\n\n");
                keyboard.nextLine();
            }
            
        }while(bringBackUpMenu == true);
        keyboard.close();
    }
}