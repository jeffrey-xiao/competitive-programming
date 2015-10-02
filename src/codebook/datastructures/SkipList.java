class SkipList {
	private Node head;
	private int maxLevel;
	
	SkipList () {
		this.head = new Node(null);
		this.maxLevel = 0;
	}
	
	public void add (Integer val) {
		int newLevel = (int)(Math.random() * (maxLevel + 1));
		if (newLevel > maxLevel) {
			maxLevel++;
			Node newNode = new Node(null);
			newNode.down = head;
			head = newNode;
		}
		Node currentNode = head;
		for (int i = maxLevel; i >= 0; i--) {
			currentNode = add(currentNode, val, i, newLevel);
		}
	}
	private Node add (Node current, Integer val, int currentLevel, int wantedLevel) {
		while (current != null && current.val < val)
			current = current.right;
		if (currentLevel <= wantedLevel) {
			Node newNode = new Node(val);
			newNode.right = current;
			current = newNode;
		}
		if (currentLevel == 0)
			return current;
		current.down = add(current.down, val, currentLevel - 1, wantedLevel);
		return current;
	}
	static class Node {
		Integer val;
		Node down, right;
		Node (Integer val) {
			this.val = val;
			this.down = null;
			this.right = null;
		}
	}	
	public static void main (String[] args) {
		SkipList list = new SkipList();
		
	}
	
}
