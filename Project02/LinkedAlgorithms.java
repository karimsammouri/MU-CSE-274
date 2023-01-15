import java.util.NoSuchElementException;

/**
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: Program #2 – Linked List Algorithms.
 * Attribution: Everything aside from the prewritten code given by the course 
 * is written by me.
 */

public class LinkedAlgorithms {

	private class Node {
		private String data;
		private Node next;

		private Node(String data) {
			this.data = data;
			this.next = null;
		}
	}

	public Node head;
	public int numItems;

	/**
	 * Creates the empty list.
	 */
	public LinkedAlgorithms() {
		head = null;
		numItems = 0;
	}

	/**
	 * Creates a list containing all the elements of the passed array.
	 * {@code data[0]} will be the first element of the list, {@code data[1]}
	 * will be the second element of the list, and so on.
	 * 
	 * @param data
	 *            The array of values
	 * @throws NullPointerException
	 *             - data is null
	 */
	public LinkedAlgorithms(String[] data) {
		if (data == null) {
			throw new NullPointerException();
		} else {
			for (int i = data.length - 1; i >= 0; i--) {
				Node node = new Node(data[i]);
				node.next = head;
				head = node;
				numItems++;
			}
		}
	}

	/**
	 * Constructs a deep copy of the linked list {@code original}
	 * 
	 * @param original
	 *            The list to be copied
	 * @throws NullPointerException
	 *             - original is null
	 */
	public LinkedAlgorithms(LinkedAlgorithms original) {
		this(original.toArray());
	}

	/**
	 * Returns array with all the elements.
	 * 
	 * @return Array containing all elements.
	 */
	public String[] toArray() {
		String[] result = new String[numItems];
		int i = 0;
		Node pointer = head;
		while (pointer != null) {
			result[i] = pointer.data;
			i++;
			pointer = pointer.next;
		}
		return result;
	}

	/**
	 * Formats the elements in the list using leading and ending brackets (i.e.,
	 * []), with the values comma separated. There will be one space between
	 * each of these but none at the beginning nor at the end. Some examples: if
	 * the list is empty, toString() gives [] if the list has these three
	 * elements in this order: "hello", "world", "everyone", then the result
	 * should be [hello, world, everyone]
	 */
	@Override
	public String toString() {
		String result = "[";
		Node pointer = head;
		while (pointer != null) {
			if (pointer.next == null) {
				result += pointer.data;
			} else {
				result += pointer.data + ", ";
			}
			pointer = pointer.next;
		}
		result += "]";
		return result;
	}

	/**
	 * Returns the number of items in the list
	 * 
	 * @return Number of items in the list
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Determines if two lists contain exactly the same values, in exactly the
	 * same order.
	 * 
	 * @return {@code true} if and only if obj is an list that is equivalent to
	 *         the incoming list.
	 */
	public boolean equalsLinkedList(LinkedAlgorithms obj) {
		if (obj.numItems != numItems) {
			return false;
		} else {
			Node pointer = head;
			Node pointer2 = obj.head;
			while (pointer != null) {
				if (!pointer.data.equals(pointer2.data)) {
					return false;
				}
				pointer = pointer.next;
				pointer2 = pointer2.next;
			}
		}
		return true;
	}

