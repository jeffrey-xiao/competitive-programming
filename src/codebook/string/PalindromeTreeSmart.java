package codebook.string;

import java.util.ArrayList;

public class PalindromeTreeSmart {

	static final int SHIFT = 'a';

	private ArrayList<Node> tree;
	private ArrayList<Character> seq;
	private Node root, zero;
	private int ans = 0;

	// initializing the tree and the roots
	PalindromeTreeSmart () {
		tree = new ArrayList<Node>();
		seq = new ArrayList<Character>();
		// the root has a length of negative one for convenience
		root = new Node(0, -1);
		// the "zero root" will compute the palindromes of even length
		zero = new Node(0, 0);
		root.suffixLink = root.smartLink = root;
		zero.suffixLink = zero.smartLink = root;
		tree.add(root);
	}

	class Node {
		// suffix link goes to the next suffix
		// smart link goes to the next suffix with a different character
		private int num, len;
		private Node suffixLink;
		private Node smartLink;
		private Node[] nextLink;

		Node () {
			this(0, 0);
		}

		Node (int num, int len) {
			this.num = num;
			this.len = len;
			this.suffixLink = null;
			this.smartLink = null;
			this.nextLink = new Node[26];
		}
	}

	private Node getMatchingSuffix (Node n) {
		// while we haven't found the new suffix, we travel down the smart and suffix links
		while (seq.size() - n.len - 2 < 0 || seq.get(seq.size() - n.len - 2) != seq.get(seq.size() - 1)) {
			// if the next suffix link leads to an appropriate suffix, then we go down the suffix link
			// else we go down the smart link
			if (seq.size() - n.suffixLink.len - 2 >= 0 && seq.get(seq.size() - n.suffixLink.len - 2) == seq.get(seq.size() - 1))
				n = n.suffixLink;
			else
				n = n.smartLink;
		}
		return n;
	}

	public void addCharacter (char c) {
		seq.add(c);
		// find the suffix to extend so that when we add character c it will be a palindrome
		Node curr = getMatchingSuffix(tree.get(tree.size() - 1));
		Node next = curr.nextLink[c - SHIFT];
		// after we found our suffix to extend on, we check if the new palindrome has already been created
		if (next == null) {
			// if it hasn't then we just create a new palindrome node
			next = (curr.nextLink[c - SHIFT] = new Node());
			next.len = curr.len + 2;
			// if the length is one, we create a suffix link to the zero root to process any even length palindromes in the future
			// else we find the next palindrome suffix and create a suffix link to it
			if (next.len == 1)
				next.suffixLink = zero;
			else
				next.suffixLink = getMatchingSuffix(curr.suffixLink).nextLink[c - SHIFT];
			int len1 = seq.size() - next.suffixLink.len - 1;
			int len2 = seq.size() - next.suffixLink.suffixLink.len - 1;
			// if the suffix begins with the same character, then the smart link will be the smart link of the suffix
			// else the smart link will just point the suffix
			if (len1 >= 0 && len2 >= 0 && len1 < seq.size() && len2 < seq.size() && seq.get(len1) == seq.get(len2))
				next.smartLink = next.suffixLink.smartLink;
			else
				next.smartLink = next.suffixLink;
			next.num = next.suffixLink.num + 1;
		}
		// add the current palindrome node to the tree and increment the answer by the number of palindromes contained by the node
		tree.add(next);
		ans += next.num;
	}

	public void deleteCharacter () {
		// subtract answer by the number of palindromes contained by the node to delete
		ans -= tree.get(tree.size() - 1).num;
		// delete the node and the previous character added
		tree.remove(tree.size() - 1);
		seq.remove(tree.size() - 1);
	}

	public static void main (String[] args) {
		PalindromeTreeSmart m = new PalindromeTreeSmart();
		m.addCharacter('a');
		System.out.println(m.ans);
		m.addCharacter('a');
		System.out.println(m.ans);
		m.deleteCharacter();
		m.deleteCharacter();
		m.addCharacter('a');
		System.out.println(m.ans);
	}
}
