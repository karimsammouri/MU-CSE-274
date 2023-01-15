/**
 * Student: Karim R. Sammouri
 * Instructor: Dr. James Kiper
 * Class: CSE 274 - F
 * Date: Sep 17, 2020
 * Description: Lab #5 - Bag ADT
 * Attribution: Everything below, except those given by the course, 
 * is written by me.
 */

public class Bag<T> implements BagInterface<T>, CollectionInterface<T> {

	private Node<T> front;
	private Node<T> rear;
	int size;

	public Bag() {
		front = null;
		rear = null;
		size = 0;
	}
	// Attempts to add element to this collection.
	// Returns true if successful, false otherwise.
	public boolean add(T element) {
		if (isEmpty()) {
			Node<T> node = new Node<T>(element);
			front = rear = node;
		} else {
			Node<T> node = new Node<T>(element, null, rear);
			rear.next = node;
			rear = node;
		}
		size++;
		return true;
	}

	// Returns an element e from this collection such that e.equals(target).
	// If no such e exists, returns null.
	public T get(T target) {
		if (isEmpty()) {
			return null;
		}
		T result;
		Node<T> pointer = front;
		while (pointer != null) {
			if (pointer.data.equals(target)) {
				result = pointer.data;
				return result;
			}
			pointer = pointer.next;
		}
		return null;
	}

	// Returns true if this collection contains an element e such that
	// e.equals(target), otherwise returns false.
	public boolean contains(T target) {
		Node<T> pointer = front;
		while (pointer != null) {
			if (pointer.data.equals(target)) {
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	// Removes an element e from this collection such that e.equals(target)
	// and returns true. If no such e exists, returns false.
	public boolean remove(T target) {
		if (isEmpty()) {
			return false;
		}
		Node<T> pointer = front;
		while (pointer != null) {
			if (pointer.data.equals(target)) {
				Node prevNode = pointer.prev;
				Node nextNode = pointer.next;
				if (prevNode == null) {
					front = pointer.next;
					front.prev = null;
				} else if (nextNode == null) {
					rear = pointer.prev;
					rear.next = null;
				} else {
					prevNode.next = nextNode;
					nextNode.prev = prevNode;
				}
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

	// Returns true if this collection is full, otherwise returns false.
	public boolean isFull() {
		return false;
	}

	// Returns true if this collection is empty, otherwise returns false.
	public boolean isEmpty() {
		return (size == 0);
	}

	// Returns the number of elements in this collection.
	public int size() {
		return size;
	}

	// If this bag is not empty, removes and returns a random element of the
	// bag, otherwise returns null.
	public T grab() {
		if (isEmpty()) {
			return null;
		}
		T result;
		if (size == 1) {
			result = front.data;
			clear();
			return result;
		}
		int rand = (int) (Math.random() * size);
		Node<T> pointer = front;
		for (int i = 1; i <= rand; i++) {
			pointer = pointer.next;
		}
		result = pointer.data;
		Node prevNode = pointer.prev;
		Node nextNode = pointer.next;
		if (prevNode == null) {
			front = pointer.next;
			front.prev = null;
		} else if (nextNode == null) {
			rear = pointer.prev;
			rear.next = null;
		} else {
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		}
		size--;
		return result;
	}

	// Returns a count of all elements e in this collection such that
	// e.equals(target).
	public int count(T target) {
		return 0;
	}

	// Removes all elements e from this collection such that e.equals(target)
	// and returns the number of elements removed.
	public int removeAll(T target) {
		return 0;
	}

	// Empties this bag so that it contains zero elements.
	public void clear() {
		front = rear = null;
		size = 0;
	}

	private class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;

		private Node(T item) {
			data = item;
			next = prev = null;
		}

		private Node(T item, Node<T> newNext, Node<T> newPrev) {
			data = item;
			next = newNext;
			prev = newPrev;
		}
	}
}
