package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_1998_S2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		for (int i = 1000; i <= 9999; i++)
			if (isPerfect(i))
				out.print(i + " ");
		out.println();
		for (int i = 100; i <= 999; i++)
			if (isSum(i))
				out.print(i + " ");
		out.println();
		out.close();
	}

	static boolean isPerfect (int n) {
		int sum = 0;
		for (int i = 1; i <= n / i; i++)
			if (n % i == 0)
				sum += n / i + i;
		return sum == 2 * n;
	}

	static boolean isSum (int n) {
		int sum = 0;
		int m = n;
		while (m > 0) {
			sum += (m % 10) * (m % 10) * (m % 10);
			m /= 10;
		}
		return sum == n;
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
