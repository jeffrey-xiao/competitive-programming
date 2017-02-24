package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		long total = 0;
		Tree t = new Tree();
		for (int x = 0; x < n; x++) {
			int next = readInt();
			int temp = t.add(next);
			total += temp + 1;
		}
		float f = total / (float)n;
		if (Double.parseDouble(String.format("%.2f", f * 100.0 - (int)(f * 100.0))) == 0.5 && (int)((f * 10 - (int)(f * 10)) * 10) % 2 == 0)
			f -= 0.01;
		System.out.printf("%.2f", f);
	}

	static class Node {
		int score;
		int rank;
		Node left;
		Node right;

		public Node (int s) {
			score = s;
		}
	}

	static class Tree {
		Node root;

		public int add (int score) {
			int rank = 0;
			if (root == null) {
				root = new Node(score);
			} else {
				Node next = root;
				while (true) {
					if (score < next.score) {
						rank += next.rank + 1;
						if (next.left == null) {
							next.left = new Node(score);
							return rank;
						} else
							next = next.left;
					} else if (score >= next.score) {
						next.rank++;
						if (next.right == null) {
							next.right = new Node(score);
							return rank;
						} else
							next = next.right;
					}
				}
			}
			return 0;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
