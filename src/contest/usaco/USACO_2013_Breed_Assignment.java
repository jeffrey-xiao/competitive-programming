package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2013_Breed_Assignment {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static char[][] adj;
	static int n;
	static int k;
	static int[] b;

	public static void main (String[] args) throws IOException {
		n = readInt();
		k = readInt();
		adj = new char[n][n];
		b = new int[n];
		for (int x = 0; x < k; x++) {
			char c = next().charAt(0);
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj[a][b] = c;
			adj[b][a] = c;
		}
		System.out.println(compute(0));
	}

	private static int compute (int i) {
		if (i == n)
			return 1;
		int count = 0;
		main : for (int x = 1; x <= 3; x++) {
			for (int y = 0; y < i; y++) {
				if (b[y] == x && adj[y][i] == 'D')
					continue main;
				if (b[y] != x && adj[y][i] == 'S')
					continue main;
			}
			b[i] = x;
			count += compute(i + 1);
			b[i] = 0;
		}
		return count;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
