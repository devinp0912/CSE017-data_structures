/***
 * Class to model the class ArrayList
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 23, 2023
 * Last Date Modified: March 23, 2023
 */
import java.util.Iterator;
import java.util.Comparator;
public class ArrayList<E> {
   // data members
   private E[] elements;
   private int size;
   public static int containsIter, sortIter, comparatorSortIter;
   //O(1)
   /*** 
	 * Default constructor
	 * No parameters
	 * Initializes arraylist to length ten and size to 0
	 */
   public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
   }
   //O(1)
   /***
	 * Constructor with one parameter
	 * @param	  capacity for the capacity of the array
	 */
   public ArrayList(int capacity) {
     elements = (E[]) new Object[capacity];
     size = 0;
   }
   //O(1)
    /***
     * Method for adding an item to the list
     * @param item holds the object to add to the array
     * return true if added
     */
    public boolean add(E item) {
		  return add(size, item);	// adding at the first free index
    }
    //O(n)
    /***
     * Method for adding an item to the list at index
     * @param index holds index to add item at
     * @param item holds the object to add to the array
     * @return true if added
     */
    public boolean add(int index, E item){
		  if(index > size || index < 0)
			  throw new ArrayIndexOutOfBoundsException();
		  ensureCapacity();
		  for(int i=size-1; i>=index; i--){
			  elements[i+1] = elements[i];
      }
		  elements[index] = item;
		  size++;
		  return true;
    }
    //O(1)
    /***
	   * Getter item in arraylist
	   * @param index holds where the item is 
	   * @return the item
	   */
    public E get(int index) {
		  checkIndex(index);
		  return elements[index];
    }
    //O(1)
    /***
     * Setter method
     * @param index where the item wants to be added
     * @param item the item to add their
     */
    public E set(int index, E item) {
		  checkIndex(index);
		  E oldItem = elements[index];
		  elements[index] = item;
		  return oldItem;
    }
    //O(1)
    /***
     * Getter for size
     * no param
     * @return size
     */
    public int size() {
      return size;
    }
    //O(1)
    /***
     * method to clear the list
     * no param
     * no return value
     */
    public void clear() {
      size = 0;
    }
    //O(1)
    // Check if the list is empty
    public boolean isEmpty() {
      return (size == 0);
    }
    //O(n)
    // Removing an object from the list
    public boolean remove(Object o) {
      E item = (E) o; // casting down o to type E
      for(int i=0; i<size; i++)
		    if(elements[i].equals(item)){
            remove(i);
            return true;
        }
      return false;
    }
    //O(n)
    // Removing the item at index from the list
    public E remove(int index) {
      checkIndex(index);
      E item = elements[index];
      for(int i=index; i<size-1; i++)
			  elements[i] = elements[i+1];
      size--;
      return item;
    }
    //O(n)
    // Shrink the list to size
    public void trimToSize() {
		  if (size != elements.length) {
			    E[] newElements = (E[]) new Object[size];// capacity = size
			    for(int i=0; i<size; i++)
				    newElements[i] = elements[i];
			    elements = newElements;
		  }
    }
    //O(n)
    // Grow the list if needed
    private int ensureCapacity() {
        int iterations = 0;
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++){
                    iterations++;
				    newElements[i] = elements[i];
              }
		      elements = newElements;
	    }
        return iterations;
    }
    //O(1)
    // Check if the index is valid
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
    //O(n)
    // toString() method
    public String toString() {
		  String output = "[";
		  for(int i=0; i<size-1; i++)
			    output += elements[i] + ", ";
		  output += elements[size-1] + "]";
		  return output;
    }
    // Iterator for the list
    public Iterator<E> iterator(){
		  return new ArrayIterator();
    }
    // Inner class that implements Iterator<E>
    private class ArrayIterator implements Iterator<E>{
	    private int current = -1;

	    public boolean hasNext() {
        return current < size-1;
      }

	    public E next() {
        return elements[++current];
      }
    } 
    // O(n)
    /***
     * Method to see if a list contains an object
     * @param o holds the object
     * @return boolean value T/F if it is in the array
     */
    public boolean contains(Object o){
        containsIter = 0;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIter++;
            if(iter.next().equals(o)){
                return true;
            }
        }
        return false;
    }   
    // O(n^2)
    /***
     * Method to sort the elements in the arraylist
     * no param
     * no return value
     */
    public void sort(){
        sortIter = 0;
        for(int i=0; i<size; i++){
            sortIter++;
            int minIndex = i;
            for(int j=i; j<size; j++){
                sortIter++;
                Comparable<E> min = (Comparable<E>) elements[minIndex];
                if(min.compareTo(elements[j]) > 0){
                    minIndex = j;
                }
            }
            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
    // O(n^2)
    /***
     * Method to sort the elements by a comparator object
     * @param c is the comparator
     * no return value
     */
    public void sort(Comparator<E> c){
        comparatorSortIter = 0;
        for(int i=0; i<size; i++){
            comparatorSortIter++;
            int minIndex = i;
            for(int j=i; j<size; j++){
                comparatorSortIter++;
                if(c.compare(elements[minIndex], elements[j]) > 0){
                    minIndex = j;
                }
            }
            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
}