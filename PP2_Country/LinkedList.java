/***
 * Class to model the entity LinkedList
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 11, 2023
 * Last Date Modified: April 11, 2023 
 */
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
    Linked List data structure (Singly Linked) ---- MODIFIED NOW DOUBLY LINKED: iteratorable in both directions 
 */
public class LinkedList<E>{
    // Data members: references to the head and tail nodes
	private Node head, tail;
    // data member: size of the list
	int size;
    /**
        Inner class Node
     */ 
	private class Node{
		E value;
        //MODIFIED DATA MEMBERS: added previous to make it doubly linked 
		Node next, previous;
        /**
            Constructor with one parameter
            sets the value and initializes next to null
            @param initialValue the value of the node
         */
        //MODIFIED THIS METHOD: added previous = null
        //O(1) constant time
		Node(E initialValue){
			value = initialValue; 
            next = null;
            previous = null;

		}
	}
    /**
        default constructor
        sets the head and tail to null and size to 0
     */ 
	public LinkedList() { 
		head = tail = null;
		size = 0;
	}

    /**
        Adding an item at the head of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
   //MODIFIED THIS METHOD: after adding a newNode to the front, the old head as a pointer back to the newNode such that it is a doubly linked list in both directions
   //O(1) constant time
    public boolean addFirst(E value) {
		Node newNode = new Node(value);
        if(head == null) {
            head = tail = newNode; 
        }
		else { 
            //The newNode's next node is the old head 
            newNode.next = head;
            //now the old head has to have a previous pointer to newNode
            head.previous = newNode;
            //the new head does not have "previous" so keep that as null
            //now actually swap the old head of this linkedList to the new node; 
			head = newNode;
            //the newNode now points to the old head which is now the 2nd node. The old head (which is now the 2nd) points preivously back to the newNode or new head
		}
		size++; 
        return true;
    }
    /**
        Adding an item at the end of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
    /*MODIFIED THIS METHOD: when adding a node to the end of the linked list, the old tail has a pointer 
     * to the new tail, and the new tail has a previous pointer to the preivous old tail such that it is implemented
     * as a doubly linked list that could be iteratorabled in both directions
     */
    //O(1) constant time
    public boolean addLast(E value) {
		Node newNode = new Node(value);
		//if the list is empty
        if(head == null) { 
            //both head and tail are the newNode
            head = tail = newNode; 
        }
		else { 
            //set this currentTails next pointer to the newNode; 
            tail.next = newNode; 
            //Then set the newNode's previous pointer to the old tail
            newNode.previous = tail;
            //now the actual tail is the newNode so set the newNode to tail
            tail = newNode; 
        }
		size++; 
        return true;
    }
    /**
        Adding an item at the tail of the list (inherited from Collection)
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean add(E value) {
		return addLast(value);
    }
    /**
        Retrieving the value of the first node in the list
        @return the value of the node at the head of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getFirst() {
		if (head == null)
			throw new NoSuchElementException();
		return head.value;
    }
    /**
        Retrieving the value of the last node in the list
        @return the value of the node at the tail of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getLast() {
		if (head == null)
			throw new NoSuchElementException();
		return tail.value;
    } 
    /**
        Removing the first node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
     */
    //MODIFIED METHOD: also handles changing the previous pointer of the new head to null
    //O(1) constant time
    public boolean removeFirst() {
		//if empty list
        if (head == null){ 
            //throw error
            throw new NoSuchElementException();
        }
        //else the head of this list is the next node 
		head = head.next;
        //get ride of the this next node's previous pointer bc it's the new head and shouldnt have one
        head.previous = null;
        //now after decrementing the list, check and make sure the list is not empty
		if(head == null){
            //if now empty reset tail to null
            tail = null;
        }
        //regularless (unless error caught in first if statement) decrement size
		size--; 
        return true;
    }
    /**
        Removing the last node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
    */
    //MODIFIED METHOD - Removes last while keeping doubly linked functionality in tact
    //O(n) - has while loop 
    public boolean removeLast() {
        //if list is empty you can't remove an element
        if (head == null)
            //so throw error
            throw new NoSuchElementException();
        //else if only one element in list, removing the first is the same as removing the last    
        if (size == 1)
            return removeFirst();
        //else size < 1; 
        Node current = head;
        Node previous = null;
        //iterate to the until you get to the tail bc the next tail node is null; 
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        //set the node before the tail -- currently named previous -- next's pointer to null
        previous.next = null;
        //set the current tails -- currently named current -- previous pointer to null
        current.previous = null;
        //now the old tail has been detatched from the list
        //set this list's tail to the previous node
        tail = previous;
        //decrement size
        size--;
        return true;
    }
    /**
        Get the value of the node at position index
        @param index of the node being accessed
        @return the value of the node at index
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E get(int index){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        if(index == 0){
            return getFirst();
        }
        if(index == size-1){
            return getLast();
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        return node.value;
    }
    /**
        Set the value of the node at position index
        @param index of the node being modified
        @return the old value of the modified node
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E set(int index, E value){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        E old;
        if(index == 0){
            old = head.value;
            head.value = value;
            return old;
        }
        if(index == size-1){
            old = tail.value;
            tail.value = value;
            return old;
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        old = node.value;
        node.value = value;
        return old;
    }

    /**
        @override toString from class Object
        @return formatted string with all the values in the list
        time complexity: O(n)
     */
    public String toString() {
		String output = "[";
		Node node = head;
		while(node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
    }
    /**
        clear the list
        sets head and tail to null and size to 0
        time complexity: O(1)
     */
    public void clear() {
        head = tail = null; 
        size = 0; 
    }
    /**
        check if the list is empty
        @return true if the list is empty, false otherwise
        time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
        get the size of the list
        @return the number of nodes in the list
        time complexity: O(1)
     */
    public int size() {
        return size; 
    } 
    /**
        Get an iterator for the list
        @return an iterator object pointing to the first node in the list
        time complexity: O(1)
     */
    public Iterator<E> iterator(){
		  return new LinkedListIterator();
    }
    /**
        Inner class that implements the interface Iterator
     */
    private class LinkedListIterator implements Iterator<E>{
        // data member: reference to the node referenced by the iterator
		private Node current = head;
        /**
            check if the iterator has a next node
            @return true if the iterator has access to the next node, false otherwise
            time complexity: O(1)
         */
		public boolean hasNext() {
			return (current != null);
		}
        /**
            get the value of the current noe and move the iterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E next() {
            if(current == null)
			  throw new NoSuchElementException();
			E value = current.value;
			current = current.next; 
            return value;
		}
    }
    
    /**
        Method to get a bidirectional iterator for the list
        @return an iterator object pointing to the first node of the list
     */
    //MODIFIED METHOD
    //O(1) constant time
    public ListIterator<E> listIterator(){
        return new DoublyLinkedListIterator();
    }
    /**
        Method to get a bidirectional iterator for the list
        @param index where the iterator should start
        @return the iterator object pointing to the element at index
        @throws ArrayIndexOutOfBoundsExeption if index is less than 0 or index is greater than size
     */
    //MODIFIED METHOD
    //O(n) for loop
    public ListIterator<E> listIterator(int index){
        ListIterator<E> listIter = new DoublyLinkedListIterator();
        if(index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < index; i++){
            listIter.next();
        }
        return listIter;
    }
    /**
        Inner class that implements the interface ListIterator
     */
    //IMPLEMENTED CLASS for ListIerator
    public class DoublyLinkedListIterator implements ListIterator<E>{
        // data member: reference to the node referenced by the iterator 
        private Node current = head; 
        /**
            check if the iterator has a next node
            @return true if the iterator has access to the next node, false otherwise
            time complexity: O(1)
         */
        public boolean hasNext() {
			return (current != null);
		}
        /**
            get the value of the current noe and move the iterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
        public E next(){
            if(current == null)
			  throw new ArrayIndexOutOfBoundsException();
			E value = current.value;
			current = current.next; 
            return value;
        }
        /**
            check if the iterator has a previous node
            @return true if the iterator has access to the previous node, false otherwise
            time complexity: O(1)
         */
        public boolean hasPrevious(){
            return (current != null);
        }
        /**
            get the value of the current node and move the iterator to the previous node
            @return the value of the current node
            time complexity: O(1)
         */
        public E previous(){
            if(current == null){
                throw new ArrayIndexOutOfBoundsException();
            }
            E value = current.value;
            current = current.previous;
            return value;
        }
        public void add(E v){
            throw new UnsupportedOperationException();    
        }
        public void set(E v){
            throw new UnsupportedOperationException();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public int nextIndex(){
            throw new UnsupportedOperationException();
        }
        public int previousIndex(){
            throw new UnsupportedOperationException();
        }
    }
}