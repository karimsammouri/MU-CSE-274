/**
 * @author Karim Sammouri
 */

import java.util.NoSuchElementException;

/**
 * A class that implements the ADT queue by using a chain of linked nodes that
 * has references to the front and end of the chain. Adds will be after the last
 * node. Removes will be from the front node.
 */
public final class LinkedQueue<T> implements QueueInterface<T> {
	private Node front; // References node at front of queue
	private Node rear; // References node at back of queue

	public LinkedQueue() {
		front = null;
		rear = null;
	} // end default constructor

	public void enqueue(T newEntry) {
		Node node = new Node(newEntry);
		if (isEmpty()) {
			 front = rear = node;
		} else {
		rear.next = node;
		rear = node;
		}
	} // end enqueue

	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		else
			return front.data;
	} // end getFront

	public T dequeue() {
		T frontData = peek();
		front = front.next;
		if (front == null)
			rear = null;
		return frontData;
	} // end dequeue

	public boolean isEmpty() {
		// TODO
		return front == null;
	} // end isEmpty

	public void clear() {
		// TODO
		front = rear = null;
	} // end clear

	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
}