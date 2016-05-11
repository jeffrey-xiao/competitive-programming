package contest.misc;

import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] id, sz;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
		
		out.close();
	}

	static int find (int u) {
		return id[u] == u ? u : (id[u] = find(id[u]));
	}
	
	static void merge (int u, int v) {
		if (sz[u] > sz[v]) {
			sz[u] += sz[v];
			id[v] = u;
		} else {
			sz[v] += sz[u];
			id[u] = v;
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

