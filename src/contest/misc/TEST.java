package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TEST extends Thread {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int index;

	int source = 0;
	int sink = 0;
	ArrayList<ArrayList<Edge>> adj;
	int[] h = new int[n];
	int[] e = new int[n];
	int[][] f = new int[n][n];
	int[][] c = new int[n][n];

	static int[] tree1;
	static int[] tree2;
	static int n;

	private static void update (int[] tree, int x, int val) {
		while (x <= n) {
			tree[x] += val;
			x += (x & -x);
		}
	}

	static void update (int x, int y, int val) {
		update(tree1, x, val);
		update(tree1, y + 1, -val);
		update(tree2, x, val * (x - 1));
		update(tree2, y + 1, -val * y);
	}

	// auxiliary query function
	private int query (int[] tree, int x) {
		int sum = 0;
		while (x > 0) {
			sum += tree[x];
			x -= (x & -x);
		}
		return sum;
	}

	// return sum from 1 to x
	private int query (int x) {
		return query(tree1, x) * x - query(tree2, x);
	}

	// return sum from x to y
	int query (int x, int y) {
		return query(y) - query(x - 1);
	}

	private static int threadcounter = 0;

	@Override
	public void run () {
		System.out.println(threadcounter++);
	}

	class A {
		public void robustMethod (int[] intArray) {
		}
	}

	class B extends A {
		public void rubustMethod (int[] intArray) throws IOException {
		}
	}

	public static void main (String[] args) throws IOException {
		for (int i = 0; i < 1 << 5; i++)
			System.out.println(Integer.toString(i, 2));
		
		// List a = new ArrayList<Integer>();
		// a.add(null);
		// System.out.println(a.get(0));
		/*
		 * int n = 55; String[] H =
		 * {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"}; String[] T =
		 * {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
		 * System.out.println(H[n%1000/100]+T[(n%1000)/10%10]+O[n%10]); int a =
		 * 7; int b = 1; a=a^b; b = b^a; a = a^b;
		 * 
		 * System.out.println(a + " " + b);
		 */
		// int[] a = {34,64,16,81,59,15,72,47,58,37 };
		// selection(a);
		// a =new int[]{51,46,91,52,21,57,40,15,42,48 };
		// shell(a);

		// Node root = null;
		// String input = next();
		// root = insert(root, input);
		// inorder(root);
		// int val[] = {0, 60, 100, 120};
		// int wt[] = {0, 10, 20, 30};
		// int W = 50;
		// int n = val.length-1;
		// System.out.println(knapSack(W, wt, val, n));
		// System.out.println(minimize("abc", "topabcoder"));
		// int n = readInt();
		// int[] i = new int[n];
		// for(int x = 0; x < n; x++)
		// i[x] = readInt();
		// Arrays.sort(i);
		// for(int x = 0; x < n; x++)
		// System.out.print(i[x] + " ");
		// System.out.println();
		// System.out.println(new TEST().solve(1, 3, 7));
		/*
		 * int n = 10; // represents the number of nodes int source = 0; //
		 * represents the id of source int sink = 0; // represents the id of
		 * sink int[] h = new int[n]; // represents the heights int[] e = new
		 * int[n]; // represents the excess flow int[][] f = new int[n][n]; //
		 * represents the flow between two vertexes
		 * 
		 * // the graph ArrayList<ArrayList<Edge>> adj = new
		 * ArrayList<ArrayList<Edge>>();
		 * 
		 * // initialization of arrays for clarity purposes for(int x = 0; x <
		 * n; x++){ for(int y = 0; y < n; y++) f[x][y] = 0; h[x] = 0; e[x] = 0;
		 * } h[source] = n; // set the height of source to n
		 * 
		 * // saturated pushes from the source for(Edge next : adj.get(source)){
		 * f[source][next.dest] = next.capacity; f[next.dest][source] =
		 * -next.capacity; e[next.dest] -= next.capacity; e[source] =
		 * -next.capacity; }
		 * 
		 * String s = "yabbadabbado"; ArrayList<String> st = new
		 * ArrayList<String>(); for(int x = 0; x < s.length(); x++)
		 * st.add(s.substring(x, s.length())); Collections.sort(st); for(String
		 * str : st){ System.out.println(str); }
		 */
	}

	void push (int x, int y) {
		int flow = Math.min(e[x], c[x][y] - f[x][y]);
		f[x][y] += flow;
		f[y][x] -= flow;
		e[x] -= flow;
		e[y] += flow;
	}

	void relabel (int x) {
		int currMax = -1;
		for (Edge e : adj.get(x))
			if (c[x][e.dest] - f[x][e.dest] > 0)
				if (currMax == -1 || currMax > h[e.dest])
					currMax = h[e.dest];
		h[x] = currMax + 1;
	}

	static class Edge {
		int dest, capacity;

		Edge (int dest, int capacity) {
			this.dest = dest;
			this.capacity = capacity;
		}
	}

	static int minimize (String a, String b) {
		int minCount = Integer.MAX_VALUE;
		for (int x = 0; x < b.length() - a.length() + 1; x++) {
			int count = 0;
			for (int y = 0; y < a.length(); y++) {
				if (a.charAt(y) != b.charAt(y + x)) {
					count++;
				}
			}
			minCount = Math.min(minCount, count);
		}
		return minCount;
	}

	@SuppressWarnings ("unused")
	private static int knapSack (int W, int wt[], int val[], int n) {
		// W = capacity of backpack; wt = weights of items; val = values of
		// items; n = # of items
		int[][] matrix = new int[n + 1][W + 1]; // build a matrix n+1 by W+1
		// everything uses one-based indexing
		for (int i = 0; i <= n; i++)
			for (int j = 1; j <= W; j++)
				matrix[i][j] = 0;
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= W; w++) {
				matrix[i][w] = Math.max(matrix[i][w], matrix[i - 1][w]);
				if (wt[i] + w <= W)
					matrix[i][w + wt[i]] = Math.max(val[i] + matrix[i][w], matrix[i][w]);
			}
		}
		return matrix[n][W];
	}

	public static void a () {
		int i = 10;
		int j = 10;
		int k = 10;
		int[][][] a = new int[i + 1][j + 1][k + 1];
		int[][][] sum = new int[i + 1][j + 1][k + 1];
		for (int x = 1; x <= i; x++) {
			for (int y = 1; y <= j; y++) {
				for (int z = 1; z <= k; z++) {
					sum[x][y][z] = a[x][y][z];
					sum[x][y][z] += sum[x - 1][y][z];
					sum[x][y][z] += sum[x][y - 1][z];
					sum[x][y][z] += sum[x][y][z - 1];
					sum[x][y][z] -= sum[x - 1][y - 1][z];
					sum[x][y][z] -= sum[x - 1][y][z - 1];
					sum[x][y][z] -= sum[x][y - 1][z - 1];
					sum[x][y][z] += sum[x - 1][y - 1][z - 1];
				}
			}
		}
	}

	static class Node {
		Node right, left;
		Character value;

		Node (Character value) {
			this.value = value;
		}
	}

	@SuppressWarnings ("unused")
	private static Node insert (Node n, String s) {
		if (n == null) {
			if (index >= s.length() || s.charAt(index) == '#') {
				index++;
				return null;
			}
			n = new Node(s.charAt(index));
			index++;
		}
		n.left = insert(n.left, s);
		n.right = insert(n.right, s);
		return n;
	}

	@SuppressWarnings ("unused")
	private static void inorder (Node n) {
		if (n == null)
			return;
		inorder(n.left);
		System.out.print(n.value + " ");
		inorder(n.right);
	}

	@SuppressWarnings ("unused")
	private static void shell (int[] a) {
		for (int x = 0; x < a.length; x++) {
			for (int y = x + 4; y < a.length; y += 4) {
				if (a[x] > a[y])
					swap(a, x, y);
			}
		}
		for (int i : a)
			System.out.print(i + " ");
	}

	@SuppressWarnings ("unused")
	private static void selection (int[] a) {
		for (int x = 0; x < 4; x++) {
			int min = Integer.MAX_VALUE;
			int index = 0;
			for (int y = x; y < a.length; y++) {
				if (a[y] < min) {
					min = a[y];
					index = y;
				}
			}
			swap(a, x, index);
		}
		for (int i : a)
			System.out.print(i + " ");
		System.out.println();
	}

	@SuppressWarnings ("unused")
	private static void insertion (int[] a) {
		int index = 0;
		for (int x = 1; x < a.length; x++) {
			for (int y = x; y >= 1; y--) {
				if (a[y] < a[y - 1] && index < 6) {
					swap(a, y, y - 1);
					index++;
				} else
					break;
			}
		}
		for (int i : a)
			System.out.print(i + " ");
		System.out.println();
	}

	private static void swap (int[] a, int x, int y) {
		// a[x] = (a[y]=(a[x]=a[y]^a[x])^a[y])^a[x];
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;

	}

	int x = 0, y = 0, d = 0;

	public void extendedEuclid (int a, int b) {
		if (b == 0) {
			x = 1;
			y = 0;
			d = a;
			return;
		}
		extendedEuclid(b, a % b);
		int x1 = y;
		int y1 = x - (a / b) * y;
		x = x1;
		y = y1;
	}

	public int solve (int a, int b, int m) {
		extendedEuclid(a, m);
		if (b % d != 0)
			return -1;
		return x * a * (b / d);
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
