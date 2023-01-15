/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: This program implements an iterator for the OrderedStockList class.
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Oct 10, 2020
 */

import java.util.Iterator;

public class StockListIterator implements Iterator<Stock> {

	// Add code to implement an iterator for Stocks.
	// This will be similar to the iterator in OrderedStockList
	// with a few small changes.
	
	private OrderedStockList list;
	private Node prevPos = null;
	private Node currPos = null;
	private Node nextPos;
	
	StockListIterator(OrderedStockList list) {
		this.list = list;
		nextPos = list.front;
	}
	
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
				list.front = nextPos;
				currPos = null;
				if (list.front == null) {
					list.rear = null;
				}
			} else {
				prevPos.setNext(nextPos);
				currPos = null;
			}
			list.size--;
		}
	}
}