package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2014_Cow_Decathlon_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static Award[] awards;
	static short[][] skill;
	static int[] dp;
	static int n, k;

	public static void main (String[] args) throws IOException {
		n = readInt();
		k = readInt();
		awards = new Award[k];
		dp = new int[1 << n];
		skill = new short[n][n];
		ArrayList<ArrayList<Award>> list = new ArrayList<ArrayList<Award>>();
		for (int x = 0; x <= n; x++)
			list.add(new ArrayList<Award>());
		for (int x = 0; x < k; x++)
			list.get(readInt()).add(new Award(readInt(), readInt()));
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				skill[x][y] = readShort();
		for (int x = 1; x < 1 << n; x++) {
			int max = 0;
			int length = Integer.bitCount(x);
			for (int y = 0; y < n; y++) {
				if ((x & (1 << y)) > 0) {
					max = Math.max(max, dp[x ^ (1 << y)] + skill[y][length - 1]);
				}
			}
			int next = max;
			for (Award a : list.get(length))
				if (max >= a.min)
					next += a.points;
			dp[x] = next;
		}
		System.out.println(dp[(1 << n) - 1]);
	}

	static class Award {
		int min, points;

		Award (int min, int points) {
			this.min = min;
			this.points = points;
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

	static short readShort () throws IOException {
		return Short.parseShort(next());
	}

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
