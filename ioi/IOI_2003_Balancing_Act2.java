package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2003_Balancing_Act2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int min = Integer.MAX_VALUE;
	static Node[] nodes;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		boolean[] added = new boolean[n];
		nodes = new Node[n];
		added[0] = true;
		Node u = new Node(null, -1);
		u.size = 0;
		nodes[0] = new Node(u, 0);
		ArrayList<int[]> edges = new ArrayList<int[]>();
		for (int x = 0; x < n - 1; x++)
			edges.add(new int[] {readInt() - 1, readInt() - 1});
		while (!edges.isEmpty()) {
			for (int x = edges.size() - 1; x >= 0; x--) {
				int[] curr = edges.get(x);
				if (added[curr[0]]) {
					// System.out.println("YE " + curr[0] + " " + curr[1]);
					nodes[curr[1]] = new Node(nodes[curr[0]], curr[1]);
					nodes[curr[0]].child.add(nodes[curr[1]]);
					added[curr[1]] = true;
					edges.remove(x);
				} else if (added[curr[1]]) {
					// System.out.println(curr[0] + " YE " + curr[1]);
					nodes[curr[0]] = new Node(nodes[curr[1]], curr[0]);
					nodes[curr[1]].child.add(nodes[curr[0]]);
					added[curr[0]] = true;
					edges.remove(x);
				}
			}
		}
		update(nodes[0]);
		int pos = 0;
		for (int x = 0; x < nodes.length; x++) {
			int curr = getMin(nodes[x]);
			if (curr < min) {
				min = curr;
				pos = x;
			}
		}
		System.out.println(pos + 1 + " " + min);
	}

	private static int getMin (Node node) {
		int max = nodes[0].size - node.size;
		for (int x = 0; x < node.child.size(); x++) {
			max = Math.max(node.child.get(x).size, max);
		}
		return max;
	}

	private static int update (Node node) {
		int size = 1;
		for (int x = 0; x < node.child.size(); x++) {
			// System.out.println(node.child.get(x).id);
			size += update(node.child.get(x));
		}
		node.size = size;
		return size;
	}

	static class Node {
		Node parent;
		int size;
		int id;
		ArrayList<Node> child;

		Node (Node parent, int id) {
			this.parent = parent;
			this.id = id;
			child = new ArrayList<Node>();
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
