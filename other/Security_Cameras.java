package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Security_Cameras {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<Integer> intersection = new ArrayList<Integer>();
	static boolean[][] adj;
	static boolean[] visited;
	static int[] prev;
	static int l;
	static int r;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Node root = null;
		ArrayList<Interval> ys = new ArrayList<Interval>();
		for (int x = 0; x < n; x++) {
			int x1 = readInt();
			int y1 = readInt();
			int x2 = readInt();
			int y2 = readInt();
			if (y1 == y2) {
				root = insert(root, x1, x2, y1, l);
				l++;
			} else if (x1 == x2) {
				ys.add(new Interval(y1, y2, x1));
				r++;
			}
		}
		// System.out.println(l + " " + r);
		adj = new boolean[l][r];
		prev = new int[r];
		for (int x = 0; x < ys.size(); x++) {
			Interval curr = ys.get(x);
			search(root, curr.x, curr.y1, curr.y2);
			for (int y = 0; y < intersection.size(); y++) {
				adj[intersection.get(y)][x] = true;
				// System.out.println("INTERSECTION HERE " + intersection.get(y)
				// + " " + x);
				// System.out.println(adj[intersection.get(y)][x]);
			}
			intersection.clear();
		}
		for (int x = 0; x < l; x++) {
			for (int y = 0; y < r; y++) {
				System.out.print(adj[x][y] ? "1 " : "0 ");
			}
			System.out.println();
		}
		int count = 0;
		for (int x = 0; x < r; x++)
			prev[x] = -1;
		for (int x = 0; x < l; x++) {
			visited = new boolean[r];
			count += hungary(x) ? 1 : 0;
		}
		System.out.println(count + (n - 2 * count));
	}

	static class Interval {
		int y1;
		int y2;
		int x;

		Interval (int y1, int y2, int x) {
			this.y1 = y1;
			this.y2 = y2;
			this.x = x;
		}
	}

	private static boolean hungary (int x) {
		for (int y = 0; y < r; y++) {
			if (adj[x][y] && !visited[y]) {
				visited[y] = true;
				if (prev[y] == -1 || hungary(prev[y])) {
					prev[y] = x;
					return true;
				}
			}
		}
		return false;
	}

	public static void search (Node n, int x, int y1, int y2) {
		if (n == null)
			return;
		System.out.printf("Node left: %d; right: %d; y: %d\n", n.x1, n.x2, n.y);
		System.out.printf("Search left: %d; right: %d; x: %d and Max: %d\n",
				y1, y2, x, n.max);
		if (n.x1 <= x && x <= n.x2 && y1 <= n.y && n.y <= y2)
			intersection.add(n.index);

		if (n.left != null && n.left.max >= x)
			search(n.left, x, y1, y2);
		if (n.right != null)
			search(n.right, x, y1, y2);
	}

	public static Node insert (Node n, int x1, int x2, int y, int index) {
		if (n == null) {
			return new Node(x1, x2, y, index);
		}
		double compare = x1 - n.x1;
		if (compare < 0) {
			n.left = insert(n.left, x1, x2, y, index);
			n.max = Math.max(n.max, n.left.max);
		} else if (compare == 0)
			n.x1 = x1;
		else {
			n.right = insert(n.right, x1, x2, y, index);
			n.max = Math.max(n.max, n.right.max);
		}
		return n;
	}

	static class Node {
		int x1, x2, max, index;
		int y;
		Node left, right;

		Node (int x1, int x2, int y, int index) {
			this.x1 = x1;
			this.x2 = x2;
			this.max = x2;
			this.y = y;
			this.index = index;
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
