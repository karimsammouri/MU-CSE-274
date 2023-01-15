public class Node  {
	private Stock data;
	private Node prev, next;

	public Stock getData() {
		return data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public Node getPrev() {
		return prev;
	}
	
	public void setNext( Node newNext) {
		next = newNext;
	}
	
	public void setPrev(Node newPrev) {
		prev = newPrev;
	}
	
	public Node(Stock info) {
		this.data = info;
		prev = null;
		next = null;
	}
}
