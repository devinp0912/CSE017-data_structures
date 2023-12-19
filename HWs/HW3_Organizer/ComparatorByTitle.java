/***
 * Class to model the entity ComparatorByTitle which implements the comparator interface
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
import java.util.Comparator;

public class ComparatorByTitle implements Comparator<Note>{
    /***
     * method that compares notes by title
     * @param   n1 holds a note object
     * @param   n2 holds a note object
     * @return  an int value from the string compareTo called in the body in the body of the method indicating 
     *          which string is greater
     */
    public int compare(Note n1, Note n2){ 
        String t1 = n1.getTitle();
        String t2 = n2.getTitle();
        return t1.compareTo(t2);
    }
}