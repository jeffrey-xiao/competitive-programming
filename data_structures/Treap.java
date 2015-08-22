package data_structures;
import java.util.*;

public class Treap {
	public static void main (String[] args) {
		Treap t = new Treap();
		long c = System.currentTimeMillis();
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int x = 0; x < 10; x++) {
			int ran = (int) (Math.random() * (20)) + 5;
			hs.add(ran);
			t.add(ran);
		}
		System.out.println(hs.size());
		t.traverse(t.root);
		System.out.println();
		t.add(1);
		System.out.println(t.contains(t.root, 1));
		System.out.println(t.contains(t.root, 2));
		t.remove(1);
		System.out.println(t.contains(t.root, 1));
		System.out.println(System.currentTimeMillis() - c);
		System.out.println(t.range(10, 15));
	}
	// root of the tree
	Node root = null;

	// object representing the nodes of the tree
	static class Node {
		Integer key;
		Integer value;
		Double priority;
		Node left, right;
		
		Node (int key, int value) {
			this.key = key;
			this.value = value;
          	priority = Math.random();
		}
		
		Node (int key) {
			this.key = key;
			this.value = key;
			priority = Math.random();
		}
	}
	/**
	 * @param k represents the key to remove
	 */
	public void remove (Integer k) {
		root = remove(root, k);
	}
	/**
	 * @param k represents the key to add; in this case the value of the key is equal to the key
	 */
	public void add (Integer k) {
		root = add(root, k, k);
	}
	/**
	 * @param k represents the key to add
	 * @param v represents the value of the key
	 */
	public void add (Integer k, Integer v) {
		root = add(root, k, v);
	}
	
	/**
	 * @param k represents the key to check for
	 */
	public boolean contains (Integer k) {
		return contains(root, k);
	}
	/**
	 * @param k represents the key to find
	 * @return the value associated with the key
	 */
	public Integer get (Integer k) {
		return get(root, k);
	}
	/**
	 * @param loK represents the lower bound of the range
	 * @param hiK represents the upper bound of the range
	 * @return an iterable object of all the elements in the range present in the tree
	 */
	public Iterable<Integer> range (Integer loK, Integer hiK) {
		Queue<Integer> res = new ArrayDeque<Integer>();
		range(root, loK, hiK, res);
      		return res;
	}
	
	// in order traversal of nodes
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.print(n.key + " ");
		traverse(n.right);
	}
	
	// auxiliary function for range
	private void range (Node n, Integer loK, Integer hiK, Queue<Integer> res) {
		if (n == null)
			return;
		if (n.key >= loK)
			range(n.left, loK, hiK, res);
		if (loK <= n.key && n.key <= hiK)
			res.offer(n.key);
		if (n.key <= hiK)
			range(n.right, loK, hiK, res);
	}
	
	// auxiliary function for contains
	private boolean contains (Node n, Integer k) {
		if (n == null)
			return false;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			return contains(n.left, k);
		else if (cmp > 0)
			return contains(n.right, k);
		return true;
	}

	// auxiliary function for get
	private Integer get (Node n, Integer k) {
		if (n == null)
			return null;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			return get(n.left, k);
		else if (cmp > 0)
			return get(n.right, k);
		return n.value;
	}
	
	// auxiliary function to delete
	private Node remove (Node n, Integer k) {
		if (n == null)
			return n;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			n.left = remove(n.left, k);
		else if (cmp > 0)
			n.right = remove(n.right, k);
		else {
			n = merge(n.left, n.right);
		}
		return n;
	}

	// auxiliary function to merge
	private Node merge (Node t1, Node t2) {
		if (t1 == null)
			return t2;
		else if (t2 == null)
			return t1;

		Node newRoot = null;
		if (t1.priority > t2.priority) {
			t1.left = merge(t1.left, t1.right);
			newRoot = t1;
			newRoot.right = t2;
		} else {
			t2.right = merge(t2.left, t2.right);
			newRoot = t2;
			newRoot.left = t1;
		}
		return newRoot;
	}

	// auxiliary function to insert
	private Node add (Node n, Integer k, Integer v) {
		if (n == null)
			return new Node(k, v);
		int cmp = k.compareTo(n.key);
		// going left
		if (cmp < 0) {
			n.left = add(n.left, k, v);
			if (n.priority < n.left.priority) {
				n = rotateRight(n);
			}
		}
		// going right
		else if (cmp > 0) {
			n.right = add(n.right, k, v);
			if (n.priority < n.right.priority)
				n = rotateLeft(n);
		}
		// else the value already exists
		return n;
	}

	// rotate left
	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		return x;
	}

	// rotate right
	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		return x;
	}
}
