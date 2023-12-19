/***
 * Class to model the entity Flight
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 29, 2023
 * Last Date Modified: March 30, 2023 
 */
public class Flight{
    //data members
    private String flight;
    private Time departure;
    private Time arrival;
    /***
     * Default constructor with 3 param
     * @param   flight holds the NAME 
     * @param   departure holds the departure time
     * @param   arrival holds the arrival time
     */
    public Flight(String flight, Time departure, Time arrival){
        this.flight = flight;
        this.departure = departure;
        this.arrival = arrival;
    }
    /***
     * Getter method for flight
     * no param
     * @return data field flight
     */
    public String getFlight(){
        return flight;
    }
    /***
     * Getter method for departure time
     * no param
     * @return data field departure time
     */
    public Time getDeparture(){
        return departure;
    }
    /***
     * Getter method for arrival time
     * no param
     * @return data field arrival time
     */
    public Time getArrival(){
        return arrival;
    }
    /***
     * Setter method for flight
     * @param   flight holds the new flight
     * no return value
     */
    public void setFlight(String flight){
        this.flight = flight;
    }
    /***
     * Setter method for departure time
     * @param   departure holds the new departure time
     * no return value
     */
    public void setDeparture(Time departure){
        this.departure = departure;
    }
    /***
     * Setter method for arrival time
     * @param   arrival holds the new arrival time
     * no return value
     */
    public void setArrival(Time arrival){
        this.arrival = arrival;
    }
    /***
     * Method to get the information from the flight object
     * no param
     * @return a formatted string with the flight objects info
     */
    public String toString(){
        return String.format("%-15s%-15s%-15s", flight, departure.toString(), arrival.toString());
    }
}