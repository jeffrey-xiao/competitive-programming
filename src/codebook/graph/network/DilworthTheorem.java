/*
 * Maximum size anti-chain in N^3 time using Kuhn's algorithm and Dilworth's Theorem
 */

package codebook.graph.network;

import java.util.*;
import java.io.*;

public class DilworthTheorem {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<HashSet<Integer>> adj;
	static int[] prev;
	static boolean[] v;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		n = readInt();
		m = readInt();

		prev = new int[n];
		v = new boolean[n];
		
		adj = new ArrayList<HashSet<Integer>>();
		
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<Integer>());
			prev[i] = -1;
		}
		
		for (int i = 0; i < m; i++)
			adj.get(readInt() - 1).add(readInt() - 1);

		
		for (int i = 0; i < n; i++)
			if (!v[i])
				build(i);
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			v = new boolean[n];
			ans += match(i) ? 1 : 0;
		}
		out.println(n - ans);
		out.close();
	}
	
	static boolean match (int i) {
		for (int j : adj.get(i))
			if (!v[j]) {
				v[j] = true;
				if (prev[j] == -1 || match(prev[j])) {
					prev[j] = i;
					return true;
				}
			}
		return false;
	}
	
	static void build (int i) {
		v[i] = true;
		HashSet<Integer> toAdd = new HashSet<Integer>();
		for (int j : adj.get(i)) {
			if (!v[j])
				build(j);
			toAdd.addAll(adj.get(j));
		}
		adj.get(i).addAll(toAdd);
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

