package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2012_Haybale_Restacking {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] d = new int[n];
		for (int x = 0; x < n; x++) {
			a[x] = readInt();
			b[x] = readInt();
		}
		d[0] = a[0] - b[0];
		for (int x = 1; x < n; x++)
			d[x] = a[x] - b[x] + d[x - 1];
		Arrays.sort(d);
		int flow = d[n / 2];
		long result = 0;
		for (int x = 0; x < n; x++)
			result += Math.abs(d[x] - flow);
		System.out.println(result);
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
