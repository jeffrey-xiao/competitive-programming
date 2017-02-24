package contest.hackerrank;

import java.util.*;

import java.io.*;

public class University_Codesprint_2_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, Q, K;
	static int[] l, r;
	static String search;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		Q = readInt();
		K = readInt();

		search = next();

		l = new int[M];
		r = new int[M];

		for (int i = 0; i < M; i++) {
			l[i] = readInt();
			r[i] = readInt();
		}

		for (int i = 0; i < Q; i++) {
			String in = next();
			int a = readInt();
			int b = readInt();
			AhoCorasick s = new AhoCorasick();
			for (int j = a; j <= b; j++) {
				s.addWord(in.substring(l[j], r[j] + 1));
			}
			s.computeFall();
			s.search(search);
			out.println(s.ans);
		}

		out.close();
	}

	static class AhoCorasick {

		// constant to represent the shift from the ASCII value to the proper index
		private static final int SHIFT = 'a';

		// Object representing the root of the search tree
		private Node root = new Node(0, 0);

		AhoCorasick () {
			root = new Node(0, 0);
			root.parent = root;
		}

		public void addWord (String s) {
			root.addWord(s);
		}

		int ans = 0;

		public void computeFall () {
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

		public void search (String s) {
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
						ans += other.cnt;
					}
					other = other.fall;
				}
			}
		}

		class Node {
			int depth, index;
			Node[] child;
			Node fall;
			Node parent;
			boolean isEnd = false;
			int cnt;
			ArrayList<Integer> c;

			Node (int depth, int index) {
				this.depth = depth;
				this.index = index;
				child = new Node[26];
				c = new ArrayList<Integer>();
				for (int i = 0; i < 26; i++)
					child[i] = null;
			}

			private void addWord (String s) {
				// marking it as a leaf node if it is the end of the word
				if (depth == s.length()) {
					isEnd = true;
					cnt++;
					return;
				}
				char curr = s.charAt(depth);
				// creating a new node if it does not already exist
				if (child[curr - SHIFT] == null) {
					child[curr - SHIFT] = new Node(depth + 1, curr - SHIFT);
					child[curr - SHIFT].parent = this;
					c.add(curr - SHIFT);
				}
				// recursively add the rest of the word
				child[curr - SHIFT].addWord(s);
			}

			// auxiliary method to print out the words in the trie
			public void printWord (String curr) {
				if (isEnd)
					System.out.println(curr);
				for (Integer i : c) {
					child[i].printWord(curr + (char)(i + SHIFT));
				}
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
