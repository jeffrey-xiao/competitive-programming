package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Routing_Silver {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int a = readInt() - 1;
		int b = readInt() - 1;
		int n = readInt();
		long[][] adj = new long[1000][1000];
		long[][] moves = new long[1000][1000];
		for (int x = 0; x < 1000; x++)
			for (int y = 0; y < 1000; y++) {
				adj[x][y] = x == y ? 0 : Long.MAX_VALUE;
				moves[x][y] = x == y ? 0 : Long.MAX_VALUE;
			}
		for (int x = 0; x < n; x++) {
			long cost = readLong();
			int num = readInt();
			int[] curr = new int[num];
			for (int y = 0; y < num; y++) {
				curr[y] = readInt() - 1;
				for (int z = 0; z < y; z++) {
					if (cost < adj[curr[z]][curr[y]]
							|| (cost == adj[curr[z]][curr[y]] && y - z < moves[curr[z]][curr[y]])) {
						adj[curr[z]][curr[y]] = cost;
						// System.out.println(cost);
						moves[curr[z]][curr[y]] = y - z;
					}
				}
			}
		}
		long[] min = new long[1000];
		long[] minMoves = new long[1000];
		boolean[] v = new boolean[1000];
		for (int x = 0; x < 1000; x++) {
			min[x] = Long.MAX_VALUE;
			minMoves[x] = Long.MAX_VALUE;
		}
		min[a] = 0;
		minMoves[a] = 0;
		v[a] = true;
		int curr = a;
		for (int x = 0; x < 1000; x++) {
			int next = -1;
			long currMin = Long.MAX_VALUE;
			long currMinMoves = Long.MAX_VALUE;
			for (int y = 0; y < 1000; y++) {
				if (v[y] || adj[curr][y] == Long.MAX_VALUE)
					continue;
				if (adj[curr][y] + min[curr] < min[y]
						|| (adj[curr][y] + min[curr] == min[y] && moves[curr][y]
								+ minMoves[curr] < minMoves[y])) {
					min[y] = adj[curr][y] + min[curr];
					minMoves[y] = moves[curr][y] + minMoves[curr];
				}
			}
			for (int y = 0; y < 1000; y++) {
				if (v[y])
					continue;
				if (min[y] < currMin
						|| (min[y] == currMin && minMoves[y] < currMinMoves)) {
					next = y;
					currMin = min[y];
					currMinMoves = minMoves[y];
				}
			}
			if (next == -1)
				break;
			v[next] = true;
			curr = next;
		}
		if (min[b] == Long.MAX_VALUE)
			System.out.println("-1 -1");
		else
			System.out.println(min[b] + " " + minMoves[b]);
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
