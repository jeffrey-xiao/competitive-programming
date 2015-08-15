package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_P4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static final int SIZE = 100005;

	public static void main (String[] args) throws IOException {
		int[] k = new int[SIZE];
		int[] factors = new int[SIZE];
		for (int x = 1; x <= 100000; x++) {
			int num = 0;
			for (int y = 1; y * y <= x; y++) {
				if (y * y == x)
					num++;
				else if (x % y == 0)
					num += 2;
			}
			factors[x] = num;
		}
		int t = readInt();
		for (int x = 0; x < t; x++) {
			int q = readInt();
			int a = readInt();
			int b = readInt();
			int total = 0;
			for (int y = a; y <= b; y++) {
				if (factors[y] == q)
					total++;
			}
			System.out.println(total);
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
