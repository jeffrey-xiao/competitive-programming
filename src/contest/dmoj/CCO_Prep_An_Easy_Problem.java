package contest.dmoj;

import java.util.*;
import java.io.*;

public class CCO_Prep_An_Easy_Problem {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static ArrayList<ArrayList<Integer>> occ = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static boolean[] vis;
	static int[] len;
	static Queue<Integer> q = new ArrayDeque<Integer>();
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		vis = new boolean[N];
		len = new int[N];
		
		for (int i = 0; i < 32; i++)
			occ.add(new ArrayList<Integer>());
		
		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());
			
		for (int i = 0; i < N; i++) {
			int val = readInt();
			for (int j = 0; j < 32; j++)
				if ((val & 1 << j) > 0)
					occ.get(j).add(i);
		}
		
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < occ.get(i).size() - 1; j++) {
				adj.get(occ.get(i).get(j)).add(occ.get(i).get(j + 1));
			}
		}

		for (int i = 0; i < N; i++)
			if (!vis[i])
				dfs(i);
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			int max = 0;
			for (int j : adj.get(curr)) {
				max = Math.max(len[j], max);
			}
			len[curr] = max + 1;
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, len[i]);
		}
		out.println(ans);
		out.close();
	}
	
	static void dfs (int u) {
		vis[u] = true;
		for (int v : adj.get(u))
			if (!vis[v])
				dfs(v);
		q.offer(u);
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

