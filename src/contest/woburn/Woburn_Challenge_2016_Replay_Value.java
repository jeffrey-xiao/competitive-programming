package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Replay_Value {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] D;
	static boolean[] exclude;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		D = new int[N];
		exclude = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			D[i] = readInt();
			adj.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N - 1; i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		long ans = 0;
		
		while (!q.isEmpty()) {
			int u = q.poll();
			u = getCentroid(u, -1, getSize(u, -1));
			
			long outgoing = 0;
			long incoming = 0;

			for (int v : adj.get(u)) {
				if (exclude[v])
					continue;
				long currOutgoing = 0;
				long currIncoming = 0;
				if (D[u] >= D[v])
					currOutgoing = getOutgoing(v, u);
				if (D[u] <= D[v])
					currIncoming = getIncoming(v, u);
				ans += currOutgoing + currIncoming;
				ans += currOutgoing * incoming + currIncoming * outgoing;
				outgoing += currOutgoing;
				incoming += currIncoming;
			}
			
			exclude[u] = true;
			for (int v : adj.get(u))
				if (!exclude[v])
					q.offer(v);
		}
		
		out.println(ans);
		out.close();
	}

	static int getOutgoing (int u, int par) {
		int ret = 1;
		for (int v : adj.get(u))
			if (v != par && !exclude[v] && D[u] >= D[v])
				ret += getOutgoing(v, u);
		return ret;
	}
	
	static int getIncoming (int u, int par) {
		int ret = 1;
		for (int v : adj.get(u))
			if (v != par && !exclude[v] && D[u] <= D[v])
				ret += getIncoming(v, u);
		return ret;
	}
	
	static int getSize (int u, int par) {
		int sz = 1;
		for (int v : adj.get(u))
			if (v != par && !exclude[v])
				sz += getSize(v, u);
		return sz;
	}
	
	static int getCentroid (int u, int par, int treeSize) {
		int n = treeSize;
		int sz = 1;
		boolean valid = true;
		for (int v : adj.get(u)) {
			if (v == par || exclude[v])
				continue;
			int ret = getCentroid(v, u, treeSize);
			if (ret >= 0)
				return ret;
			valid &= -ret <= n / 2;
			sz += -ret;
		}
		valid &= n - sz <= n / 2;
		return valid ? u : -sz;
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

