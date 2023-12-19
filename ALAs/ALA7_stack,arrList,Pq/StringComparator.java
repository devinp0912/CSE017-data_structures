/***
 * Class to model the class StringComparator
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 23, 2023
 * Last Date Modified: March 23, 2023
 */
import java.util.Comparator;

public class StringComparator implements Comparator<String>{
    /***
     * Method organizes strings based on their length
     * @param s1 holds the first string
     * @param s2 holds the second string
     * @return the difference between the 2 lengths
     */
    public int compare(String s1, String s2){
        return s1.length() - s2.length();
    }
}