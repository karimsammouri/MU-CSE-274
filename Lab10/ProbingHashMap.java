import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

public class ProbingHashMap<K,V> implements Map<K,V>{
	
	private Entry[] hashTable;
	private int size;
	private static final int defaultCapacity = 11;
	
	public ProbingHashMap() {
		size = 0;
		hashTable = new Entry[defaultCapacity];
	}

	@Override
	public V put(K key, V value) {
		Entry entry = new Entry(key, value);
		int hashIndex = getHashIndex(key);
		V prev = null;
		
		while (hashTable[hashIndex] != null) {
			if (hashTable[hashIndex].key.equals(key)) {
				if (hashTable[hashIndex].inUse) {
					prev = (V) hashTable[hashIndex].value;
				}
				hashTable[hashIndex] = entry;
				return prev;
			}
			hashIndex = (hashIndex + 1) % hashTable.length;
		}
		
		hashTable[hashIndex] = entry;
		size++;
		if (isFull()) resize();
		
		return null;
	}

	@Override
	public V get(K key) {
		int hashIndex = getHashIndex(key);
		
		while (hashTable[hashIndex] != null) {
			if (hashTable[hashIndex].key.equals(key) && hashTable[hashIndex].inUse)
			return (V) hashTable[hashIndex].value;
			hashIndex = (hashIndex + 1) % hashTable.length;
		}
		
		return null;
	}

	@Override
	public V remove(K key) {
        int hashIndex = getHashIndex(key);
		
		while (hashTable[hashIndex] != null) {
			if (hashTable[hashIndex].key.equals(key)) {
				hashTable[hashIndex].inUse = false;
				size--;
				return (V) hashTable[hashIndex].value;
			}
			hashIndex = (hashIndex + 1) % hashTable.length;
		}
		
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		int inUse = 0;
		for ( int i=0; i < hashTable.length; i++) {
			if ( hashTable[i] != null ) inUse++;
		}
		
		return inUse == hashTable.length;
	}

	@Override
	public void clear() {
		size = 0;
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = null;																																
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	private void resize() {
		Entry[] oldTable = hashTable;
		hashTable = new Entry[firstPrime(oldTable.length * 2)];
		size = 0;
		
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null)
			put((K) oldTable[i].key, (V) oldTable[i].value);
		}
	}
	
	// Gets the index of the bucket where a given string should go,
	// by computing the hashCode, and then compressing it to a valid index.
	private int getHashIndex(K key) {
		int index = key.hashCode() % hashTable.length;
		if (index < 0)
			index += hashTable.length;
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


	public String toString() {
		String result = "";
		
		for (int i = 0; i < hashTable.length; i++) {
			result += "[" + i + "]\t" + hashTable[i] + "\n";
		}
 		result += "size: " + size + "\n";
		return result;
	}
	
	private class Entry<K, V> {
		private K key;
		private V value;
		private boolean inUse;
		
		// constructors
		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
			inUse = true;
		}
		private Entry(K key) {
			this(key, null);
			inUse = true;
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
			Entry<K,V> other = (Entry<K,V>) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}

		public String toString() {
			return "<" + key + ", " + value + ", " + inUse + ">";
		}
		
	}
	
	public static void main( String[] args) {
	ProbingHashMap<String, Integer> map = new ProbingHashMap<>();
		
	map.put("Lily",  13);
	map.put("Max",  10);
	map.put("Evelyn",  5);
	map.put("Theo",  0);
	map.put("Lou",  2);
	map.put("Marin",  1);
	map.put("Morgan",  39);
	map.put("Carmen",  38);
	map.put("Kiestin",  31);
		
		System.out.println( "Lily:\t" +  map.get("Lily"));
		System.out.println( "Max:\t" + map.get("Max"));
		System.out.println( "Evelyn:\t" +  map.get("Evelyn"));
		System.out.println( "Theo:\t" +  map.get("Theo"));
		
		System.out.println( "\n\n" + map.toString());
		
/*		System.out.println( "\n\nRemove Jim\t" +  map.remove("Jim"));
		System.out.println( "\n\n" + map.toString());
		
		map.put("Beth",  66);
		System.out.println( "\n\n" + map.toString());
		
		
		ProbingHashMap<Integer, String> map2 = new ProbingHashMap<>();
		map2.put(10,  "Bill");
		map2.put(2,  "Mary");
		map2.put(7,  "Jose");
		map2.put(8,  "Ted");
		map2.put(0,  "Eric");
		map2.put(5,  "Matt");
		map2.put(6,  "Karen");
		map2.put(11,  "Daniela");
		map2.put(22,  "Norm");
		map2.put(1,  "Joe");
		map2.put(0,  "Larry");
		System.out.println("Size: " + map2.size);
		map2.put(33,  "John");
		System.out.println("Size: " + map2.size);
		System.out.println( "\n\n" + map2.toString());
		*/
	}
}
