package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Kth_Ancestor {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			int p = readInt();
			int root = 0;
			Node[] tree = new Node[100001];
			for (int i = 0; i <= 100000; i++)
				tree[i] = new Node(i);
			for (int i = 0; i < p; i++) {
				int a = readInt();
				int b = readInt();
				if (b == 0) {
					root = a;
					continue;
				}
				tree[a].addParent(tree[b]);
				tree[b].addChild(tree[a]);
			}
			tree[root] = dfs(tree[root]);
			int q = readInt();
			for (int i = 0; i < q; i++) {
				int command = readInt();
				if (command == 0) {
					int a = readInt();
					int b = readInt();
					if (b == 0)
						continue;
					tree[b].addParent(tree[a]);
					tree[b].computeParent();
					tree[a].addChild(tree[b]);
				} else if (command == 1) {
					int a = readInt();
					tree[a] = new Node(a);
				} else {
					int a = readInt();
					int k = readInt();
					out.println(tree[a].getParent(k));
				}
			}
		}

		out.close();
	}

	static Node dfs (Node n) {
		n.computeParent();
		for (int i = 0; i < n.child.size(); i++)
			n.child.set(i, dfs(n.child.get(i)));
		return n;
	}

	static class Node {
		Node[] par;
		ArrayList<Node> child = new ArrayList<Node>();
		int index;

		Node (int index) {
			this.index = index;
			child = new ArrayList<Node>();
			par = new Node[14];
		}

		void addChild (Node n) {
			child.add(n);
		}

		void addParent (Node n) {
			par[0] = n;
		}

		void computeParent () {
			for (int i = 1; i < 14; i++)
				if (par[i - 1] != null)
					par[i] = par[i - 1].par[i - 1];
		}

		int getParent (int k) {
			if (k == 0)
				return index;
			for (int i = 13; i >= 0; i--) {
				if (k >= 1 << i) {
					if (par[i] == null) {
						return 0;
					} else
						return par[i].getParent(k - (1 << i));
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
