package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_20_E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, K;
	static ArrayList<ArrayList<Integer>> adj;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
//		K = (int)(Math.cbrt(N));
		K = 150;
		
		adj = new ArrayList<ArrayList<Integer>>(N);

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		for (int i = 0; i < N; i++)
			Collections.sort(adj.get(i));

		long ans = 0;

		int[] pairs = new int[M * K];
		int index = 0;
		
		for (int i = 0; i < N; i++) {
			// small corners
			if (adj.get(i).size() <= K) {
				for (int j = 0; j < adj.get(i).size(); j++) {
					for (int k = j + 1; k < adj.get(i).size(); k++) {
						pairs[index++] = adj.get(i).get(j) * N + adj.get(i).get(k);
					}
				}
			}

			// large corners
			else {
				boolean[] vis = new boolean[N];

				for (int u : adj.get(i))
					vis[u] = true;

				for (int j = 0; j < N; j++) {
					if (adj.get(j).size() > K && j <= i)
						continue;
					long cnt = 0;
					for (int k : adj.get(j))
						if (vis[k])
							cnt++;
					ans += (cnt) * (cnt - 1) / 2;
				}
			}
		}
		Arrays.sort(pairs, 0, index);
		for (int i = 0; i < index; ) {
			int j = i;
			while (j < index && pairs[i]  == pairs[j]) {
				j++;
			}
			long cnt = j - i;
			ans += (cnt) * (cnt - 1) / 2;
			i = j;
		}
		out.println(ans / 2);
		out.close();
	}

	static class Edge {
		int u, v;
		Edge (int u, int v) {
			this.u = u;
			this.v = v;
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

