/***
 * Class to model the class Stack
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 23, 2023
 * Last Date Modified: March 23, 2023
 */
import java.util.Comparator;
public class Stack<E>{
    //data member
    private ArrayList<E> elements;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes stack to basic arraylist 
	 */
    public Stack(){
        elements = new ArrayList<>(); // creates an empty stack with capacity 10
    }
    //O(1)
    /***
     * Method for adding item to top of the stack
     * @param   item is the item that is added
     * no return value
     */
    public void push(E item){
        elements.add(item);// add at the end of elements (top of the stack)
    }
    //O(1)
    /***
     * Method for seeing what is at the top of a stack
     * no param
     * @return the object at the top
     */
    public E peek(){
        int lastIndex = elements.size()-1; // get the index of the top
        return elements.get(lastIndex); // return the value at the top
    }
    //O(1)
    /***
     * method for taking top item off the stack
     * no param
     * @return the removed obj
     */
    public E pop(){
        int lastIndex = elements.size()-1; // get the index of the top
        E value =  elements.get(lastIndex);// get the value of the top
        elements.remove(lastIndex); // remove the top
        return value;
    }
    //O(1)
    /***
     * Method for checking if the stack is empty
     * no param
     * @return T/F if the it is empty or not
     */
    public boolean isEmpty(){
        return elements.isEmpty();
    }
    //O(1)
    /***
     * Getter for size
     * no param
     * @return the size
     */
    public int size(){
        return elements.size();
    }
    //O(1)
    /***
     * Method to get the information from stack
     * no param
     * @return stick with all the information
     */
    public String toString(){
        return "Stack: " + elements.toString();
    }
    //O(n)
    /***
     * Method to see if a list contains an object
     * @param o holds the object
     * @return boolean value T/F if it is in the array
     */
    public boolean contains(Object o){ 
        return elements.contains(o);
    }
    // O(n^2)
    /***
     * Method to sort the elements in the arraylist
     * no param
     * no return value
     */
    public void sort(){
        elements.sort();
    }
    // O(n^2)
    /***
     * Method to sort the elements by a comparator object
     * @param c is the comparator
     * no return value
     */
    public void sort(Comparator<E> c){
        elements.sort(c);
    }
}