package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_Can_Shahir_Even_Get_There {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, a, b;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static boolean[] vis;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		a = readInt() - 1;
		b = readInt() - 1;

		vis = new boolean[n];

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < m; i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		out.println(dfs(a) ? "GO SHAHIR!" : "NO SHAHIR!");
		out.close();
	}

	static boolean dfs (int u) {
		vis[u] = true;
		if (u == b)
			return true;
		for (int v : adj.get(u))
			if (!vis[v] && dfs(v))
				return true;
		return false;
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
