import java.util.Iterator;

public class PresidentListIterator implements Iterator<President> {
	
	OrderedList presidents;

	Node nextNode;
	
	public PresidentListIterator(OrderedList list ) {
		nextNode = list.getFront();
	}
	
	public boolean hasNext() {
		return true;
	}
	
	public President next() {
		President result = null;
		
		return result;
	}
}

