/***
 * Class to model the class ArrayList
 * @author GIVE TO US FROM COURSE SITE 
 * @version 0.1
 * Date of creation: March 30, 2023
 * Last Date Modified: March 30, 2023
 */

 ////////// I didn't write any of these methods, it was given to us
import java.util.Iterator;
import java.util.Comparator;

public class ArrayList<E> {
   // data members
   private E[] elements;
   private int size;
   public static int containsIter, addIter, removeIter;
   // Constructors
   public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
   }
   public ArrayList(int capacity) {
     elements = (E[]) new Object[capacity];
     size = 0;
   }
   // Adding an item to the list (2 methods)
    public boolean add(E item) {
		  return add(size, item);
    }
    // Adding an item to the list at index
    public boolean add(int index, E item){
          addIter = 0;
		  if(index > size || index < 0)
			  throw new ArrayIndexOutOfBoundsException();
		  ensureCapacity();
		  for(int i=size-1; i>=index; i--){
              addIter++;
			  elements[i+1] = elements[i];
          }
		  elements[index] = item;
		  size++;
		  return true;
    }

    // Getter and Setter
    public E get(int index) {
		  checkIndex(index);
		  return elements[index];
    }
    public E set(int index, E item) {
		  checkIndex(index);
		  E oldItem = elements[index];
		  elements[index] = item;
		  return oldItem;
    }
    // Size of the list
    public int size() {
      return size;
    }
    // Clear the list
    public void clear() {
      size = 0;
    }
    // Check if the list is empty
    public boolean isEmpty() {
      return (size == 0);
    }

    // Removing an object from the list
    public boolean remove(Object o) {
      E item = (E) o; // casting down o to type E
      removeIter = 0;
      for(int i=0; i<size; i++){
         removeIter++;
		    if(elements[i].equals(item)){
                
            remove(i);
            return true;
        }
      }
      return false;
    }

    // Removing the item at index from the list
    public E remove(int index) {
      checkIndex(index);
      E item = elements[index];
      for(int i=index; i<size-1; i++){
                removeIter++;
			    elements[i] = elements[i+1];
      }
      size--;
      return item;
    }

    // Shrink the list to size
    public void trimToSize() {
		  if (size != elements.length) {
			    E[] newElements = (E[]) new Object[size];// capacity = size
			    for(int i=0; i<size; i++)
				    newElements[i] = elements[i];
			    elements = newElements;
		  }
    }
    // Grow the list if needed
    private void ensureCapacity() {
        addIter = 0;
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++){
                    addIter++;
				    newElements[i] = elements[i];
          }
		      elements = newElements;
	    }
    }
    // Check if the index is valid
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
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

    // Method contains
    // O(n)
    public boolean contains(Object o){
        containsIter = 0;
        E value = (E) o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIter++;
            if(iter.next().equals(value))
            {
                return true;
            }
        }
        return false;
    }
}