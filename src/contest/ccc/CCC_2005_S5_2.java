package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2005_S5_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[] tree;

	public static void main (String[] args) throws IOException {
		n = readInt();
		tree = new int[n + 1];
		int[] indexes = new int[n];
		Score[] ranks = new Score[n];
		for (int x = 0; x < n; x++) {
			int score = readInt();
			ranks[x] = new Score(score, x);
		}
		Arrays.sort(ranks);
		int count = 1;
		indexes[ranks[0].index] = 1;
		int[] freq = new int[n + 1];
		for (int x = 2; x <= n; x++) {
			if (ranks[x - 1].score != ranks[x - 2].score)
				count++;
			indexes[ranks[x - 1].index] = count;
		}
		double f = 0;
		for (int x = 0; x < n; x++) {
			update(indexes[x], +1);

			f += freqTo(indexes[x]) - freq[indexes[x]];
			freq[indexes[x]]++;
		}
		f /= n;
		if (Double.parseDouble(String.format("%.2f", f * 100.0 - (int)(f * 100.0))) == 0.5 && (int)((f * 10 - (int)(f * 10)) * 10) % 2 == 0)
			f -= 0.01;
		System.out.printf("%.2f", f);
	}

	static class Score implements Comparable<Score> {
		int score;
		int index;

		Score (int score, int index) {
			this.score = score;
			this.index = index;
		}

		@Override
		public int compareTo (Score o) {
			if (o.score == score)
				return index - o.index;
			return o.score - score;
		}
	}

	private static void update (int x, int val) {
		while (x <= n) {
			tree[x] += val;
			x += (x & -x);
		}
	}

	@SuppressWarnings("unused")
	private static int freqAt (int x) {
		return freqTo(x) - freqTo(x - 1);
	}

	private static int freqTo (int x) {
		int sum = 0;
		while (x > 0) {
			sum += tree[x];
			x -= (x & -x);
		}
		return sum;
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
