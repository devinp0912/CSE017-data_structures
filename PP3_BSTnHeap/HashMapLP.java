import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;

/**
	Class that implements a hashtable of Hashmap entries with two generic types
	K for key
	V for value
 */
public class HashMapLP <K, V> {
	// constant data member: dthe default load factor
    private final static double DEFAULT_LOAD_FACTOR = 0.5;
    // data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor;
	// data member: Array of linked lists
	private HashMapEntry<K,V>[] hashTable;
	// data member: to counter the iterations of various methods
	public static int iterations = 0;
	// data member: counts collisions
	public static int collisions = 0;


	/**
		Default constructor
		Creates a hashmap with capacity 100 and load factor 0.9
		time complexity: O(1)
	 */
	public HashMapLP() {
		this(100, DEFAULT_LOAD_FACTOR);
	}
	/**
		Constructor with one parameter
		Creates a hashmap with capacity c and default load factor 0.9
		@param c the capacity of the hashtable
		time complexity: O(logn)
	 */
	public HashMapLP(int c) {
		this(c, DEFAULT_LOAD_FACTOR);
	}
	/**
		Constructor with two parameters
		@param c the capacity of the hashtable
		@param lf the load factor for the hashtable
		time complexity: O(logn)
	 */
	public HashMapLP(int c, double lf) {
		hashTable =  new HashMapEntry[trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
	}
    /**
		Method trimToPowerOf2 to create a hashtable with a size that is
		the closest power of two to c
		@param c the capacity intended for the hashtable
		@return the closet power of 2 to c 
		time complexity: O(logn)
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity  = capacity << 1; // * 2
		return capacity;
	}
	/**
		The hash function
		@param the hash code of the key
		@return a valid index in the hashtable
		time complexity: O(1)
	 */
    private int hash(int hashCode) {
		return hashCode & (hashTable.length-1);
	}
    /**
		Method to get the size of the hashtable
		@return the number of elements in the hashtable
		time complexity: O(1)
	 */
	public int size() { 
		return size;
	}
	/**
		Method to clear the hashtable
		time complexity: O(n)
	 */
	public void clear() { 
		size = 0;
		for(int i=0; i<hashTable.length; i++)
			if(hashTable[i] != null)
				hashTable[i] = null;
	}
	/**
		Method to check if the hashtable is empty
		@return true if the hashtable is empty, false otherwise
		time complexity: O(1)
	 */
	public boolean isEmpty() { 
		return (size == 0);
	}

	/**
		Search method
		@param key to be serached
		@return true if key was found, false otherwise
		time complexity: O(1)
	 */
	public boolean containsKey(K key) {
		if(get(key) != null)
			return true;
		return false;
	}
    /**
		Method to get the value of a key
		@param key to be serached
		@return the value of the key if found, null otherwise
		time complexity: O(1)
	 */
	public V get(K key) {		
		int HTIndex = hash(key.hashCode());
		while(hashTable[HTIndex] != null) {
				iterations++;
				if(hashTable[HTIndex].getKey().equals(key)){
					return hashTable[HTIndex].getValue();
				}
				HTIndex = (HTIndex + 1) % hashTable.length;
		}
		return null;
	}
    /**
		Method to add a pair (key,value) to the hashtable
		@param key to be added
		@param value of the key to be added
		@return old value if the key was found or the new value if key was not found
		time complexity: O(1) without rehash, O(n) with rehash
	 */
    public V put(K key, V value) {
		int HTIndex = hash(key.hashCode());
        int hash = HTIndex;
        if (get(key) != null) { // The key is in the hash map
            while(hashTable[HTIndex] != null) {
				iterations++;
				if(hashTable[HTIndex].getKey().equals(key)){
					V old = hashTable[HTIndex].getValue();
					hashTable[HTIndex].setValue(value);
					return old;
				}
				HTIndex = (HTIndex + 1) % hashTable.length;
			}
        }
		if(size >= hashTable.length * loadFactor){
		        rehash();
		}
		if(hashTable[HTIndex] != null){
			collisions++;
			while(hashTable[HTIndex] != null) {
				HTIndex = (HTIndex + 1) % hashTable.length;
			}
		}
		hashTable[HTIndex] = new HashMapEntry<>(key, value);
		size++;
		return value;
    }
   	/**
		Method to rehash the hashtable
		time complexity: (n)
    */
	private void rehash() {
		ArrayList<HashMapEntry<K,V>> list = toList();
		hashTable = new HashMapEntry[hashTable.length << 1]; // double the length of hashtable
		size = 0;
		for(HashMapEntry<K,V> entry: list) {
			put(entry.getKey(), entry.getValue());
        }
		
	}
   	/**
		Method to return the pairs (key,value) stored in teh hashtable
		@return an array list with all the pairs (key,value)
		time complexity: O(n)
    */
	public ArrayList<HashMapEntry<K,V>> toList(){
		ArrayList<HashMapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i] != null) {
					list.add(hashTable[i]);
			}
		} 
        return list;
	}
    /**
		toString method
		@return formatted string with all the pairs (key,value) in the hashtable
		time complexity: O(n)
	 */
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				out += hashTable[i].toString();
				out += "\n";
			}
		}
		out += "]"; return out;
	}
	/***
	 * Method that returns a sorted arraylist of all the elements in the hashMap based on key
	 * @param	c holds a comparator object
	 * @return	the arraylist with all the data sorted
	 * 
	 * Time Complexity Remark: 
	 * A usual Merge sort method has O(n logn). 
	 * HOWEVER... here we have 2 helper methods 
	 * sortedKeys has one for loop. Its complexity is O(n)
	 * mergeSort has O(logn)
	 * merge has O(n)
	 * 
	 * Combined the Time complexity of this mergeSort method is O(2n logn) = O(nlogn)
	 */
	public ArrayList<HashMapEntry<K,V>> sortedKeys(Comparator<K> c) {
        //create an arraylist and populate it will all the elements from the hashTable list
		HashMapEntry<K,V>[] list = new HashMapEntry[this.size];
		int index = 0;
		for(int i = 0; i < hashTable.length; i++){
			iterations++;
			if(hashTable[i] != null){
				list[index++] = hashTable[i];
			}
		}
		mergeSort(list, c);
		ArrayList<HashMapEntry<K,V>> returnMe = new ArrayList<>(list.length);
		for(HashMapEntry<K,V> entry: list){
			iterations++;
			returnMe.add(entry);
		}
		return returnMe;
    }
	private void mergeSort(HashMapEntry<K,V>[] list, Comparator<K> c){
		iterations++;
		if(list.length > 1){
			HashMapEntry<K,V>[] firstHalf = new HashMapEntry[list.length/2];
			HashMapEntry<K,V>[] secondHalf = new HashMapEntry[list.length - list.length/2];
			//copy the first and 2nd halfs to there own respective
			System.arraycopy(list, 0, firstHalf, 0, list.length/2);
			iterations = iterations + list.length/2;
			System.arraycopy(list, list.length/2, secondHalf, 0, list.length - list.length/2);
			iterations = iterations + list.length/2;
			mergeSort(firstHalf, c);
			mergeSort(secondHalf, c);
			merge(firstHalf, secondHalf, list, c);
		}
	}
	private void merge(HashMapEntry<K,V>[] list1, 
					   HashMapEntry<K,V>[] list2, 
					   HashMapEntry<K,V>[] list, 
					   Comparator<K> c){
		int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
		iterations++;
        while(list1Index < list1.length && list2Index < list2.length) {
			iterations++;
            if (c.compare(list1[list1Index].getKey(), list2[list2Index].getKey()) < 0)
                list[listIndex++] = list1[list1Index++];
            else
                list[listIndex++] =  list2[list2Index++];
        }
        while(list1Index < list1.length){
            list[listIndex++] = list1[list1Index++];
			iterations++;
		}
        while(list2Index < list2.length){
            list[listIndex++] = list2[list2Index++];
			iterations++;
		}
	}
}