/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: This program implements a linked list that keeps the list in order.
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Oct 10,2020
 */

import java.util.Comparator;
import java.util.Iterator;

public class OrderedStockList {

	protected int size;
	protected Node front;
	protected Node rear;

	private Iterator<Stock> iter;
	private Comparator<Stock> comp;

	public OrderedStockList(Comparator<Stock> compChoice) {
		size = 0;
		front = rear = null;
		comp = compChoice;
	}

	public Node getFront() {
		return front;
	}

	public Node getRear() {
		return rear;
	}

	public int getSize() {
		return size;
	}

	// This method insert the item into the list in the order
	// specified by the Comparator comp
	public void insert(Stock item) {

		Node current = front;
		Node prev = null;

		Node node = new Node(item);
		if (size == 0) {
			front = rear = node;
			size++;
			return;
		}
		if (size == 1) { // prev == null
			if (comp.compare(current.getData(), item) > 0) { // insert before
																// front
				node.setNext(current);
				node.setPrev(null);
				current.setPrev(node);
				front = node;
			} else { // insert after front
				front.setNext(node);
				node.setPrev(front);
				rear = node;
			}
			size++;
			return;
		}
		// list size 2 or bigger
		while (current != null) {
			if (comp.compare(current.getData(), item) < 0) {
				prev = current;
				current = current.getNext();
			} else { // insert before this node
				if (prev == null) {
					node.setNext(front);
					front.setPrev(node);
					front = node;
				} else {
					node.setPrev(prev);
					node.setNext(current);
					prev.setNext(node);
					current.setPrev(node);
				}
				size++;
				return;
			}
		}

		// insert at end of list
		rear.setNext(node);
		node.setPrev(rear);
		rear = node;
		size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String result = "";
		iter = iterator();

		// Add code to create the string result from this list.
		// Use the iterator iter to traverse the list.
		while (iter.hasNext()) {
			Stock stock = iter.next();
			result += stock.toString() + "\n";
		}

		return result;
	}

	Iterator<Stock> iterator() {

		// Add the methods needed for a standard iterator
		return new Iterator<Stock>() {
			private Node prevPos = null;
			private Node currPos = null;
			private Node nextPos = front;

			public boolean hasNext() {
				return (nextPos != null);
			}

			public Stock next() {
				if (!hasNext()) {
					throw new IndexOutOfBoundsException();
				}
				Stock hold = nextPos.getData();
				if (currPos != null) {
					prevPos = currPos;
				}
				currPos = nextPos;
				nextPos = nextPos.getNext();
				return hold;
			}

			public void remove() {
				if (currPos == null) {
					return;
				} else {
					if (prevPos == null) {
						front = nextPos;
						currPos = null;
						if (front == null) {
							rear = null;
						}
					} else {
						prevPos.setNext(nextPos);
						currPos = null;
					}
					size--;
				}
			}
		};
	}
}
