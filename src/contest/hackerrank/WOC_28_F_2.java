package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_28_F_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = 998244353;
	
	static int N, M, K;
	static int[] next, indegree;
	static long[] dice, ans;
	static boolean[] vis;
	static Stack<Integer> s = new Stack<Integer>();
	
	static ArrayList<Cycle> cycles = new ArrayList<Cycle>();
	static ArrayList<Chain> chains = new ArrayList<Chain>();
	static Cycle[] c;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		K = readInt();
		
		next = new int[N];
		indegree = new int[N];
		dice = new long[M];
		ans = new long[N];
		vis = new boolean[N];
		c = new Cycle[N];
		
		for (int i = 0; i < N; i++) {
			next[i] = readInt() - 1;
			indegree[next[i]]++;
		}

		for (int i = 0; i < M; i++)
			dice[i] = readInt();

		// finding all chains
		for (int i = 0; i < N; i++) 
			if (indegree[i] == 0) 
				findChain(i);
		
		// finding all cycles
		for (int i = 0; i < N; i++)
			if (!vis[i])
				findChain(i);
		
		
		for (Chain c : chains)
			out.println("chain: " + c.nodes.toString() + " " + c.root);
		
		for (Cycle c : cycles)
			out.println("cycle: " + c.nodes.toString());
		
		for (Cycle c : cycles)
			for (int node : c.nodes)
				ans[node] = modInverse(N, MOD);
		
		for (Chain c : chains) {
			long[] prob = getProb(c.nodes.size());
		}
		
		out.close();
	}

	static long[] getProb (int sz) {
		// 
		long[][] dp = new long[2][sz];
		dp[0] = 1;
		for (int i = 0; i < K; i++) {
			Arrays.fill(dp[(i + 1) % 2], 0);
			for (int j = 0; j < N; j++) {
				dp[(i + 2) % 2][i] = (dp[(i + 2) % 2] + dp[()]);
			}
		}
	}
	
	static void findChain (int u) {
		vis[u] = true;
		s.push(u);

		while (!vis[next[u]] && c[next[u]] == null) {
			u = next[u];
			s.push(u);
			vis[u] = true;
		}
		
		// found a chain and a cycle
		if (vis[next[u]]) {
			int root = next[u];
			ArrayList<Integer> cycle = new ArrayList<Integer>();
			while (s.peek() != root)
				cycle.add(s.pop());
			cycle.add(s.pop());
			Collections.reverse(cycle);
			
			ArrayList<Integer> chain = new ArrayList<Integer>();
			while (!s.isEmpty())
				chain.add(s.pop());
			Collections.reverse(chain);
			
			Cycle newCycle = new Cycle(cycle);
			
			for (int node : cycle)
				c[node] = newCycle;
			
			cycles.add(new Cycle(cycle));

			if (chain.size() > 0)
				chains.add(new Chain(chain, c[root], root));
		} 
		
		// found a chain
		else {
			int root = u;
			ArrayList<Integer> chain = new ArrayList<Integer>();
			while (!s.isEmpty())
				chain.add(s.pop());
			Collections.reverse(chain);
			
			chains.add(new Chain(chain, c[root], u));
		}
	}
	
	static class Chain {
		ArrayList<Integer> nodes;
		Cycle c;
		int root;
		
		Chain (ArrayList<Integer> nodes, Cycle c, int root) {
			this.nodes = nodes;
			this.c = c;
			this.root = root;
		}
	}
	
	static class Cycle {
		ArrayList<Integer> nodes;
		HashMap<Integer, Integer> toIndex;
		
		Cycle (ArrayList<Integer> nodes) {
			this.nodes = nodes;
			this.toIndex = new HashMap<Integer, Integer>();
			for (int i = 0; i < nodes.size(); i++)
				toIndex.put(nodes.get(i), i);
		}
	}
	

	static long mod (long a, long b) {
		return ((a % b) + b) % b;
	}

	// precondition: m > 0 && gcd(a, m) = 1
	public static long modInverse (long a, long m) {
		a = mod(a, m);
		return a == 0 ? 0 : mod((1 - modInverse(m % a, a) * m) / a, m);
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

