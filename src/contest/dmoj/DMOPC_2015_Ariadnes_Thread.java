package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Ariadnes_Thread {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, Q, LN = 17, cnt = 0;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static ArrayList<TreeSet<Integer>> occ = new ArrayList<TreeSet<Integer>>();
	static int[] first, depth, size;
	static int[][] pa;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		first = new int[N];
		pa = new int[LN][N];
		depth = new int[N];
		size = new int[N];
		
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Integer>());
			occ.add(new TreeSet<Integer>());
		}

		for (int i = 0; i < LN; i++)
			for (int j = 0; j < N; j++)
				pa[i][j] = -1;
		
		Arrays.fill(first, 1 << 30);
		
		for (int i = 0; i < N; i++) {
			int sz = readInt();
			for (int j = 0; j < sz; j++) {
				int val = readInt() - 1;
				adj.get(i).add(val);
				pa[0][val] = i;
			}
		}
		
		for (int i = 1; i < LN; i++)
			for (int j = 0; j < N; j++)
				if (pa[i - 1][j] != -1)
					pa[i][j] = pa[i - 1][pa[i - 1][j]];
		
		dfs(0, -1, 0);
		
		Q = readInt();
		
		for (int i = 0; i < Q; i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			int root = lca(u, v);
			if (first[v] < first[u]) {
				out.println(first[v] - first[root] + (occ.get(root).ceiling(first[u]) - occ.get(root).floor(first[u]) - (depth[u] - depth[root])));
			} else {
				out.println(first[v] - first[root] - (depth[u] - depth[root]));
			}
		}
		out.close();
	}

	static int lca (int u, int v) {
		if (depth[u] < depth[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		for (int i = LN - 1; i >= 0; i--)
			if (pa[i][u] != -1 && depth[pa[i][u]] >= depth[v])
				u = pa[i][u];
		
		if (u == v)
			return v;
		
		for (int i = LN - 1; i >= 0; i--)
			if (pa[i][v] != -1 && pa[i][u] != -1 && pa[i][u] != pa[i][v]) {
				u = pa[i][u];
				v = pa[i][v];
			}
		
		return pa[0][u];
	}
	
	static void dfs (int u, int prev, int d) {
		first[u] = Math.min(first[u], cnt);
		occ.get(u).add(cnt++);
		depth[u] = d;
		
		for (int v : adj.get(u)) {
			if (prev != v) {
				dfs(v, u, d + 1);
				occ.get(u).add(cnt++);
				size[u] += 2;
				size[u] += size[v];
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

