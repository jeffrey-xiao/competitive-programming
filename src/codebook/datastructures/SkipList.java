package codebook.datastructures;

public class SkipList {
	
	private Node head;
	private int maxLevel;
	
	private static final double PROBABILITY = 0.5;
	
	SkipList () {
		this.head = new Node(null);
		this.head.right = new Node(null);
		this.maxLevel = 0;
	}
	
	public void remove (Integer val) {
		remove(head, val);
	}
	
	private void remove (Node curr, Integer val) {
		while (curr.right.val != null && curr.right.val < val)
			curr = curr.right;
		if (curr.right != null && curr.right.val == val)
			curr.right = curr.right.right;
		if (curr.down != null)
			remove(curr.down, val);
	}
	
	public boolean contains (Integer val) {
		return contains(head, val);
	}
	
	private boolean contains (Node curr, Integer val) {
		while (curr.right.val != null && curr.right.val < val)
			curr = curr.right;
		if (curr.right != null && curr.right.val == val)
			return true;
		if (curr.down != null)
			return contains(curr.down, val);
		return false;
	}
	
	public boolean add (Integer val) {
		if (contains(val))
			return false;
		int newLevel = 0;
		while (Math.random() < PROBABILITY)
			newLevel++;
		while (newLevel > maxLevel) {
			maxLevel++;
			Node newNode = new Node(null);
			newNode.right = new Node(null);
			newNode.down = head;
			head = newNode;
		}
		head = add(head, val, maxLevel, newLevel);
		return true;
	}
	private Node add (Node curr, Integer val, int currentLevel, int wantedLevel) {
		Node start = curr;
		while (curr.right.val != null && curr.right.val < val)
			curr = curr.right;
		if (currentLevel <= wantedLevel) {
			Node newNode = new Node(val);
			newNode.right = curr.right;
			curr.right = newNode;
		}
		if (currentLevel != 0) {
			curr.down = add(curr.down, val, currentLevel - 1, wantedLevel);
			if (currentLevel <= wantedLevel)
				curr.right.down = find(curr.down, val);
		}
		return start;
	}
	private Node find (Node current, Integer val) {
		while (current.val != val)
			current = current.right;
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
//		for (int i = 0; i < 5; i++) {
//			int insert = (int)(Math.random()*100); 
//
//			System.out.println("ADDED " + list.add(insert));
//
//			
//		}
//		System.out.println("FINISHED ADDING");
//		list.add(10);
//		list.add(1);
//		System.out.println(list.contains(10));
//		System.out.println(list.contains(1));
//		list.remove(10);
//		list.remove(1);
//		System.out.println(list.contains(10));
//		System.out.println(list.contains(1));

		long c = System.currentTimeMillis();
		for (int x = 0; x < 1000000; x++) {
			int ran = (int) (Math.random() * (1 << 30)) + 5;
			list.add(ran);
		}
		// t.traverse(root);
		list.add(1);
		System.out.println(list.contains(1));
		System.out.println(list.contains(2));
		list.remove(1);
		System.out.println(list.contains(1));
		System.out.println(System.currentTimeMillis() - c);
		
//		Node currentNode = list.head;
//		for (int j = 0; j <= list.maxLevel; j++) {
//			print(currentNode);
//			currentNode = currentNode.down;
//		}
	}
	static void print (Node currentNode) {
		while (currentNode.right.val != null) {
			System.out.print(currentNode.right.val + " ");
			currentNode = currentNode.right;
		}
		System.out.println();
	}
	
}
