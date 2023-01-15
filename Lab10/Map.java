import java.util.Set;


public interface Map<K, V> {
	
	/**
	 * If the given key is not already in the map, adds the 
	 * key-value pair to the map. Otherwise, updates the old
	 * value of the existing key to the specified value.
	 * @param key the key
	 * @param value the value to be stored in the map with the key
	 * @return null if the key was not already in the map, or
	 * the old value associated with the key if the key was already in the map
	 */
	public V put(K key, V value);
	
	/**
	 * Gets the value from the map that is associated with the given key
	 * @param key the key
	 * @return the value associated with the key, or null if the key is
	 * not in the map
	 */
	public V get(K key);
	
	/**
	 * Removes from the key-value pair associated with the specified key
	 * @param key the key
	 * @return the value associated with the key, or null if the key is
	 * not in the map
	 */
	public V remove(K key);
	
	/**
	 * Returns whether the map contains the key-value pair associated with
	 * the specified key
	 * @param key the key
	 * @return true if the map contains a key-value pair with the specified
	 * key, and false otherwise
	 */
	public boolean containsKey(K key);
	
	/**
	 * Returns whether the map contains no elements
	 * @return true if the map contains no key-value pairs, and false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Removes all elements from the map
	 */
	public void clear();
	
	/**
	 * Gets the number of key-value pairs in the map
	 * @return the number of key-value pairs in the map
	 */
	public int size();
	
	/**
	 * Gets a set of all keys in the map
	 * @return
	 */
	public Set<K> keySet();

}
