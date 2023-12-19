/***
 * Class to model the entity InvalidDateTimeException
 * @author Devin Pombo
 * @version 0.2
 * Date of creation: February 6, 2023
 * Last Date Modified: March 30, 2023 
 */
public class InvalidDateTimeException extends Exception{
    /***
     * default constructor 
     * no parameters
     * default message
     */
    public InvalidDateTimeException(){
        super("Invalid Date Format.");
    }
    /***
     * constructor with 1 parameters
     * @param   message passes message to print when a specific error occurs
     */
    public InvalidDateTimeException(String message){
        super(message);
    }
}