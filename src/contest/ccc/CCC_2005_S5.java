package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2005_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<Integer> scores = new ArrayList<Integer>();
		long total = 0;
		for (int x = 0; x < n; x++) {
			int next = readInt();
			total += binaryInsertion(scores, next) + 1;
			// System.out.println(total);

		}
		// System.out.println(total);

		float f = total / (float) n;
		// System.out.println(Double.parseDouble(String.format("%.3f",f*100.0-(int)(f*100.0))));
		// System.out.println("blahhh " + (int)((f*10-(int)(f*10))*10));
		if (Double.parseDouble(String.format("%.2f", f * 100.0
				- (int) (f * 100.0))) == 0.5
				&& (int) ((f * 10 - (int) (f * 10)) * 10) % 2 == 0)
			f -= 0.01;
		System.out.printf("%.2f", f);
	}

	private static int binaryInsertion (ArrayList<Integer> scores, int next) {
		int lower = 0;
		int higher = scores.size() - 1;
		if (scores.size() == 0) {
			scores.add(next);
			return 0;
		}
		while (lower <= higher) {
			int mid = lower + (higher - lower) / 2;
			if (scores.get(mid) <= next)
				higher = mid - 1;
			else
				lower = mid + 1;
		}
		scores.add(lower, next);
		return lower;
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
