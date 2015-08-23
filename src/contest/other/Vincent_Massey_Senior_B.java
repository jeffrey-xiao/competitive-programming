package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Vincent_Massey_Senior_B {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintWriter pw;
	static int[] tree;
	static int n;

	public static void main (String[] args) throws IOException {
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		n = readInt();
		tree = new int[n + 1];
		for (int m = readInt(); m > 0; m--)
			update(readInt());
		for (int q = readInt(); q > 0; q--) {
			pw.println((-getFreq(readInt() - 1) + getFreq(readInt())));
		}
		pw.close();
	}

	static void update (int idx) {
		for (int x = idx; x <= n; x += (x & -x))
			tree[x]++;
	}

	static int getFreq (int idx) {
		int sum = 0;
		for (int x = idx; x > 0; x -= (x & -x))
			sum += tree[x];
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
