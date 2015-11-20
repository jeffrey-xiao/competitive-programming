/*
 * Dynamic programming algorithm that computes the maximum value that you can obtain from putting n elements (each with integral value and weight) in a knapsack that can fit
 * a total of n weight.
 *
 * Time complexity: O(NM) where N is the number of items and the M is the size of the knapsack
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Knapsack {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// number of items
		int n = readInt();
		// size of knapsack
		int m = readInt();

		int[] weight = new int[n + 1];
		int[] value = new int[n + 1];
		int[] dp = new int[m + 1];

		for (int i = 1; i <= n; i++) {
			weight[i] = readInt();
			value[i] = readInt();
		}
		for (int i = 1; i <= n; i++)
			// 0-1 knapsack; for unbounded then iterate the opposite way
			for (int j = m; j >= 0; j--)
				if (j - weight[i] >= 0)
					dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
		out.println(dp[m]);
		out.close();
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
