/* 
 * A Huffman tree is used to data compression where elements which occur more often are assigned a shorter compressed value.
 *
 * Time complexity: O(N log N)
 */

package codebook.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

	// requires a dictionary that maps a character value to its frequency
	HuffmanTree (Map<Character, Integer> map) {
		buildTree(map);
	}

	private Node root = null;
	private HashMap<Character, Node> leaves = new HashMap<Character, Node>();

	private void buildTree (Map<Character, Integer> map) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (Map.Entry<Character, Integer> entry : map.entrySet())
			pq.offer(new Node(entry.getKey(), entry.getValue()));
		while (pq.size() >= 2) {
			Node n1 = pq.poll();
			Node n2 = pq.poll();
			Node par = new Node(null, n1.freq + n2.freq, n1, n2);
			n1.par = par;
			n2.par = par;
			if (n1.value != null)
				leaves.put(n1.value, n1);
			if (n2.value != null)
				leaves.put(n2.value, n2);
			pq.offer(par);
		}
		root = pq.poll();
	}

	public void traverse () {
		traverse(root);
	}

	private void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.value + " " + n.freq);
		traverse(n.right);
	}

	@SuppressWarnings ("unused")
	private class Node implements Comparable<Node> {
		private Character value;
		private Integer freq;
		private Node left, right, par;

		Node (Character value, Integer freq) {
			this(value, freq, null, null);
		}

		Node (Character value, Integer freq, Node left, Node right) {
			this.value = value;
			this.freq = freq;
			this.left = left;
			this.right = right;
			this.par = null;
		}

		@Override
		public int compareTo (Node o) {
			return freq - o.freq;
		}
	}

	public static void main (String[] args) {
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		hm.put('e', (int) (Math.random() * 10));
		hm.put('n', (int) (Math.random() * 10));
		hm.put('o', (int) (Math.random() * 10));
		hm.put('u', (int) (Math.random() * 10));
		hm.put('a', (int) (Math.random() * 10));
		hm.put('t', (int) (Math.random() * 10));
		hm.put('m', (int) (Math.random() * 10));
		hm.put('i', (int) (Math.random() * 10));
		hm.put('x', (int) (Math.random() * 10));
		hm.put('p', (int) (Math.random() * 10));
		hm.put('h', (int) (Math.random() * 10));
		hm.put('s', (int) (Math.random() * 10));
		hm.put('r', (int) (Math.random() * 10));
		HuffmanTree tree = new HuffmanTree(hm);
		tree.traverse();
	}
}
