package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Hard_Question {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		double g = readDouble();
		double c = readDouble();
		double t = readDouble();
		double t1 = -c + Math.sqrt(c * c - 4 * (g / 2) * (-c * t));
		t1 /= (2 * g / 2);
		double d = (g / 2) * t1 * t1;
		double v = Math.sqrt(2 * g * d);
		System.out.printf("%.2f\n%.2f", d, v);

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
