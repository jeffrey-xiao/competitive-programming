package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_S3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static boolean[] isPho;
	static boolean[] marked;
	static int[] dist;
	static int totalDist;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		isPho = new boolean[N];
		marked = new boolean[N];

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++)
			isPho[readInt()] = true;

		for (int i = 0; i < N - 1; i++) {
			int a = readInt();
			int b = readInt();
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		for (int i = 0; i < N; i++)
			if (isPho[i]) {
				mark(i, -1);
				break;
			}

		dist = new int[N];
		for (int i = 0; i < N; i++)
			if (marked[i]) {
				computeDist(i, -1, 0);
				break;
			}

		int maxIndex = -1;
		for (int i = 0; i < N; i++)
			if (marked[i] && (maxIndex == -1 || (dist[maxIndex] < dist[i])))
				maxIndex = i;

		dist = new int[N];
		computeDist(maxIndex, -1, 0);

		int longestDist = 0;

		for (int i = 0; i < N; i++)
			longestDist = Math.max(longestDist, dist[i]);

		out.println(totalDist - longestDist);
		out.close();
	}

	static void computeDist (int u, int prev, int depth) {
		dist[u] = depth;
		for (int v : adj.get(u)) {
			if (v != prev && marked[v]) {
				computeDist(v, u, depth + 1);
			}
		}
	}

	static void mark (int u, int prev) {
		if (isPho[u])
			marked[u] = true;

		for (int v : adj.get(u)) {
			if (v == prev)
				continue;
			mark(v, u);
			if (marked[v]) {
				totalDist += 2;
				marked[u] = true;
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