	/**
	 * Determines if {@code key} is in the linked list.
	 * 
	 * @param key
	 *            The value to be found
	 * @return true if and only if {@code key} is in the list
	 */
	public boolean contains(String key) {
		Node pointer = head;
		while (pointer != null) {
			if (key.equals(pointer.data)) {
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	/**
	 * Determines the index of {@code key}. -1 is returned if the value is not
	 * present in the linked list. If {@code key} is present present more than
	 * once, the first index is returned.
	 * 
	 * @param key
	 *            The value to be found
	 * @return The index of the {@code key}
	 */
	public int find(String key) {
		int index = -1;
		Node pointer = head;
		while (pointer != null) {
			index++;
			if (key.equals(pointer.data)) {
				return index;
			}
			pointer = pointer.next;
		}
		return -1;
	}

	/**
	 * Returns the value of the first element of the list.
	 * 
	 * @return The first element of the list.
	 * @throws NoSuchElementException
	 *             the list is empty
	 */
	public String getFirst() {
		if (numItems != 0) {
			return head.data;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the value of the last element of the list.
	 * 
	 * @return The last element of the list.
	 * @throws NoSuchElementException
	 *             the list is empty
	 */
	public String getLast() {
		if (numItems != 0) {
			Node pointer = head;
			while (pointer != null) {
				if (pointer.next == null) {
					return pointer.data;
				}
				pointer = pointer.next;
			}
			return null;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the value of the {@code ith} element of the list (0 based).
	 * 
	 * @param i
	 *            The target index
	 * @return The value of the ith element of the list.
	 * @throws IndexOutOfBoundsException
	 *             {@code i} is illegal
	 */
	public String getAt(int i) {
		if (i < 0 || i >= numItems) {
			throw new IndexOutOfBoundsException();
		} else {
			Node pointer = head;
			for (int index = 0; index < i; index++) {
				pointer = pointer.next;
			}
			return pointer.data;
		}
	}

	/**
	 * Adds {@code data} to the beginning of the list. All other values in the
	 * list remain but they are "shifted to the right."
	 * 
	 * @param data
	 *            The value to add to the list
	 */
	public void insertFirst(String data) {
		Node node = new Node(data);
		node.next = head;
		head = node;
		numItems++;
	}

	/**
	 * Adds {@code data} to the end of the list. All other values in the list
	 * remain in their current positions.
	 * 
	 * @param data
	 *            The value to add to the list
	 */
	public void insertLast(String data) {
		Node node = new Node(data);
		if (head == null) {
			head = node;
			numItems++;
		} else {
			Node pointer = head;
			while (pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = node;
			numItems++;
		}
	}

	/**
	 * Adds data to a specific spot in the list. The values in the list remain
	 * intact but {@code data} is inserted in the list at position {@code i}.
	 * When {@code i=0}, the result is the same as {@code insertFirst}.
	 * 
	 * @param i
	 *            The target index
	 * @param data
	 *            The value to add to the list
	 * @throws IndexOutOfBoundsException
	 *             {@code i} is illegal
	 */
	public void insertAt(int i, String data) {
		if (i < 0 || i > numItems) {
			throw new IndexOutOfBoundsException();
		} else {
			Node node = new Node(data);
			if (i == 0) {
				node.next = head;
				head = node;
			} else {
				Node pointer = head;
				for (int index = 0; index < i - 1; index++) {
					pointer = pointer.next;
				}
				node.next = pointer.next;
				pointer.next = node;
			}
			numItems++;
		}
	}

	/**
	 * Adds {@code newData} the position immediately preceding
	 * {@code existingData}. If the {@code existingData} appears multiple times,
	 * only the first one is used.
	 * 
	 * @param newData
	 *            The value to add to the list
	 * @param existingData
	 *            The value used to control where insertion is to take place
	 * @throws NoSuchElementException
	 *             {@code existingData} is not in the list
	 */
	public void insertBefore(String newData, String existingData) {
		int insertionIndex = find(existingData);
		if (insertionIndex == -1) {
			throw new NoSuchElementException();
		} else {
			insertAt(insertionIndex, newData);
		}
	}

	/**
	 * Adds {@code newData} the position immediately after {@code existingData}.
	 * If the {@code existingData} appears multiple times, only the first one is
	 * used.
	 * 
	 * @param newData
	 *            The value to add to the list
	 * @param existingData
	 *            The value used to control where insertion is to take place
	 * @throws NoSuchElementException
	 *             {@code existingData} is not in the list
	 */
	public void insertAfter(String newData, String existingData) {
		int insertionIndex = find(existingData);
		if (insertionIndex == -1) {
			throw new NoSuchElementException();
		} else {
			insertAt(insertionIndex + 1, newData);
		}
	}

	/**
	 * Removes the first element of the list. The remaining elements are kept in
	 * their existing order.
	 * 
	 * @return The value removed from the list
	 * @throws NoSuchElementException
	 *             if the list is empty.
	 */
	public String removeFirst() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		} else {
			String result = head.data;
			head = head.next;
			numItems--;
			return result;
		}
	}

	/**
	 * Removes the last element of the list. The remaining elements are kept in
	 * their existing order.
	 * 
	 * @return The value removed from the list
	 * @throws NoSuchElementException
	 *             if the list is empty.
	 */
	public String removeLast() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		} else if (numItems == 1) {
			String result = head.data;
			head = null;
			numItems--;
			return result;
		} else {
			Node pointer = head;
			while (pointer.next.next != null) {
				pointer = pointer.next;
			}
			String result = pointer.next.data;
			pointer.next = null;
			numItems--;
			return result;
		}
	}

	/**
	 * Removes the ith element of the list. The remaining elements are kept in
	 * their existing order.
	 * 
	 * @param i
	 *            The target index
	 * @return The value removed from the list
	 * @throws IndexOutOfBoundsException
	 *             i does not represent a legal index
	 */
	public String removeAt(int i) {
		if (i < 0 || i >= numItems) {
			throw new IndexOutOfBoundsException();
		} else if (i == 0) {
			return removeFirst();
		} else {
			Node pointer = head;
			for (int index = 0; index < i - 1; index++) {
				pointer = pointer.next;
			}
			String result = pointer.next.data;
			pointer.next = pointer.next.next;
			numItems--;
			return result;
		}
	}

	/**
	 * Removes the first occurrence of data in the linked list.
	 * 
	 * @param data
	 *            The value to be removed.
	 * @return {@code true} if and only if {@code data} was removed
	 */
	public boolean removeFirstOccurrenceOf(String data) {
		int index = find(data);
		if (index == -1) {
			return false;
		} else {
			removeAt(index);
			return true;
		}
	}

	/**
	 * Removes the all occurrence of {@code data} in the linked list.
	 * 
	 * @param data
	 *            The value to be removed.
	 * @return The number of times {@code data} was removed
	 */
	public int removeAllOccurrencesOf(String data) {
		// creating array that will hold indexes of occurrences of data.
		String[] indexArray = new String[numItems];

		int index = 0;
		Node pointer = head;
		// saving indexes in the array.
		while (pointer != null) {
			if (pointer.data.equals(data)) {
				indexArray[index] = index + "";
			}
			index++;
			pointer = pointer.next;
		}

		int result = 0;
		// going through the array and using the stored index to remove the
		// elements.
		for (int i = 0; i < indexArray.length; i++) {
			if (indexArray[i] != null) {
				// subtracting result from the indexes to take into account
				// decreasing indexes after subsequent removals.
				removeAt(Integer.parseInt(indexArray[i]) - result);
				result++;
			}
		}
		return result;
	}

	/**
	 * Reverses the elements in the incoming linked list.
	 */
	public void reverse() {
		Node prev = null;
		Node curr = head;
		Node next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
	}

	/**
	 * Puts all the elements in the list to uppercase.
	 */
	public void toUpper() {
		Node pointer = head;
		while (pointer != null) {
			pointer.data = pointer.data.toUpperCase();
			pointer = pointer.next;
		}
	}

	/**
	 * Returns the concatenation of all strings, from left to right. No extra
	 * spaces are inserted.
	 * 
	 * @return Concatenation of all string in the list
	 */
	public String getConcatenation() {
		String result = "";
		Node pointer = head;
		while (pointer != null) {
			result += pointer.data;
			pointer = pointer.next;
		}
		return result;
	}

	/**
	 * Returns the alphabetically last value in the list.
	 * 
	 * @return The alphabetically last value in the list
	 * @throws NoSuchElementException
	 *             list is empty
	 */
	public String getAlphabeticallyLast() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		} else {
			Node pointer = head;
			String result = pointer.data;
			while (pointer != null) {
				if (result.compareToIgnoreCase(pointer.data) < 0) {
					result = pointer.data;
				}
				pointer = pointer.next;
			}
			return result;
		}
	}

	/**
	 * Returns the index where the alphabetically last value value resides. If
	 * this value appears multiple times, the first occurrence is used.
	 * 
	 * @return Index value of where maximum value resides
	 * @throws NoSuchElementException
	 *             list is empty
	 */
	public int indexOfAlphabeticallyLast() {
		if (numItems == 0) {
			throw new NoSuchElementException();
		} else {
			Node pointer = head;
			int index = 0;
			int i = 0;
			String last = pointer.data;
			while (pointer != null) {
				if (last.compareToIgnoreCase(pointer.data) < 0) {
					last = pointer.data;
					index = i;
				}
				pointer = pointer.next;
				i++;
			}
			return index;
		}
	}

	/*
	 * Determines if the two list contain elements that have exactly the same
	 * value, with the same list sizes, but with the elements perhaps in
	 * different order.
	 * 
	 * @returns true only if the two lists are permutations of one another.
	 */
	public boolean anagrams(LinkedAlgorithms other) {
		if (size() == other.size()) {
			Node pointer = head;
			while (pointer != null) {
				if (wordRecurrence(this, pointer.data) != wordRecurrence(other, pointer.data)) {
					return false;
				}
				pointer = pointer.next;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Helper method for anagrams() that counts the number of times a word appears in a linked list.
	 * @param list The linked list to go through.
	 * @param word The word to be searched.
	 * @return Number of times the given word appears in the given list.
	 */
	public static int wordRecurrence(LinkedAlgorithms list, String word) {
		int result = 0;
		Node pointer = list.head;
		while (pointer != null) {
			if (pointer.data.equals(word)) {
				result++;
			}
			pointer = pointer.next;
		}
		return result;
	}
}
