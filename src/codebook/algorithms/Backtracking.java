/*
 * Demonstration of backtracking, and branch and bound using the N Queens Problem
 * 
 * Time complexity: O(N!)
 */

package codebook.algorithms;

import java.util.*;
import java.io.*;

public class Backtracking {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[] row;
	static boolean[] used, diag1, diag2;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		row = new int[n];
		used = new boolean[n];
		diag1 = new boolean[2*n-1];
		diag2 = new boolean[2*n-1];
		
		out.println(backtrack(0, n) + " " + branchAndBound(0, n));
		out.close();
	}

	static int backtrack (int i, int n) {
		if (i == n) {
			for (int j = 0; j < n; j++)
				for (int k = j + 1; k < n; k++)
					if (row[j] + j == row[k] + k || row[j] - j == row[k] - k)
						return 0;
			return 1;
		}
		int total = 0;
		for (row[i] = 0; row[i] < n; row[i]++) {
			if (used[row[i]])
				continue;
			used[row[i]] = true;
			total += backtrack(i+1, n);
			used[row[i]] = false;
		}
		return total;
	}
	
	static int branchAndBound (int i, int n) {
		if (i == n)
			return 1;
		int total = 0;
		for (int j = 0; j < n; j++) {
			if (used[j] || diag1[i + j] || diag2[i - j + n - 1])
				continue;
			used[j] = diag1[i + j] = diag2[i - j + n - 1] = true;
			total += branchAndBound(i+1, n);
			used[j] = diag1[i + j] = diag2[i - j + n - 1] = false;
		}
		return total;
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

