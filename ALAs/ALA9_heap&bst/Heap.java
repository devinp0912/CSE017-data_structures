/***
 * Class to model the entity Heap
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 13 2023
 * Last Date Modified: April 13 2023
 */
import java.util.ArrayList;
/**
    Generic class that implements the data structure Heap
*/
public class Heap<E extends Comparable<E>> {
    // data member: list of the heap nodes
    private ArrayList<E> list;
    public static int iterations;
    /**
        Default constructor to create an empty heap
        Time complexity: O(1)
    */
    public Heap(){
        list = new ArrayList<>();
    }
    /**
        Method to get the size of the heap
        @return the number of nodes in the heap
        Time complexity: O(1)
    */
    public int size(){ 
        return list.size(); 
    }
    /**
        Method to check if the heap is empty
        @return true if the heap is empty, false otherwise
        Time complexity: O(1)
    */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
        Method to clear the heap
        Time complexity: O(1)
    */
    public void clear(){
        list.clear(); 
    }
    /**
        Method to get the values stored in the heap as a string
        @return formatted string with the values of all the nodes in the heap
        Time complexity: O(n)
    */
    public String toString(){
        return list.toString();
    }
    /**
        Method to search for a value in the heap
        @param value to be searched in the heap
        @return true if value was found in the heap, false otherwise
        Time complexity: O(n)
    */
    public boolean contains(E value) {
        iterations = 0;
        for(int i=0; i<list.size(); i++) {
            iterations++;
            if(list.get(i).equals(value))
                return true;
        }
        return false;
    }
    /**
        Method to add a new value in the heap
        @param value to be added to the heap
        Time complexity: O(logn)
    */
    public void add(E value) {
        list.add(value);// add value at the end of the list
        int currentIndex = list.size()-1; // index of the added node
        iterations = 0;
        while(currentIndex > 0) {
            iterations++;
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            if(current.compareTo(parent) > 0) { // need to swap
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else
                break;
            currentIndex = parentIndex;
        }
    }
    /**
        Method to remove the root node from the heap
        @return the value of the root (the largest value in the heap)
        Time complexity: O(logn)
    */
    public E remove() {
        if(list.size() == 0) 
            return null;
        E removedItem = list.get(0);// value of the root
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        iterations = 0;
        while (currentIndex < list.size()) {
            iterations++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size()) 
                break;
            int maxIndex = left;
            E max = list.get(maxIndex);
            if (right < list.size())
            if(max.compareTo(list.get(right)) < 0)
                maxIndex = right;
            E current = list.get(currentIndex);
            max = list.get(maxIndex);            
            if(current.compareTo(max) < 0){
                list.set(maxIndex, current);
                list.set(currentIndex, max);
                currentIndex = maxIndex;
            }
            else
                break;
        }
        return removedItem;
    }
    /***
     * Method to get the height of a Heap
     * no param
     * @return the numerical value for height
     */
    // O(n)
    public int height(){
        return height(0);
    }
    /***
     * Resursive Helper Method for height
     * @param   node holds the current Node
     * @return the height of the heap back to the method above with no param
     */
    // O(n)
    private int height(int nodeIndex){
        if(nodeIndex >= list.size()){ //base case
            return 0;
        }
        int lHeight = height(nodeIndex * 2 + 1); //recursive call on the left subtree
        int rHeight = height(nodeIndex * 2 + 2); 
        return Math.max(lHeight, rHeight) + 1; 
    }
    /***
     * Method to check if a heap is balanced
     * no param
     * @return T/F respectively
     */
    // O(n)
    public boolean isBalanced(){
        return isBalanced(0);
    }
    /***
     * Resursive Helper method for checking balance
     * @param   node points to the next node we are going to check
     * @return  T/F respectively
     */
    // O(n)
    public boolean isBalanced(int nodeIndex){
        if(nodeIndex >= list.size()){// base case 1
            return true;
        }
        int lHeight = height(nodeIndex * 2 + 1);
        int rHeight = height(nodeIndex * 2 + 2);
        int difference = Math.abs(lHeight - rHeight);
        if(difference > 1){ // base case 2
            return false;
        }
        return isBalanced(nodeIndex * 2 + 1) && isBalanced(nodeIndex * 2 + 2);
    }
}