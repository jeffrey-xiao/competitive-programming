package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_SREDNJI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int[] sum = new int[n + 1];
		int i = 0;
		for (int x = 1; x <= n; x++) {
			int num = readInt();
			if (num < m)
				num = -1;
			else if (num > m)
				num = 1;
			else {
				num = 0;
				i = x;
			}
			sum[x] = num + sum[x - 1];
		}
		int[] l = new int[2 * n + 1];
		int[] r = new int[2 * n + 1];
		for (int x = 1; x <= i; x++) {
			l[sum[i] - sum[x - 1] + n]++;
		}
		for (int x = i; x <= n; x++) {
			r[-(sum[x] - sum[i - 1]) + n]++;
		}
		int total = 0;
		for (int x = 0; x < 2 * n + 1; x++)
			total += l[x] * r[x];
		System.out.println(total);
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
