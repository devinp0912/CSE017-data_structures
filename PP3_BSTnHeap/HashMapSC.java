import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
// import java.util.Comparable;

/**
	Class that implements a hashtable of Hashmap entries with two generic types
	K for key
	V for value
 */
public class HashMapSC <K, V> {
	// data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor;
	// data member: Array of linked lists
	private LinkedList<HashMapEntry<K,V>>[] hashTable;
	// data member: to counter the iterations of various methods
	public static int iterations = 0;
	// data member: counts collisions
	public static int collisions = 0;
	/**
		Default constructor
		Creates a hashmap with capacity 100 and load factor 0.9
		time complexity: O(1)
	 */
	public HashMapSC() {
		this(100, 0.9);
	}
	/**
		Constructor with one parameter
		Creates a hashmap with capacity c and default load factor 0.9
		@param c the capacity of the hashtable
		time complexity: O(logn)
	 */
	public HashMapSC(int c) {
		this(c, 0.9);
	}
	/**
		Constructor with two parameters
		@param c the capacity of the hashtable
		@param lf the load factor for the hashtable
		time complexity: O(logn)
	 */
	public HashMapSC(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)];
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
				hashTable[i].clear();
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
		if(hashTable[HTIndex] != null) {
			LinkedList<HashMapEntry<K,V>> ll = hashTable[HTIndex];
			for(HashMapEntry<K,V> entry: ll) {
				iterations++;
				if(entry.getKey().equals(key))
					return entry.getValue();
			}
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
	    if (get(key) != null) { // The key is in the hash map
		    int HTIndex = hash(key.hashCode());//get its hashCode for indexing
		    LinkedList<HashMapEntry<K,V>> ll; //create a linked list
            ll = hashTable[HTIndex]; //the linked list at the index in the hashtable is assigned the the ll
			for(HashMapEntry<K,V> entry: ll) {//for every element in the linked list in the hashtable
				if(entry.getKey().equals(key)) {// if the entry's key is equal to the key
					V old = entry.getValue(); //swap the values 
                    entry.setValue(value); 
                    return old;
			    }
		    }
        }
        // key not in the hash map - check load factor
        if(size >= hashTable.length * loadFactor){
		        rehash();
		}
        int HTIndex = hash(key.hashCode());
        //create a new LL if empty
        if(hashTable[HTIndex] == null){
		    hashTable[HTIndex] = new LinkedList<>();
		}else{
			collisions++;
		}
		
        hashTable[HTIndex].add(new HashMapEntry<>(key, value));
        size++; 
        return value;
    }
   	/**
		Method to rehash the hashtable
		time complexity: (n)
    */
	private void rehash() {
		ArrayList<HashMapEntry<K,V>> list = toList();
		hashTable = new LinkedList[hashTable.length << 1]; // double the length of hashtable
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
			if(hashTable[i]!= null) {
				LinkedList<HashMapEntry<K,V>> ll = hashTable[i];
				for(HashMapEntry<K,V> entry: ll)
					list.add(entry);
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
				for(HashMapEntry<K,V> entry: hashTable[i])
					out += entry.toString();
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
	 * HOWEVER... here we have a sortedKeys helper method
	 * sortedKeys has two for loops. Its complexity is O(2n)
	 * mergeSort has O(logn)
	 * merge has O(n)
	 * 
	 * Combined the Time complexity of this mergeSort method is O(3n logn) = O(nlogn)
	 */
	public ArrayList<HashMapEntry<K,V>> sortedKeys(Comparator<K> c) {
        //create an new list 
		HashMapEntry<K,V>[] list = new HashMapEntry[this.size];
		int index = 0;
		for(int i = 0; i < (hashTable.length); i++){
			if(hashTable[i] != null){
				for(HashMapEntry<K,V> entry: hashTable[i]){
					iterations++;
					list[index++] = entry;
				}
				
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
			HashMapEntry<K,V>[] firstHalf = new HashMapEntry[(list.length)/2];
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
		iterations++;
		int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index < list1.length && list2Index < list2.length) {
			iterations++;
            if (c.compare(list1[list1Index].getKey(), list2[list2Index].getKey()) < 0)
                list[listIndex++] = list1[list1Index++];
            else
                list[listIndex++] = list2[list2Index++];
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