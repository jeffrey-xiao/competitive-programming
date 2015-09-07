package codebook.graph;

import java.util.*;
import java.io.*;

public class LowestCommonAncestorDp {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int[] depth;
	static int[][] pa;
	static int n, q, ln;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// number of nodes
		n = readInt();
		// number of queries
		q = readInt();
		ln = (int)(Math.ceil(Math.log(n) / Math.log(2)));
		
		depth = new int[n];
		pa = new int[n][ln];
		
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			for (int j = 0; j < ln; j++)
				pa[i][j] = -1;
		}
		
		for (int i = 0; i < n-1; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		dfs(0, 0, -1);
		
		for (int i = 1; i < ln; i++)
			for (int j = 0; j < n; j++)
				if (pa[j][i-1] != -1)
					pa[j][i] = pa[pa[j][i-1]][i-1];
		
		for (int i = 0; i < q; i++)
			out.println(getLca(readInt()-1, readInt()-1) + 1);
		
		out.close();
	}

	static int getLca (int i, int j) {
		if (depth[i] < depth[j]) {
			int temp = i;
			i = j;
			j = temp;
		}
		for (int k = ln - 1; k >= 0; k--)
			if (pa[i][k] != -1 && depth[pa[i][k]] >= depth[j])
				i = pa[i][k];
		
		if (i == j)
			return i;
		for (int k = ln - 1; k >= 0; k--)
			if (pa[i][k] != -1 && pa[j][k] != -1 && pa[i][k] != pa[j][k]) {
				i = pa[i][k];
				j = pa[j][k];
			}
		return pa[i][0];
	}
	
	static void dfs (int i, int d, int prev) {
		depth[i] = d;
		pa[i][0] = prev;
		for (int j : adj.get(i))
			if (j != prev)
				dfs(j, d+1, i);
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

