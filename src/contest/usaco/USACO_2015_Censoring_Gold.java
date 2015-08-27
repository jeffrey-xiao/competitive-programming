package contest.usaco;

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

public class USACO_2015_Censoring_Gold {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SHIFT = 'a';
	static Node root;
	static Node[] dp = new Node[100001];
	static char[] res = new char[100001];
	static int end = 0;

	public static void main (String[] args) throws IOException {
		StringBuilder s = new StringBuilder(readLine());
		root = new Node(0, 0);
		root.parent = root;
		int n = readInt();
		for (int i = 0; i < n; i++)
			addWord(readLine());
		computeFall();

		Node currState = root;
		dp[0] = root;
		for (int i = 0; i < s.length(); i++) {
			res[end++] = s.charAt(i);

			char curr = res[end - 1];
			Node startState = currState;
			while (currState.child[curr - SHIFT] == null && currState != root)
				currState = currState.fall;
			if (currState == root) {
				if (currState.child[curr - SHIFT] != null)
					currState = currState.child[curr - SHIFT];
				else
					currState = root;
			} else {
				currState = currState.child[curr - SHIFT];
			}
			startState.child[curr - SHIFT] = currState;
			Node other = currState;
			if (other.endDepth != -1) {
				// System.out.println("Length is " + other.depth + " at " + i);
				currState = dp[end - other.endDepth];

				end = (end - other.endDepth);
				// System.out.println(currState.depth + " " + currState.index +
				// " " + res);
			}
			other = other.fall;
			dp[end] = currState;
		}
		for (int i = 0; i < end; i++)
			System.out.print(res[i]);
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
			//
			Node fall = curr.parent.fall;
			while (fall.child[curr.index] == null && fall != root)
				fall = fall.fall;
			curr.fall = fall.child[curr.index];
			if (curr.fall == null || curr.fall == curr)
				curr.fall = root;
			if (curr.fall.endDepth != -1)
				curr.endDepth = curr.fall.endDepth;
		}
	}

	static class Node {
		int depth, index;
		Node[] child;
		Node fall;
		Node parent;
		int endDepth = -1;
		ArrayList<Integer> c;

		Node (int depth, int index) {
			this.depth = depth;
			this.index = index;
			child = new Node[26];
			c = new ArrayList<Integer>();
			for (int i = 0; i < 26; i++)
				child[i] = null;
		}
	}

	private static void addWord (String s) {
		Node curr = root;
		while (true) {
			if (curr.depth == s.length()) {
				curr.endDepth = curr.depth;
				return;
			}
			char c = s.charAt(curr.depth);
			if (curr.child[c - SHIFT] == null) {
				curr.child[c - SHIFT] = new Node(curr.depth + 1, c - SHIFT);
				curr.child[c - SHIFT].parent = curr;
				curr.c.add(c - SHIFT);
			}
			curr = curr.child[c - SHIFT];
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
