package contest.ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2005_Rivers_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int count;
	static long dp[][][];
	static int color = 1;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		Node[] nodes = new Node[n + 1];
		for (int x = 0; x <= n; x++)
			nodes[x] = new Node();
		nodes[0].index = 0;
		for (int x = 1; x <= n; x++) {
			nodes[x].index = x;
			int trees = readInt();
			int a = readInt();
			int length = readInt();
			nodes[x].length = length;
			nodes[x].trees = trees;
			nodes[a].child.add(nodes[x]);
		}
		Queue<Node> moves = new LinkedList<Node>();
		Stack<Integer> order = new Stack<Integer>();
		moves.offer(nodes[0]);
		while (!moves.isEmpty()) {
			Node curr = moves.poll();
			order.push(curr.index);
			for (Node child : curr.child)
				moves.offer(child);
		}

		for (int z = order.size(); z > 0; z--) {
			int x = order.pop();
			int size = nodes[x].child.size();
			Node[] prev = new Node[size];
			for (int y = 0; y < size; y++)
				prev[y] = nodes[x].child.get(y).clone();
			for (int gap = (int) (Math.floor(Math.log(size - 1 == 0 ? 1
					: size - 1) / Math.log(2))); gap > 0; gap--) {
				size = prev.length;
				Node[] level = new Node[(size + 1) / 2];
				for (int y = 0; y < level.length; y++) {
					level[y] = new Node(0, 0);
					if (y * 2 + 1 < size) {
						level[y].child.add(prev[y * 2].clone());
						level[y].child.add(prev[y * 2 + 1].clone());
					} else
						level[y].child.add(prev[y * 2].clone());
				}
				prev = Arrays.copyOf(level, level.length);
			}
			nodes[x].child.clear();
			for (int y = 0; y < prev.length; y++) {
				nodes[x].child.add(prev[y].clone());
			}
		}
		traverse(nodes[0], 0);
		dp = new long[count][k + 1][count];
		for (int x = 0; x < count; x++)
			for (int y = 0; y <= k; y++)
				for (int z = 0; z < count; z++)
					dp[x][y][z] = -1;
		System.out.println(compute(nodes[0], k, nodes[0]));
	}

	private static long compute (Node n, int k, Node last) {
		if (dp[n.id][k][last.id] != -1)
			return dp[n.id][k][last.id];
		long min = 100000000;
		if (n.child.size() == 0) {
			return k >= 1 ? 0 : n.trees * (n.length - last.length);
		} else if (n.child.size() == 1) {
			min = Math.min(
					(n.length - last.length) * n.trees
							+ compute(n.child.get(0), k, last), min);
			if (k >= 1)
				min = Math.min(min, compute(n.child.get(0), k - 1, n));
		} else {
			int cost = n.trees * (n.length - last.length);
			for (int x = 0; x <= k; x++)
				min = Math.min(min, cost + compute(n.child.get(0), x, last)
						+ compute(n.child.get(1), k - x, last));
			for (int x = 0; x < k; x++)
				min = Math.min(
						min,
						compute(n.child.get(0), x, n)
								+ compute(n.child.get(1), k - x - 1, n));

		}
		dp[n.id][k][last.id] = min;
		return min;
	}

	private static void traverse (Node n, int totalLength) {
		n.id = count++;
		n.length = totalLength;
		if (n.child.size() >= 1) {
			traverse(n.child.get(0), n.length + n.child.get(0).length);
		}
		if (n.child.size() == 2) {
			traverse(n.child.get(1), n.length + n.child.get(1).length);
		}
	}

	static class Node {
		int length;
		int trees;
		int id;
		int index;
		ArrayList<Node> child = new ArrayList<Node>();

		Node () {
		}

		Node (int length, int trees) {
			this.length = length;
			this.trees = trees;
		}

		@Override
		public Node clone () {
			Node newNode = new Node(length, trees);
			for (Node n : child)
				newNode.child.add(n);
			return newNode;
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