/***
 * Class to model the entity ComparatorByEmail which implements the comparator interface
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
import java.util.Comparator;

public class ComparatorByEmail implements Comparator<Contact>{
    /***
     * method that compares contacts by email
     * @param   c1 holds a contact object
     * @param   c2 holds a contact object
     * @return  an int value from the string compareTo called in the body in the body of the method indicating 
     *          which string is greater
     */
    public int compare(Contact c1, Contact c2){
        String e1 = c1.getEmail();
        String e2 = c2.getEmail();
        return e1.compareTo(e2);
    }
}