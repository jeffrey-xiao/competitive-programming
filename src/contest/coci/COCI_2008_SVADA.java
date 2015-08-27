package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_SVADA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n, m;

	public static void main (String[] args) throws IOException {
		int lo = 1;
		int hi = readInt();
		int t = hi;
		n = readInt();
		Monkey[] m1 = new Monkey[n];
		for (int x = 0; x < n; x++)
			m1[x] = new Monkey(readInt(), readInt());
		m = readInt();
		Monkey[] m2 = new Monkey[m];
		for (int x = 0; x < m; x++)
			m2[x] = new Monkey(readInt(), readInt());
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			int sumA = get(m1, mid);
			int sumB = get(m2, t - mid);
			// System.out.println(mid + " " + sumA + " " + sumB);
			// if(sumA == sumB){
			// System.out.println(mid);
			// break;
			// }
			if (sumA <= sumB)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		System.out.println(hi);
	}

	private static int get (Monkey[] mon, int t) {
		int sum = 0;
		for (Monkey m : mon) {
			if (t >= m.time)
				sum += 1 + (t - m.time) / m.rate;
		}
		return sum;
	}

	static class Monkey {
		int time, rate;

		Monkey (int time, int rate) {
			this.time = time;
			this.rate = rate;
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
