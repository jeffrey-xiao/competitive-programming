package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Even_Tree {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int[] sz;
	static int ans;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		int n = readInt();
		int m = readInt();
		sz = new int[n];
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < m; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		dfs(0, -1);
		count(0, -1);
		out.println(ans);
		out.close();
	}
	static void count (int curr, int prev) {
		for (Integer next : adj.get(curr)) {
			if (next != prev) {
				count(next, curr);
				if (sz[next] % 2 == 0)
					ans++;
			}
		}
	}
	static void dfs (int curr, int prev) {
		sz[curr] = 1;
		for (Integer next : adj.get(curr)) {
			if (next != prev) {
				dfs(next, curr);
				sz[curr] += sz[next]; 
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

