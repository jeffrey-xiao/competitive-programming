package codebook.string;

public class SuffixTree {

	// constants
	static final int SHIFT = 'a';
	static final int END = 1 << 30;

	// attributes of the input data
	private String input;
	private int len;

	private int currentPos;
	private Node root;

	private Node activeNode;
	private int activeEdge;
	private int activeLength;

	private int remainder;

	private boolean firstNodeCreated;
	private Node lastNodeCreated;

	SuffixTree (String input) {
		this.input = input;
		initialize();
	}

	public void setString (String string) {
		this.input = string;
		initialize();
	}

	public String getString () {
		return input;
	}

	private void initialize () {
		this.len = input.length();
		this.root = new Node(0, 0);
		this.activeEdge = 0;
		this.activeLength = 0;
		this.remainder = 0;
		this.activeNode = root;
		this.currentPos = 0;
		this.lastNodeCreated = null;
		this.firstNodeCreated = false;
		// looping through the input and adding the suffixes one by one
		for (currentPos = 0; currentPos < len; currentPos++)
			addSuffix();
	}

	public void printTree () {
		printTree(root);
	}

	private void printTree (Node curr) {
		for (int i = 0; i < 26; i++) {
			if (curr.child[i] != null) {
				System.out.println(input.substring(curr.child[i].start, curr.child[i].end == END ? input.length() : curr.child[i].end));
				printTree(curr.child[i]);
			}
		}
	}

	private void addSuffix () {
		// how many previous suffixes do we need to compute
		remainder++;
		firstNodeCreated = true;
		while (remainder > 0) {
			// if the active length is zero, then we reset the active edge
			if (activeLength == 0)
				activeEdge = currentPos;
			// creating a new leaf node
			if (activeNode.child[input.charAt(activeEdge) - SHIFT] == null) {
				activeNode.child[input.charAt(activeEdge) - SHIFT] = new Node(currentPos, END);
				// if a previous node has already been created during this
				// iteration, then we create a suffix link
				addSuffixLink();
			} else {
				// if the current length required is greater than the edge
				// length, then we advance to the next edge down the tree
				int nextLen = activeNode.child[input.charAt(activeEdge) - SHIFT].getLength();
				if (activeLength >= nextLen) {
					activeNode = activeNode.child[input.charAt(activeEdge) - SHIFT];
					activeEdge += nextLen;
					activeLength -= nextLen;
					continue;
				}
				// the current position of the suffix overlaps with a suffix
				// already inserted
				if (input.charAt(activeNode.child[input.charAt(activeEdge) - SHIFT].start + activeLength) == input.charAt(currentPos)) {
					activeLength++;
					// if a previous node has already been created during this
					// iteration, then we create a suffix link
					addSuffixLink();
					break;
				} else {
					// since we found that the current position of the suffix is
					// entirely new, we have to split the edge into two
					// the start position doesn't change
					activeNode.child[input.charAt(activeEdge) - SHIFT].start = activeNode.child[input.charAt(activeEdge) - SHIFT].start;
					// the end position becomes where it branches off
					activeNode.child[input.charAt(activeEdge) - SHIFT].end = activeNode.child[input.charAt(activeEdge) - SHIFT].start + activeLength;
					// adding both branches now
					activeNode.child[input.charAt(activeEdge) - SHIFT].child[input.charAt(activeNode.start + activeLength) - SHIFT] = new Node(activeNode.child[input.charAt(activeEdge) - SHIFT].start + activeLength, END);
					activeNode.child[input.charAt(activeEdge) - SHIFT].child[input.charAt(currentPos) - SHIFT] = new Node(currentPos, END);
					addSuffixLink();
				}
			}
			remainder--;
			// if we added a node at the root, we just decrease the active
			// length by one and adjust the active edge so that it is the first
			// character of the next suffix to be added
			if (activeNode == root && activeLength > 0) {
				activeLength--;
				activeEdge = currentPos - remainder + 1;
			}
			// if it is an internal node, then go to suffix link. If there is no
			// suffix link, then go to root
			else {
				if (activeNode.suffix != null) {
					activeNode = activeNode.suffix;
				} else {
					activeNode = root;
				}
			}
		}
	}

	private void addSuffixLink () {
		if (firstNodeCreated == false)
			lastNodeCreated.suffix = activeNode;
		firstNodeCreated = true;
		lastNodeCreated = activeNode;
	}

	private class Node {
		// represents the string [start, end)
		int start, end;
		Node[] child;
		Node suffix;

		Node (int start, int end) {
			child = new Node[26];
			suffix = null;
			this.start = start;
			this.end = end;
		}

		private int getLength () {
			return Math.min(currentPos + 1, end) - start;
		}
	}

	public static void main (String[] args) {
		SuffixTree st = new SuffixTree("abcabxabcd");
		st.printTree(st.root);
	}
}
