/**
 * @author Karim Sammouri
 */

import java.util.NoSuchElementException;

/**
 * A class that implements the ADT queue by using a resizable circular array.
 */
public final class ArrayQueue<T> implements QueueInterface<T> {
	private T[] queue; // Circular array of queue entries
	private int frontIndex; // Index of front entry

	private int size;
	private static final int DEFAULT_CAPACITY = 4;

	/**
	 * Constructs a queue with default initial array capacity
	 */
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a queue with a specified initial array capacity
	 * 
	 * @param initialCapacity the desired initial array capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int initialCapacity) {
		this.queue = (T[]) new Object[initialCapacity];
		this.frontIndex = 0;
		this.size = 0;
	}

	@Override
	public void enqueue(T newEntry) {
		ensureCapacity();
		queue[(frontIndex + size) % queue.length] = newEntry;
		size++;
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		else
			return queue[frontIndex];
	}

	@Override
	public T dequeue() {
		T frontData = peek();
		queue[frontIndex] = null;
		frontIndex = (frontIndex + 1) % queue.length;
		size--;
		return frontData;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		if (!isEmpty()) { // deallocates only the used portion
			for (int i = 0; i < size; i++) {
				queue[(frontIndex + i) % queue.length] = null;
			}
		}

		size = 0;
	} // end clear

	// Doubles the size of the array queue if it is full.
	private void ensureCapacity() {
		if (size == queue.length) {
			T[] oldQueue = queue;

			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[2 * size];
			queue = tempQueue;

			for (int i = 0; i < size; i++) {
				tempQueue[i] = oldQueue[(frontIndex + i) % oldQueue.length];
			}

			frontIndex = 0;
		}
	}
}