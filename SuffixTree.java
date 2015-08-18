
import java.util.*;
import java.io.*;

public class SuffixTree {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	// constants
	static final int SHIFT = 'a';
	static final int END = 1 << 30;
	
	// attributes of the input data
	static String input;
	static int len;

	static int currentPos;
	static Node root = new Node(0, 0);

	static Node activeNode = root;
	static int activeEdge = 0;
	static int activeLength = 0;

	static int remainder = 0;
	static int sub = 0;

	static boolean firstNodeCreated;
	static Node lastNodeCreated;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));
		
		input = "abcabxabcd";
		len = input.length();
		// lopping through the input and adding the suffixes one by one
		for (currentPos = 0; currentPos < len; currentPos++)
			addSuffix();

		pr.close();
	}
	static void printTree (Node curr) {
		for (int i = 0; i < 26; i++) {
			if (curr.child[i] != null) {
				System.out.println(input.substring(curr.child[i].start, curr.child[i].end == END ? currentPos + 1 : curr.child[i].end));
				printTree(curr.child[i]);
			}
		}
	}
	static void addSuffix () {
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
				// if a previous node has already been created during this iteration, then we create a suffix link
				addSuffixLink();
			} else {
				// if the current length required is greater than the edge length, then we advance to the next edge down the tree
				int nextLen = activeNode.child[input.charAt(activeEdge) - SHIFT].getLength();
				if (activeLength >= nextLen) {
					activeNode = activeNode.child[input.charAt(activeEdge) - SHIFT];
					activeEdge += nextLen;
					activeLength -= nextLen;
					continue;
				}
				// the current position of the suffix overlaps with a suffix already inserted
				if (input.charAt(activeNode.child[input.charAt(activeEdge) - SHIFT].start + activeLength) == input.charAt(currentPos)) {
					activeLength++;
					// if a previous node has already been created during this iteration, then we create a suffix link
					addSuffixLink();
					break;
				} else {
					// since we found that the current position of the suffix is entirely new, we have to split the edge into two
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
			// if we added a node at the root, we just decrease the active length by one and adjust the active edge so that it is the first character of the next suffix to be added
			if (activeNode == root && activeLength > 0) {
				activeLength--;
				activeEdge = currentPos - remainder + 1;
			} 
			// if it is an internal node, then go to suffix link. If there is no suffix link, then go to root
			else {
				if (activeNode.suffix != null) {
					activeNode = activeNode.suffix;
				} else {
					System.out.println(activeNode.child['a' - SHIFT] + " IN HERE");
					activeNode = root;
				}
			}
		}
	}
	static void addSuffixLink () {
		if (firstNodeCreated == false)
			lastNodeCreated.suffix = activeNode;
		firstNodeCreated = true;
		lastNodeCreated = activeNode;
	}
	static class Node {
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
		public int getLength () {
			return Math.min(currentPos + 1, end) - start;
		}
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}

