/***
 * Class to model the class HashMapEntry
 * @author GIVE TO US FROM COURSE SITE
 * @version 0.1
 * Date of creation: April 20, 2023
 * Last Date Modified: April 20, 2023
 */

public class HashMapEntry<K, V> {
	private K key;
	private V value;
	public HashMapEntry(K k, V v) {
		key = k;
		value = v;
	}
	public K getKey() { 
        return key; 
    }
	public V getValue() { 
        return value; 
    }
	public void setKey(K k) {
		key = k;
	}
	public void setValue(V v) {
		value=v;
	}
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}
