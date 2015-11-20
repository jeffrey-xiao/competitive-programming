/*
 * Dynamic programming algorithm that finds the minimum cost of multiplying a series of matrices.
 *
 * Time complexity: O(N^2) where N is the number of matrices
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinMatrixChainMultiplication {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] p = new int[n + 1];
		for (int i = 0; i <= n; i++)
			p[i] = readInt();
		out.println(min(p, n));
		out.close();
	}

	// the ith matrix has dimension p[i-1] x p[i] for i = 1..n
	private static int min (int[] p, int n) {
		// dp matrix
		int[][] m = new int[n][n];

		// m[i,j] = Minimum number of scalar multiplications to compute matrix i - j
		// cost is zero when multiplying one matrix.

		for (int i = 1; i < n; i++)
			m[i][i] = 0;

		// L is chain length.  
		for (int L = 2; L < n; L++) {
			for (int i = 1; i <= n - L + 1; i++) {
				int j = i + L - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < m[i][j])
						m[i][j] = q;
				}
			}
		}
		return m[1][n - 1];
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
