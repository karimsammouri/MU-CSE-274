public class Node  {
		private President data;
		private Node prev, next;

		public President getData() {
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
		
		public Node(President info) {
			this.data = info;
			prev = null;
			next = null;
		}
	}