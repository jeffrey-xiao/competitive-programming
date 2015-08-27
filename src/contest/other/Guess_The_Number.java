package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Guess_The_Number {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int low = 1;
		int high = 1000;
		String answer = "";
		while (!answer.equals("OK")) {
			int mid = (low + high) / 2;
			System.out.println(+mid);
			System.out.flush();
			answer = next();
			if (answer.equals("Lower"))
				high = mid;
			else if (answer.equals("Higher"))
				low = mid;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
