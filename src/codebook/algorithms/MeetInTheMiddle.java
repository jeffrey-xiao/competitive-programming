/*
 * Meet in the Middle: It is a search technique that divides the input into two, brute forces the two parts, and then combines the solutions.
 * It reduces the time complexity from O(2^N) to O((N/2) * 2 ^ (N/2)). However the memory is increased to O(2 ^ (N/2)).
 * 
 * Reference Problem:
 * 
 * A bunch of people on Facebook would like to find out the largest clique among them.

 * A clique is defined as a group of people where everyone is friends with everyone else. 
 * Given a list of friends (friendship works both ways), your job is to output the size of this clique.
 * For the sake of privacy (and your convenience), we have replaced the names of the people with numbers
 * 
 * INPUT:
 * 
 * Number of people, 1 <= N <= 32 
 * Number of friendships, 1 <= M <= N*(N-1)/2
 * M lines, each with 2 numbers 1 <= a, b <= N meaning that a and b are friends.
 * There will be no duplicate edges.
 * 
 * OUTPUT:
 * 
 * The size of the maximum clique.
 */

package codebook.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MeetInTheMiddle {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		int[][] adj = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj[a][b] = adj[b][a] = 1;
		}
		int szA = n / 2;
		int szB = n - n / 2;

		int[] a = new int[1 << szA];
		for (int i = 1; i < 1 << szA; i++) {
			boolean valid = true;
			for (int j = 0; j < szA; j++)
				for (int k = j + 1; k < szA; k++) {
					if ((i & 1 << j) > 0 && (i & 1 << k) > 0 && adj[j][k] == 0)
						valid = false;
				}
			if (valid) {
				int cnt = 0;
				for (int j = 0; j < szA; j++)
					if ((i & 1 << j) > 0)
						cnt++;
				a[i] = cnt;
			} else {
				for (int j = 0; j < szA; j++) {
					if ((i & 1 << j) > 0)
						a[i] = Math.max(a[i], a[i ^ 1 << j]);
				}
			}
		}
		int max = 0;
		for (int i = 1; i < 1 << szB; i++) {
			boolean valid = true;
			for (int j = 0; j < szB; j++)
				for (int k = j + 1; k < szB; k++) {
					if ((i & 1 << j) > 0 && (i & 1 << k) > 0 && adj[j + szA][k + szA] == 0)
						valid = false;
				}
			if (valid) {
				int cnt = 0;
				int bit = (1 << szA) - 1;
				for (int j = 0; j < szB; j++) {
					if ((i & 1 << j) > 0) {
						cnt++;
						for (int k = 0; k < szA; k++) {
							if (adj[j + szA][k] == 0)
								bit = bit & ~(1 << k);
						}
					}
				}
				max = Math.max(max, cnt + a[bit]);
			}
		}
		pr.println(max);

		pr.close();
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
