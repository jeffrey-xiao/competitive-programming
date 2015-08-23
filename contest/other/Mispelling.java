package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mispelling {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int x = 0; x < n; x++) {
			String input = readLine();
			st = new StringTokenizer(input);
			int i = Integer.parseInt(st.nextToken());
			input = input.substring(input.indexOf(' ') + 1);
			System.out.print(x + 1 + " ");
			for (int y = 0; y < input.length(); y++)
				if (i != y + 1)
					System.out.print(input.charAt(y));
			System.out.println();
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
