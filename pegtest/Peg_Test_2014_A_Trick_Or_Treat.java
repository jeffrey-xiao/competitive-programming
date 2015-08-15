package pegtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Peg_Test_2014_A_Trick_Or_Treat {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int t = readInt();
		int[] times = new int[n];
		for (int x = 0; x < n; x++)
			times[x] = readInt();
		Arrays.sort(times);
		int count = 0;
		for (int x = 0; x < n && t >= times[x]; x++) {
			count++;
			t -= (times[x] + 1);
		}
		System.out.println(count);
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
