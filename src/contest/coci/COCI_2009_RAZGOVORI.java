package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2009_RAZGOVORI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		Detector[] d = new Detector[n];
		for (int x = 0; x < n; x++)
			d[x] = new Detector(readInt(), readInt());
		Arrays.sort(d);
		long ans = 0;
		for (int x = 1; x < n; x++)
			ans += Math.max(0, d[x - 1].call - d[x].call);
		ans += d[n - 1].call;
		System.out.println(ans);
	}

	static class Detector implements Comparable<Detector> {
		int house, call;

		Detector (int house, int call) {
			this.house = house;
			this.call = call;
		}

		@Override
		public int compareTo (Detector o) {
			return house - o.house;
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
