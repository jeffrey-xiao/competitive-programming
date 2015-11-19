/*
 * A radix trie is a memory optimized trie where each node can represent more than one character. It supports lowercase letters.
 */

package codebook.string;

public class RadixTrie {
	private static final int SHIFT = 'a';
	private Node root = new Node();

	class Node {
		private Node[] child;
		private boolean isLeaf;
		private String value;

		Node () {
			child = new Node[26];
			isLeaf = false;
			value = "";
		}
	}

	public void addWord (String word) {
		if (word == null || word.length() == 0)
			throw new IllegalArgumentException();
		root.child[word.charAt(0) - SHIFT] = addWord(root.child[word.charAt(0) - SHIFT], word);
	}

	private Node addWord (Node n, String word) {
		if (n == null)
			n = new Node();
		if (word.length() == 0) {
			n.isLeaf = true;
			return n;
		}
		if (n.value.length() == 0) {
			n.value = word;
			n.isLeaf = true;
			return n;
		}
		for (int i = 0; i < Math.min(n.value.length(), word.length()); i++) {
			if (n.value.charAt(i) != word.charAt(i)) {
				Node node = new Node();
				node.value = n.value.substring(0, i);
				node.child[n.value.charAt(i) - SHIFT] = n;
				n.value = n.value.substring(i);
				node.child[word.charAt(i) - SHIFT] = addWord(node.child[word.charAt(i) - SHIFT], word.substring(i));
				node.isLeaf = false;
				return node;
			}
		}
		if (word.length() > n.value.length()) {
			int i = word.charAt(n.value.length()) - SHIFT;
			n.child[i] = addWord(n.child[i], word.substring(n.value.length()));
		} else if (word.length() < n.value.length()) {
			Node node = new Node();
			node.value = n.value.substring(0, word.length());
			n.value = n.value.substring(word.length());
			node.isLeaf = true;
			return node;
		}
		return n;
	}

	public void print () {
		print(root, "");
	}

	private void print (Node n, String curr) {
		if (n.isLeaf)
			System.out.println(curr);
		for (int i = 0; i < 26; i++)
			if (n.child[i] != null) {
				print(n.child[i], curr + n.child[i].value);
			}
	}

	public static void main (String[] args) {
		RadixTrie t = new RadixTrie();
		t.addWord("romane");
		t.addWord("romanus");
		t.addWord("romulus");
		t.addWord("rubens");
		t.addWord("ruber");
		t.addWord("rubicon");
		t.addWord("rubicundus");
		t.print();
	}
}
