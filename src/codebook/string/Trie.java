/*
 * A trie is an ordered tree data structure that is used to store a set of dynamic set or associative array where the keys are usually strings.
 * All the descendants of a node have a common prefix of the string associated with that node, and the root is initialized with an empty string.
 *
 * Time complexity:
 *  - Add word: O(N)
 */

package codebook.string;

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
			// if the node does not exist, then create it
			if (curr.child[word.charAt(i) - SHIFT] == null)
				curr.child[word.charAt(i) - SHIFT] = new Node();
			// iteratively traverse the trie
			curr = curr.child[word.charAt(i) - SHIFT];
		}
		// mark the last node as a leaf node
		curr.isLeaf = true;
	}

	public void print () {
		print(root, "");
	}

	private void print (Node n, String curr) {
		for (int i = 0; i < 26; i++)
			if (n.child[i] != null)
				print(n.child[i], curr + (char)(i + SHIFT));
		if (n.isLeaf)
			System.out.println(curr);
	}
}
