package contest.other;

import java.util.*;
import java.io.*;

public class TC {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int SIZE = 100001;

	static int[] bit = new int[SIZE];

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		int n = readInt();
		int[] ans = new int[n];
		Score[] s = new Score[n];
		for (int i = 0; i < n; i++)
			s[i] = new Score(readInt(), readInt(), i);
		Arrays.sort(s);
		int lastSame = 0;
		for (int i = 0; i < n; i++) {
			ans[s[i].index] = query(s[i].b);
			if (i > 0 && s[i - 1].a == s[i].a && s[i - 1].b == s[i].b)
				ans[s[i].index] -= i - lastSame;
			else
				lastSame = i;
			update(s[i].b, 1);
		}
		for (int i = 0; i < n; i++)
			pr.println(ans[i]);
		pr.close();
	}

	static class Score implements Comparable<Score> {
		int a, b, index;

		Score (int a, int b, int index) {
			this.a = a;
			this.b = b;
			this.index = index;
		}

		@Override
		public int compareTo (Score o) {
			if (a == o.a)
				return b - o.b;
			return a - o.a;
		}
	}

	static void update (int x, int v) {
		for (int i = x; i < SIZE; i += (i & -i))
			bit[i] += v;
	}

	static int query (int x) {
		int sum = 0;
		for (int i = x; i > 0; i -= (i & -i))
			sum += bit[i];
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
