/***
 * Class to model the class Priority Queue
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 23, 2023
 * Last Date Modified: March 23, 2023
 */
import java.util.Comparator;
public class PriorityQueue<E> {
    //data members
   private ArrayList<E> list;
   private Comparator<E> comparator;
   //O(1)
   /***
	 * Default constructor
	 * No parameters
	 * Initializes stack to basic arraylist 
	 */
   public PriorityQueue() { // using compareTo for the priority O(1)
     list = new ArrayList<>();
     comparator = null;
   }
   //O(1)
   /***
     * Constructor with 1 arg
     * @param   c is comparator obj
     */
   public PriorityQueue(Comparator<E> c) { // using compare on the object comparator
      list = new ArrayList<>();
      comparator = c;
   }
   //O(1)
   /***
    * Method for getting the first item off the queue
    * no param
    * @return the obj
    */
   public E poll() { //O(n)
		E value = list.get(0);
		list.remove(0);
        return value;
   }
    //O(n)
   /***
    * Method for added an item to the queue and then sorting it
    * @param    item is the item being offered
    * no return value
    */
   public void offer(E item) { //O(n)
    int i, c;
    for(i=0; i<list.size(); i++){
        if(comparator == null)
            c = ((Comparable<E>)item).compareTo(list.get(i));
        else
	        c = comparator.compare(item, list.get(i));
	    if(c < 0)
            break;
    }
    list.add(i, item);
   }
   /***
    * Method for seeing the first item on the list
    * no param
    * @return the obj
    */
   public E peek() { //O(1)
      return list.get(0);
   }
   /***
    * Method to get the information from the list
    * no param
    * @return formatted string
    */
   public String toString() { //O(n)
		return "Priority Queue: " + list.toString();
   }
    /***
     * Getter for size
     * no param
     * @return the size
     */
   public int size() { //O(1)
    return list.size();
   }
   /***
    * Method to clear list
    * no param
    * no return value
    */
   public void clear() { //O(1)
    list.clear();
   }
   /***
    * method to check is the list is empty
    * no param
    * @return T/F if it is or isnt
    */
   public boolean isEmpty() { //O(1)
    return list.isEmpty();
   }
   //O(n)
   /***
     * Method to see if a list contains an object
     * @param o holds the object
     * @return boolean value T/F if it is in the array
     */
   public boolean contains(Object o){
        return list.contains(o);
    }
    // O(n^2)
    /***
     * Method to sort the elements in the arraylist
     * no param
     * no return value
     */
    public void sort(){
        list.sort();
    }
    // O(n^2)
    /***
     * Method to sort the elements by a comparator object
     * @param c is the comparator
     * no return value
     */
    public void sort(Comparator<E> c){
        list.sort(c);
        comparator = c;
    }
}