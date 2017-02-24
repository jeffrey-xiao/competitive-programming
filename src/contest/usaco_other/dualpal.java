package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: dualpal
 */
import java.util.*;
import java.io.*;

public class dualpal {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("dualpal.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

		int n = readInt();
		for (int x = readInt() + 1; n > 0; x++) {
			int count = 0;
			for (int y = 2; y <= 10; y++) {
				String next = Integer.toString(x, y);
				if (next.equals(new StringBuilder(next).reverse().toString()))
					count++;
			}
			if (count >= 2) {
				pr.println(x);
				n--;
			}
		}

		pr.close();
		System.exit(0);
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
