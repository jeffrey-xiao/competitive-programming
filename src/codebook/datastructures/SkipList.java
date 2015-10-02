class SkipList {
	private Node head;
	private int maxLevel;
	
	SkipList () {
		head = new Node(null);
		maxLevel = 0;
	}
	
	public void add (Integer val) {
		
	}
	
	static class Node {
		Integer val;
		LinkedList<Node> next;
		Node (Integer val) {
			this.val = val;
			this.next = new LinkedList<Node>();
		}
	}	
	
}
