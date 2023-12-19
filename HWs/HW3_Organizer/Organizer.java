/***
 * Class to model the entity Organizer
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
import java.util.ArrayList;
import java.util.Comparator;

public class Organizer<E extends Comparable<E>>{
    //data members
    private ArrayList<E> elements;
    private Comparator<E> comparator;
    /***
    
    Big-0 notation for method below ---->   0(1)
    
     */
    /***
     * constructor with 1 parameters
     * @param   cap holds length of arraylist
     * sets data member comparator to null
     */
    public Organizer(int cap){
        elements = new ArrayList<>(cap);
        comparator = null;
    }
    /***
    
    Big-0 notation for method below ---->   O(1)
    
     */
    /***
     * constructor with 2 parameters
     * @param   cap holds length of arraylist
     * @param   comp holds a comparator 
     */
    public Organizer(int cap, Comparator<E> comp){
        elements = new ArrayList<>(cap);
        comparator = comp;
    }
    /***
    
    Big-0 notation for method below ---->   ARRAYS.SORT has n log(n) complexity
                                        =   0(n log n)
    
     */
    /***
	 * Method to add objects to the arraylist
	 * @param   e holds a contact or note object 
	 * no return value
	 */
    public void addElement(E e){
        elements.add(e);
        elements.sort(comparator);
    }
    /***
    
    Big-0 notation for method below ----> binary search time complexity O(log n)
    
     */
    /***
	 * Method to that searches for objects: using recursive binary search
	 * @param   e holds a contact or note object 
	 * @return  an object of contact or note that is either null, if a match wasn't found, the it returns 
     *          the object with all the information in it
	 */
    public E findElement(E e){
        //find the number of elements
        int count = elements.size();
        //start finding the element
        int mid, low, high;
        Organizer<E> newList = new Organizer<>(count);
        int keyIndex = -1;
        low = 0;
        high = count;
        
            mid = (high + low)/2;
            E currentEl = elements.get(mid);
            //System.out.println("currentEl " +currentEl);
            if(count > 1 && currentEl.compareTo(e) < 0){
                //create new list with the new elements that need to be searched
                for(int i = mid; i < high; i++){
                    newList.addElement(elements.get(i));
                }
                //System.out.println(newList);
                //recursively call the method
                return newList.findElement(e);
            }else if(count > 1 && currentEl.compareTo(e) > 0){
                for(int i = 0; i < mid; i++){
                    newList.addElement(elements.get(i));
                }
                //System.out.println(newList);
                return newList.findElement(e);
            }else if(currentEl.compareTo(e) == 0){
                return currentEl;
            }
        return null;
    }
    /***
    
    Big-0 notation for method below ---->   = O(n) ... for loop has n time complexity
    
     */
    /***
	 * Method to remove objects to the arraylist
	 * @param   e holds a contact or note object 
	 * @return  the object that got removed
	 */
    public E removeElement(E e){
        E temp = null;
        for(int i = 0; i < elements.size(); i++){
            E currentEl = elements.get(i);
            if(currentEl != null){
                if(currentEl.equals(e)){
                    temp = currentEl;
                    elements.remove(i);
                    return temp;
                }
            }
        }
        return temp;

    }
    /***
    
    Big-0 notation for method below ---->   ARRAYS.SORT has n log(n) complexity
                                        =   0(n log n)
    
     */
    /***
	 * Setter for the comparator of a organizer
	 * @param	comp holds a comparator object
	 * no return value
	 */
    public void setComparator(Comparator<E> comp){
        this.comparator = comp;
        elements.sort(comparator);
    }
    /***
    
    Big-0 notation for method below ---->   = O(n) ... for loop has n time complexity
    
     */
    /***
	 * Method to get the information in an Organizer object
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String returnMe = "";
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i) != null){
                //System.out.println(elements.get(i).toString());
                returnMe += String.format("%s\n", elements.get(i).toString());
            }
        }
        return returnMe;
    }
}

