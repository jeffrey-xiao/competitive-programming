package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Melanioana {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		boolean d = next().equals("trees!");
		double max = 0;
		double minus = 0;
		double sum = 0;
		for (int i = 0; i < 4; i++) {
			double lo = readDouble();
			double hi = readDouble();
			double weight = readDouble();
			sum += lo / hi * weight;
			if (weight > max) {
				max = weight;
				minus = lo / hi * weight;
			}
		}
		if (!d)
			sum -= minus;
		if (d)
			sum = sum - minus + max;
		System.out.println((int) (Math.round(100 * sum)));
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
