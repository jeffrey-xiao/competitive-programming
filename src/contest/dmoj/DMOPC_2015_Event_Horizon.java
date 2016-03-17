package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Event_Horizon {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static ArrayList<HashMap<Integer, Integer>> adj = new ArrayList<HashMap<Integer, Integer>>();
	static int[] cnt;
	static boolean[] vis;
	public static void main (String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		cnt = new int[N];
		
		for (int i = 0; i < N; i++)
			adj.add(new HashMap<Integer, Integer>());
		
		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			
			vis = new boolean[N];
			State s1 = dfs(a);
			vis = new boolean[N];
			State s2 = dfs(b);
			
			if (s1.min < s2.min) {
				add(a, b);
				cnt[a]++;
				for (Edge e : s1.e) {
					remove(e.a, e.b);
					add(e.b, e.a);
					cnt[e.b]++;
					cnt[e.a]--;
				}
			} else {
				add(b, a);
				cnt[b]++;
				for (Edge e : s2.e) {
					remove(e.a, e.b);
					add(e.b, e.a);
					cnt[e.b]++;
					cnt[e.a]--;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, cnt[i]);
		
		out.println(max);
		out.close();
	}

	static void add (int i, int j) {
		if (adj.get(i).get(j) == null)
			adj.get(i).put(j, 0);
		adj.get(i).put(j, adj.get(i).get(j) + 1);
	}
	
	static void remove (int i, int j) {
		adj.get(i).put(j, adj.get(i).get(j) - 1);
		if (adj.get(i).get(j) == 0)
			adj.get(i).remove(j);
	}
	
	static State dfs (int u) {
		State s = new State();
		s.min = cnt[u];
		vis[u] = true;
		for (Map.Entry<Integer, Integer> v : adj.get(u).entrySet()) {
			if (vis[v.getKey()])
				continue;
			State next = dfs(v.getKey());
			if (next.min < s.min) {
				s.min = next.min;
				s.e.clear();
				for (Edge edge : next.e)
					s.e.add(edge);
				s.e.add(new Edge(u, v.getKey()));
			}
		}
		return s;
	}
	
	static class State {
		int min = 1 << 30;
		ArrayList<Edge> e = new ArrayList<Edge>();
	}
	
	static class Edge {
		int a, b;
		Edge (int a, int b) {
			this.a = a;
			this.b = b;
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

