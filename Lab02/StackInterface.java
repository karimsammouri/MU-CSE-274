/**
 * Implements a typical stack with generics.
 * @author Norm Krumpe
 *
 * @param <T> the type of items to store in the stack.  Any object type is valid.
 */
public interface StackInterface<T> {
	/**
	 * Pushes the given item onto this stack
	 * @param item the item to be pushed
	 */
	void push(T item);
	
	/**
	 * Removes and returns the top item of this stack.
	 * @return the top item of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	T pop();
	
	/**
	 * Returns the top item of this stack, without removing it.
	 * @return the top item of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	T peek();
	
	/**
	 * Returns true if this stack is empty, and false otherwise
	 * @return true if this stack is empty, and false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Clears all items from this stack
	 */
	void clear();
}
