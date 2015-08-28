package codebook.string;

import java.util.*;

public class PalindromeTreeSimple {
	
	static final int SHIFT = 'a';
	
	private ArrayList<Node> tree;
	private ArrayList<Character> seq;
	private Node root, zero;
	private int ans = 0;
	
	PalindromeTreeSimple () {
		tree = new ArrayList<Node>();
		seq = new ArrayList<Character>();
		root = new Node(0, -1);
		zero = new Node(0, 0);
		root.suffixLink = root;
		zero.suffixLink = root;
		tree.add(root);
	}
	
	class Node {
		private int num, len;
		private Node suffixLink;
		private Node[] nextLink;
		
		Node () {
			this(0, 0);
		}
		
		Node (int num, int len) {
			this.num = num;
			this.len = len;
			this.suffixLink = null;
			this.nextLink = new Node[26];
		}
	}
	
	public void addCharacter (char c) {
		seq.add(c);
		Node curr = tree.get(tree.size()-1);
		while (seq.size() - curr.len - 2 < 0 || seq.get(seq.size() - curr.len - 2) != c)
			curr = curr.suffixLink;
		Node next = curr.nextLink[c - SHIFT];
		if (next == null) {
			next = (curr.nextLink[c - SHIFT] = new Node());
			next.len = curr.len + 2;
			if (next.len == 1) {
				next.suffixLink = zero;
				next.num = 1;
			} else {
				curr = curr.suffixLink;
				while (seq.size() - curr.len - 2 < 0 || seq.get(seq.size() - curr.len - 2) != c)
					curr = curr.suffixLink;
				curr = curr.nextLink[c - SHIFT];
				next.suffixLink = curr;
				next.num = curr.num + 1;
			}
		}
		ans += next.num;
		tree.add(next);
	}
	
	public void deleteCharacter () {
		ans -= tree.get(tree.size()-1).num;
		tree.remove(tree.size()-1);
		seq.remove(tree.size()-1);
	}
	
	public static void main (String[] args) {
		PalindromeTreeSimple m = new PalindromeTreeSimple();
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
