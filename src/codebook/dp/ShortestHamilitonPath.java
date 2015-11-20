/*
 * Bitset dynamic programming solution that finds the shortest hamiliton path.
 *
 * Time complexity: O((2^N) * (N^2)) where N is the number of vertexes
 */


package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ShortestHamilitonPath {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		//		int n = readInt();
		//		int[][] dist = new int[n][n];
		//		for (int i = 0; i < n; i++)
		//			for (int j = 0; j < n; j++)
		//				dist[i][j] = readInt();
		//		out.println(minDist(dist));

		// testing
		for (int k = 0; k < 10; k++) {
			int n = 10;
			int[][] dist = new int[n][n];
			int[] p = new int[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					dist[i][j] = (int) (Math.random() * 1000);
				dist[i][i] = 0;
				p[i] = i;
			}
			out.println(minDist(dist) == brute(p, dist, 0));
		}
		out.close();
	}

	static int brute (int[] p, int[][] dist, int i) {
		if (i == p.length - 1) {
			int ret = 0;
			for (int j = 0; j < p.length - 1; j++)
				ret += dist[p[j]][p[j + 1]];
			return ret;
		}
		int min = 1 << 30;
		for (int j = i; j < p.length; j++) {
			swap(p, i, j);
			min = Math.min(min, brute(p, dist, i + 1));
			swap(p, i, j);
		}
		return min;
	}

	static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static int minDist (int[][] dist) {
		int n = dist.length;

		int[][] dp = new int[1 << n][n];
		int[] order = new int[n];

		for (int i = 0; i < 1 << n; i++)
			for (int j = 0; j < n; j++)
				dp[i][j] = 1 << 29;

		for (int i = 0; i < n; i++)
			dp[1 << i][i] = 0;

		for (int i = 1; i < 1 << n; i++)
			for (int j = 0; j < n; j++)
				if ((i & 1 << j) != 0)
					for (int k = 0; k < n; k++)
						if ((i & 1 << k) == 0)
							dp[i ^ 1 << k][k] = Math.min(dp[i ^ 1 << k][k], dp[i][j] + dist[j][k]);

		int min = 1 << 30;
		for (int i = 0; i < n; i++)
			min = Math.min(min, dp[(1 << n) - 1][i]);

		int currState = (1 << n) - 1;
		int last = -1;
		for (int i = n - 1; i >= 0; i--) {
			int next = -1;
			for (int j = 0; j < n; j++)
				if ((currState & 1 << j) != 0 && (next == -1 || dp[currState][j] + (last == -1 ? 0 : dist[j][last]) < dp[currState][next] + (last == -1 ? 0 : dist[next][last])))
					next = j;
			order[i] = last = next;
			currState ^= 1 << last;
		}
		out.println(Arrays.toString(order));
		return min;
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
