// Simple trie implementation that supports lower-case letters
package codebook.datastructures;

public class Trie {
	private static final int SHIFT = 'a';
	private Node root = new Node();
	class Node {
		private Node[] child;
		private boolean isLeaf;
		Node () {
			child = new Node[26];
			isLeaf = false;
		}
	}
	public void addWord (String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			if (curr.child[word.charAt(i) - SHIFT] == null)
				curr.child[word.charAt(i) - SHIFT] = new Node();
			curr = curr.child[word.charAt(i) - SHIFT];
		}
		curr.isLeaf = true;
	}
	public void print () {
		print(root, "");
	}
	private void print(Node n, String curr) {
		for (int i = 0; i < 26; i++)
			if (n.child[i] != null)
				print(n.child[i], curr + (char)(i + SHIFT));
		if (n.isLeaf)
			System.out.println(curr);
	}
}

