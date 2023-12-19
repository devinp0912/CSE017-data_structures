/***
 * Class to model the entity InvalidDateTimeException
 * @author GIVEN TO US BY COURSESITE
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
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