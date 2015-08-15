import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Aho_Corasick {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SHIFT = 'a';
	static Node root;

	public static void main (String[] args) throws IOException {
		root = new Node(0, 0);
		
		root.addWord("apple");
		root.addWord("banana");
		root.parent = root;
		computeFall();
		search("askfjasjfklasjfasfjpasfjawofjoawjfopjapapple");
	}
	private static void computeFall () {
		Queue<Node> q = new LinkedList<Node>();
		// the fall of the root is the root
		root.fall = root;
		q.offer(root);
		while (!q.isEmpty()) {
			Node curr = q.poll();
			// push the children of the current node into the queue
			for (Integer i : curr.c)
				q.offer(curr.child[i]);
			// if the fall has already been computed then skip the current node
			if (curr.fall != null)
				continue;
			// let the fall first be the parent's fall
			Node fall = curr.parent.fall;
			// while the current fall node does not have a child with the value
			// of the current character, then go fall back
			while (fall.child[curr.index] == null && fall != root)
				fall = fall.fall;
			// the fall node of the current node will be the child of the fall
			// node we found
			curr.fall = fall.child[curr.index];
			// if the current node's fall is null or if it is the current node,
			// then set to root
			if (curr.fall == null || curr.fall == curr)
				curr.fall = root;
		}
	}

	private static void search (String s) {
		Node currState = root;
		for (int i = 0; i < s.length(); i++) {
			char curr = s.charAt(i);
			// loop until we have a match or we are at the root
			while (currState.child[curr - SHIFT] == null && currState != root)
				currState = currState.fall;
			// handle the root case
			if (currState == root) {
				if (currState.child[curr - SHIFT] != null)
					currState = currState.child[curr - SHIFT];
			} else {
				currState = currState.child[curr - SHIFT];
			}
			// falling back to check for other shorter matches
			Node other = currState;
			while (other != root) {
				if (other.isEnd) {
					System.out.println("Length is " + other.depth + " at " + i);
				}
				other = other.fall;
			}
		}
	}

	static class Node {
		int depth, index;
		Node[] child;
		Node fall;
		Node parent;
		boolean isEnd = false;
		ArrayList<Integer> c;

		Node (int depth, int index) {
			this.depth = depth;
			this.index = index;
			child = new Node[26];
			c = new ArrayList<Integer>();
			for (int i = 0; i < 26; i++)
				child[i] = null;
		}

		void addWord (String s) {
			if (depth == s.length()) {
				isEnd = true;
				return;
			}
			char curr = s.charAt(depth);
			if (child[curr - SHIFT] == null) {
				child[curr - SHIFT] = new Node(depth + 1, curr - SHIFT);
				child[curr-SHIFT].parent = this;
				c.add(curr - SHIFT);
			}

			child[curr - SHIFT].addWord(s);
		}

		void printWord (String curr) {
			if (isEnd)
				System.out.println(curr);
			for (Integer i : c) {
				child[i].printWord(curr + (char) (i + SHIFT));
			}
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
