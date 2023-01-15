/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Nov 1, 2020
 */

import java.util.*;

public class HashMap274<K, V> implements Map<K, V> {

	// Ordinarily, the instance variables would be private.
	// But leave them public. Why? Because it makes it easier for you
	// (and your instructors!) to test your code.
	// Each array location contains a LinkedList of Key-Value pairs
	// So, it's an array of LinkedLists
	public LinkedList<Entry<K, V>>[] buckets;
	public int size;
	public static final int DEFAULT_CAPACITY = 11; // prime
	public static final double LOAD_FACTOR = 0.6;

	@SuppressWarnings("unchecked")
	public HashMap274() {
		buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[DEFAULT_CAPACITY];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Entry<K, V>>();
		}
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		int hashIndex = getHashIndex(key);
		Entry<K, V> temp = new Entry<K, V>(key);
		V result = null;

		if (buckets[hashIndex].contains(temp)) {
			result = buckets[hashIndex]
					.get(buckets[hashIndex].indexOf(temp)).value;
			buckets[hashIndex].remove(buckets[hashIndex].indexOf(temp));
		} else {
			size++;
		}

		buckets[hashIndex].add(new Entry<K, V>(key, value));

		if (!loadFactorOK()) {
			resize();
		}

		return result;
	}

	boolean loadFactorOK() {
		if ((double) size / buckets.length > LOAD_FACTOR) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		LinkedList<Entry<K, V>>[] oldBuckets = buckets;
		buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[firstPrime(
				2 * oldBuckets.length)];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Entry<K, V>>();
		}

		for (int i = 0; i < oldBuckets.length; i++) {
			while (!oldBuckets[i].isEmpty()) {
				Entry<K, V> entry = oldBuckets[i].remove(0);
				int newIndex = getHashIndex(entry.key);
				buckets[newIndex].add(entry);
			}
		}
	}

	@Override
	public V get(K key) {
		int hashIndex = getHashIndex(key);
		Entry<K, V> temp = new Entry<K, V>(key);

		if (buckets[hashIndex].contains(temp)) {
			V result = buckets[hashIndex]
					.get(buckets[hashIndex].indexOf(temp)).value;
			return (V) result;
		} else {
			return null;
		}
	}

	@Override
	public V remove(K key) {
		int hashIndex = getHashIndex(key);
		Entry<K, V> temp = new Entry<K, V>(key);
		V result = null;

		if (buckets[hashIndex].contains(temp)) {
			result = buckets[hashIndex]
					.get(buckets[hashIndex].indexOf(temp)).value;
			buckets[hashIndex].remove(buckets[hashIndex].indexOf(temp));
			size--;
		}

		return result;
	}

	@Override
	public boolean containsKey(K key) {
		Entry<K, V> temp = new Entry<K, V>(key);
		int bucketIndex = getHashIndex(key); // tells us which bucket to check
		return buckets[bucketIndex].indexOf(temp) != -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		for (LinkedList<Entry<K, V>> list : buckets) {
			list.clear();
		}
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Set<K> keySet() {
		Set<K> result = new TreeSet<K>();

		for (LinkedList<Entry<K, V>> linkedList : buckets) {
			for (Entry<K, V> entry : linkedList) {
				result.add(entry.key);
			}
		}

		return result;
	}

	public String toString() {
		String result = "";

		for (int i = 0; i < buckets.length; i++) {
			result += "[" + i + "]\t" + buckets[i] + "\n";
		}
		result += "size: " + size + "\n";
		return result;
	}

	// Gets the index of the bucket where a given string should go,
	// by computing the hashCode, and then compressing it to a valid index.
	private int getHashIndex(K key) {
		int index = key.hashCode() % buckets.length;
		if (index < 0)
			index += buckets.length;
		return index;
	}

	// Returns true if a number is prime, and false otherwise
	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	// Returns the first prime >= n
	private static int firstPrime(int n) {
		while (!isPrime(n))
			n++;
		return n;
	}

	// Ordinarily, the inner class would be private.
	// But leave it public. Why? Because it makes it easier for you
	// (and your instructors!) to test your code.
	public class Entry<K, V> {
		public K key;
		public V value;
		// constructors
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public Entry(K key) {
			this(key, null);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Entry<K, V> other = (Entry<K, V>) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}

		public String toString() {
			return "<" + key + ", " + value + ">";
		}
	}

	public static void main(String[] args) {
		HashMap274<String, Integer> map = new HashMap274<>();
		System.out.println(map);
		System.out.println("cat".hashCode() % 11);
		System.out.println("dog".hashCode() % 11);
		System.out.println("A".hashCode() % 11);
		System.out.println("B".hashCode() % 11);
		System.out.println("C".hashCode() % 11);
		System.out.println(map.put("cat", 1));
		System.out.println(map.put("dog", 1));
		System.out.println(map.put("cat", 99));
		System.out.println(map);

		map.put("A", 3);
		map.put("B", 4);
		map.put("C", 5);
		System.out.println(map.get("cat")); // 99
		System.out.println(map.get("tree")); // null
		System.out.println(map.get("C")); // 5
		System.out.println(map);
		System.out.println("resized?");
		// Adding these next values should cause resize()
		map.put("D", 6);
		map.put("E", 7);
		System.out.println("resized?");
		System.out.println(map);

		System.out.println(map.remove("E"));
		System.out.println(map);
		System.out.println(map.remove("dog"));
		System.out.println(map);
		System.out.println(map.put("C", 20));
		System.out.println(map);

	}
}
