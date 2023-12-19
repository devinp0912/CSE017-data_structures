/***
 * Class to model the entity InvalidSeatException which extends from the super calss Exception
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 2, 2023
 * Last Date Modified: Febuary 6, 2023
 */
public class InvalidSeatException extends Exception{
    /***
	 * default constructor
	 * no parameters
	 * no return value
	 */
    public InvalidSeatException(){
        super("Invalid Seat Exception");
    }
    /***
	 * constructor with different message
	 * @param message holds the message to be printed
	 * no return value
	 */
    public InvalidSeatException(String message){
        super(message);
    }
}