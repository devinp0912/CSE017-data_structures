/***
 * Class to test the entitys time and flight
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 29, 2023
 * Last Date Modified: March 30, 2023 
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//this class acts like to handle the flights going in and out like a real airport does

/***

The rules of the airport:

1.) Only one plane can occupy the runway at a time

2.) it takes 7 minutes for a plane to takeoff once it leaves the queue
   it also takes 12 minutes for a plane to land and clear the runway once it leaves the queue

3.) Planes cannot land and takeoff at the same time, and multiple landing/takeoff requests can occur
    within the same minute

4.) landing requests are a higher priority than takeoff

*/


public class Airport{
    public static void main(String[]args){
        Queue<Flight> landingQueue = new LinkedList<>();
        Queue<Flight> takeoffQueue = new LinkedList<>();
        ArrayList<Flight> landingList = new ArrayList<>();
        ArrayList<Flight> takeoffList = new ArrayList<>();
        int landingTime = 12;
        int takeoffTime = 7;
        int runway = 0; 
        String landingOutput = "";
        String takeoffOutput = "";
        String flightLog = "";

        //average calculation variables
        int landingOffers = 0;
        int totalLandWaitTime = 0;
        int takeoffOffers = 0;
        int totalTakeWaitTime = 0;

        try{ 
            //set the current time
            Time currentTime = new Time("12:00"); 
            Time endTime = new Time("20:00");
            
            //read from the file and initalize the arraylists
            readFlights(landingList, "landing.txt");
            readFlights(takeoffList, "takeoff.txt");

            //while the current time does not equal the endTime of the simulation
            while(!currentTime.equals(endTime)){

                //search if a flight takes off or lands at the currentTime
                int indexOfLanding = findLanding(landingList, currentTime);
                //if a landing request was found at a certain time, add it to the queue
                //checking the list at the time because multiple landing requests can come in at the same time -- this is handled with the "while loop"
                while(indexOfLanding != -1){
                    landingQueue.offer(landingList.get(indexOfLanding));
                    //make sure a flight after it is added so you dont get an infinite loop
                    landingList.remove(indexOfLanding);
                    indexOfLanding = findLanding(landingList, currentTime);
                    flightLog += String.format("A landing request has been added to the landing queue at " + currentTime.toString() + "\n");
                }

                //repeat the same for the takeoff queue
                int indexOfTakeoff = findTakeoff(takeoffList, currentTime);
                while(indexOfTakeoff != -1){
                    takeoffQueue.offer(takeoffList.get(indexOfTakeoff));
                    takeoffList.remove(indexOfTakeoff);
                    indexOfTakeoff = findTakeoff(takeoffList, currentTime);
                    flightLog += String.format("A takeoff request has been added to the landing queue at " + currentTime.toString() + "\n");
                }
                
                //if runway isn't free decrement it
                if(runway != 0){
                    runway--;
                    //else the runway is free...
                }else{
                    //check landingQueue, and poll a flight 
                    if(landingQueue.peek() != null){
                        Flight currentlyReady = landingQueue.poll();
                        //determine its wait time
                        int difference = (currentlyReady.getArrival()).diff(currentTime);
                        //increment int varaibles for average caluclation;
                        landingOffers++;
                        totalLandWaitTime += difference;
                        //set runway to landingTime
                        runway = landingTime;
                        landingOutput += String.format("%s%-15s%-15d\n", currentlyReady.toString(), currentTime.toString(), difference);
                        

                    //check takeoff queue, and poll a flight
                    }else if(takeoffQueue.peek() != null){
                        Flight currentlyReady = takeoffQueue.poll();
                        //determine its wait time
                        int difference = (currentlyReady.getDeparture()).diff(currentTime);
                        
                        takeoffOffers++;
                        totalTakeWaitTime += difference;
                        //set runway to landingTime
                        runway = takeoffTime;//7
                        takeoffOutput += String.format("%s%-15s%-15d\n", currentlyReady.toString(), currentTime.toString(), difference);
                        
                    //else (if no takeoff or landing requests are in either of the queues)..... 
                        //do nothing and increment the time below
                    }   
                }
                //increment time
                currentTime.tick();
            }
        }
        catch(InvalidDateTimeException e){ 
            e.getMessage(); 
        }
        

        //after the simulation print the results in a formatted fashion 
        System.out.println(flightLog);
        System.out.println("\nLanding Report");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Flight", "Departure", "Arrival", "Arrived", "Waiting time");
        System.out.println(landingOutput);
        double averageLandWait = (totalLandWaitTime * 1.0) / landingOffers;
        System.out.println("Average landing wait time: " + averageLandWait);
        System.out.println("\nTakeoff Report");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Flight", "Departure", "Arrival", "Departed", "Waiting time");
        System.out.println(takeoffOutput);
        double averageTakeWait = (totalTakeWaitTime * 1.0) / takeoffOffers;
        System.out.println("Average landing wait time: " + averageTakeWait);
        
    }
    
    /***
     * Method to read the data from a file and add it to an arraylist
     * @param   list holds the reference to an arraylist
     * @param   filename holds the name of the file to read from
     * no return value
     */
    public static void readFlights(ArrayList<Flight> list, String filename){
        File file = new File(filename);
        int count = 0;
        try {
            Scanner readFile = new Scanner(file);//throws filenotfoundexception
            while(readFile.hasNext()) {
                String flightName = readFile.next();
                String departTime = readFile.next();
                String arriveTime = readFile.next();
                Time departure = new Time(departTime);
                Time arrival = new Time(arriveTime);
                Flight flight = new Flight(flightName, departure, arrival);
                list.add(count, flight);
                count++;
            }
            readFile.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Cannot open file \"" + filename + "\"");
        }
        catch(InvalidDateTimeException e){ 
            e.getMessage(); 
        }
    }
    
    /***
     * Method that checks to see if a landing requests exists in the arraylist at a particular time 
     * @param   list holds the reference to the list
     * @param   time holds the time that we are searching for
     * @return the index it was found
     */
    public static int findLanding(ArrayList<Flight> list, Time time){
        int whileloopbound = list.size();
        int index = 0;
        while(index < whileloopbound){
            Flight currentFlight = list.get(index);
            if((currentFlight.getArrival()).compareTo(time) == 0){//if the 2 times are equal
                return index;
            }
            index++;
        }
        return -1;
    }
    /***
     * Method that checks to see if a takeoff requests exists in the arraylist at a particular time 
     * @param   list holds the reference to the list
     * @param   time holds the time that we are searching for
     * @return the index it was found
     */
    public static int findTakeoff(ArrayList<Flight> list, Time time){
        int whileloopbound = list.size();
        int index = 0;
        while(index < whileloopbound){
            Flight currentFlight = list.get(index);
            if((currentFlight.getDeparture()).compareTo(time) == 0){//if the 2 times are equal
                return index;
            }
            index++;
        }
        return -1;
    }
    
}