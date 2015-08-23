package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Machined_Surfaces {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = 0;
		while ((n = readInt()) != 0) {
			int min = Integer.MAX_VALUE;
			int total = 0;
			String[] ss = new String[n];
			for (int x = 0; x < n; x++) {
				ss[x] = readLine();
				min = Math.min(count(ss[x]), min);
			}
			for (int x = 0; x < n; x++) {
				int a = count(ss[x]) - min;
				if (a > 0)
					total += a;
			}
			System.out.println(total);
		}
	}

	static int count (String s) {
		int count = 0;
		for (int y = 0; y < s.length(); y++)
			if (s.charAt(y) == ' ')
				count++;
		return count;
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
