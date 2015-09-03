package codebook.string;

import java.util.*;

public class PalindromeTreeSmart {

	static final int SHIFT = 'a';

	private ArrayList<Node> tree;
	private ArrayList<Character> seq;
	private Node root, zero;
	private int ans = 0;

	PalindromeTreeSmart () {
		tree = new ArrayList<Node>();
		seq = new ArrayList<Character>();
		root = new Node(0, -1);
		zero = new Node(0, 0);
		root.suffixLink = root.smartLink = root;
		zero.suffixLink = zero.smartLink = root;
		tree.add(root);
	}

	class Node {
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
		while (seq.size() - n.len - 2 < 0 || seq.get(seq.size() - n.len - 2) != seq.get(seq.size() - 1)) {
			if (seq.size() - n.suffixLink.len - 2 >= 0 && seq.get(seq.size() - n.suffixLink.len - 2) == seq.get(seq.size() - 1))
				n = n.suffixLink;
			else
				n = n.smartLink;
		}
		return n;
	}

	public void addCharacter (char c) {
		seq.add(c);
		Node curr = getMatchingSuffix(tree.get(tree.size() - 1));
		Node next = curr.nextLink[c - SHIFT];
		if (next == null) {
			next = (curr.nextLink[c - SHIFT] = new Node());
			next.len = curr.len + 2;
			if (next.len == 1)
				next.suffixLink = zero;
			else
				next.suffixLink = getMatchingSuffix(curr.suffixLink).nextLink[c - SHIFT];
			int len1 = seq.size() - next.suffixLink.len - 1;
			int len2 = seq.size() - next.suffixLink.suffixLink.len - 1;
			if (len1 >= 0 && len2 >= 0 && len1 < seq.size() && len2 < seq.size() && seq.get(len1) == seq.get(len2))
				next.smartLink = next.suffixLink.smartLink;
			else
				next.smartLink = next.suffixLink;
			next.num = next.suffixLink.num + 1;
		}
		tree.add(next);
		ans += next.num;
	}

	public void deleteCharacter () {
		ans -= tree.get(tree.size() - 1).num;
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
