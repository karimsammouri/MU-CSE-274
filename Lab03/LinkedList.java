/**
 * 
 * @author Karim R. Sammouri
 *
 * @date September 3, 2020
 * 
 *       Purpose: this is a utility class to implement a generic linked list
 *       that can be used to implement other complex data structures like
 *       stacks, queues, etc. Course: CSE 274 Data Abstraction and Data
 *       Structures
 * 
 */

public class LinkedList<T> {

	private class Node<T> {
		protected T data;
		protected Node<T> next;

		Node(T data) {
			this.data = data;
			next = null;
		}
		public void setData(T data) {
			this.data = data;
		}
		public T getData() {
			return this.data;
		}
		public void setLink(Node<T> next) {
			this.next = next;
		}
		public Node<T> getLink() {
			return this.next;
		}
	}

	private int size;
	private Node<T> head;

	/*
	 * Constructs an empty list
	 */
	public LinkedList() {
		size = 0;
		head = null;
	}

	/*
	 * Returns a string representation of this collection.
	 */
	@Override
	public String toString() {
		String result = "";
		Node<T> pointer = head;
		while (pointer != null) {
			result += pointer.getData() + " ";
			pointer = pointer.getLink();
		}
		result = result.trim();
		return result;
	}

	/*
	 * Appends the specified element to the front of this list.
	 */
	public void addFront(T item) {
		Node<T> node = new Node<T>(item);
		node.setLink(head);
		head = node;
		size++;
	}

	/*
	 * Appends the specified element to the rear of this list.
	 */
	public void addRear(T item) {
		Node<T> pointer = head;
		Node<T> node = new Node<T>(item);
		if (head == null) {
			head = node;
		} else {
			while (pointer.next != null) {
				pointer = pointer.getLink();
			}
			pointer.setLink(node);
		}
		size++;
	}

	/*
	 * Returns the number of elements in this list.
	 */
	public int size() {
		return size;
	}

	/*
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/*
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/*
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(T item) {
		return (indexOf(item) != -1);
	}

	/*
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 */
	public int indexOf(T item) {
		int index = -1;
		Node<T> pointer = head;
		while (pointer != null) {
			index++;
			if (pointer.getData().equals(item)) {
				return index;
			}
			pointer = pointer.getLink();
		}
		return -1;
	}

	/*
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 */
	public int lastIndexOf(T item) {
		int index = -1;
		int result = index;
		Node<T> pointer = head;
		while (pointer != null) {
			index++;
			if (pointer.getData().equals(item)) {
				result = index;
			}
			pointer = pointer.getLink();
		}
		return result;
	}

	/*
	 * Returns the element at the specified position in this list. Throws:
	 * IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	 * index >= size())
	 */
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		} else {
			Node<T> pointer = head;
			int pointerIndex = -1;
			while (pointer != null) {
				pointerIndex++;
				if (index == pointerIndex) {
					return pointer.getData();
				}
				pointer = pointer.getLink();
			}
		}
		return null;
	}


	/*
	 * Replaces the element at the specified position in this list with the
	 * specified element. Returns: the element previously at the specified
	 * position Throws: IndexOutOfBoundsException - if the index is out of range
	 * (index < 0 || index >= size())
	 */
	public T set(int index, T value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		} else {
			Node<T> pointer = head;
			int pointerIndex = -1;
			T previous;
			while (pointer != null) {
				pointerIndex++;
				if (index == pointerIndex) {
					previous = pointer.getData();
					pointer.setData(value);
					return previous;
				}
				pointer = pointer.getLink();
			}
		}
		return null;
	}

	/*
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns: the element that was removed from the list Throws:
	 * IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	 * index >= size())
	 */
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		} else {
			Node<T> pointer = head;
			int pointerIndex = -1;
			T previous;
			while (pointer != null) {
				pointerIndex++;
				if (pointerIndex == index - 1) {
					previous = pointer.getLink().getData();
					pointer.setLink(pointer.getLink().getLink());
					return previous;
				}
				pointer = pointer.getLink();
			}
		}
		return null;
}
}