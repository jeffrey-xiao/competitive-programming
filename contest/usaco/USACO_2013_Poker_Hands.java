package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2013_Poker_Hands {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] cards = new int[n];
		int sum = 0;
		for (int x = 0; x < cards.length; x++) {
			cards[x] = readInt();
		}
		int max = -1;
		while (max != 0) {
			max = 0;
			int index = 0;
			int curr = 0;
			for (int x = 0; x < cards.length; x++) {
				if (cards[x] > 0)
					curr++;
				if (cards[x] <= 0) {
					if (curr > max) {
						max = curr;
						index = x - max;
					}
					curr = 0;
				}
			}

			if (curr > max) {
				max = curr;
				index = cards.length - max;
			}
			if (max > 0)
				for (int x = index; x < index + max; x++) {
					cards[x]--;
				}
			sum++;
		}
		System.out.println(sum - 1);
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
