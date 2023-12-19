/***
 * Class to model the class LinkedList
 * @author GIVE TO US FROM COURSE SITE ---- I created the methods at the bottom
 * @version 0.1
 * Date of creation: March 30, 2023
 * Last Date Modified: March 30, 2023
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;
    public static int containsIter, removeIter, addIter;
  
    // Inner class Node
    private class Node {
        E value;
        Node next;
        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }
    // Constructor
    public LinkedList() {
        head = tail = null;
        size = 0;
    }
    // Adding an item to the list
    public boolean addFirst(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    public boolean add(E item) {
        return addFirst(item);
    }
    
    // Retrieving an item from the list
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    // Removing an item from the list
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return true;
    }
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        Node previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return true;
    }
    // toString() method
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }
    // clear, check if empty, and size
    public void clear() {
        head = tail = null;
        size = 0;
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public int size() {
        return size;
    }

    // Generating an iterator for the list
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;
        public boolean hasNext() {
            return (current != null);
        }
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //wrote these methods


    /***
     * Method that looks to see if the array contains an object
     * @param   o holds the object that is looked for in the array
     * @return  T/F depending on if it contains it or not
     */ 
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
    /***
     * Method that removes an object from a linkedlist
     * @param   o holds the object that is looked to be remove
     * @return T/F depending on where or not it was removed 
     */
    //O(n)
    public boolean remove(Object o){
        Node current = head;
        Node previous = null;
        removeIter = 0;
        while(current != null && !current.value.equals(o)){
            removeIter++;
            previous = current;
            current = current.next;
        }
        if(current == null){
            return false;
        }
        if(previous == null){
            return removeFirst();
        }
        previous.next = current.next;
        if(current == tail){//your at the tail
            tail = previous;
        }
        return true;
    }
    /***
     * Method to add a node at a given index
     * @param   index holds the index at which you want to add the element
     * @param   element holds the element to add
     * @return T/F if it was added succesfully or not
     */
    //O(n)
    public boolean add(int index, E element){
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        addIter = 0;
        if(index == 0){//add at the head
            return addFirst(element);
        }
        if(index == size){//add after tail
            return addLast(element);
        }
        Node current = head;
        Node previous = null;
        for(int i = 0; i < index; i++){
            addIter++;
            previous = current;
            current = current.next;
        }
        Node newNode = new Node(element);
        previous.next = newNode;
        newNode.next = current;
        size++;
        return true;
    }
}