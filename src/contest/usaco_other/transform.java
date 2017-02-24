package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: transform
 */
import java.util.*;
import java.io.*;

public class transform {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("transform.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

		int n = readInt();
		char[][] start = new char[n][];
		char[][] curr = new char[n][];
		char[][] end = new char[n][];
		for (int x = 0; x < n; x++) {
			start[x] = next().toCharArray();
			curr[x] = Arrays.copyOf(start[x], n);
		}
		for (int x = 0; x < n; x++)
			end[x] = next().toCharArray();
		char[][] reflect = reflect(curr, n);

		if (equals((curr = rotate(curr, n)), end, n))
			pr.println(1);
		else if (equals((curr = rotate(curr, n)), end, n))
			pr.println(2);
		else if (equals((curr = rotate(curr, n)), end, n))
			pr.println(3);
		else if (equals(reflect, end, n))
			pr.println(4);
		else if (equals((reflect = rotate(reflect, n)), end, n))
			pr.println(5);
		else if (equals((reflect = rotate(reflect, n)), end, n))
			pr.println(5);
		else if (equals((reflect = rotate(reflect, n)), end, n))
			pr.println(5);
		else if (equals(start, end, n))
			pr.println(5);
		else
			pr.println(7);
		pr.close();
		System.exit(0);
	}

	private static boolean equals (char[][] a, char[][] b, int n) {
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				if (a[x][y] != b[x][y])
					return false;
		return true;
	}

	private static char[][] reflect (char[][] c, int n) {
		char[][] nc = new char[n][n];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				nc[x][y] = c[x][n - 1 - y];
			}
		}
		return nc;
	}

	private static char[][] rotate (char[][] c, int n) {
		char[][] nc = new char[n][n];
		int count = 0;
		for (int y = n - 1; y >= 0; y--) {
			for (int x = 0; x < n; x++) {
				nc[x][y] = c[count / n][count++ % n];
			}
		}
		return nc;
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
