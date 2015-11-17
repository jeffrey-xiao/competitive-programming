package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_DOM {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int n = readInt();
		int m = readInt();
		int p = readInt();
		int[] change = new int[m + 1];
		boolean[] v = new boolean[m + 1];
		for (int i = 0; i < n; i++) {
			int fav = readInt();
			int hate = readInt();
			if (change[hate] == 0)
				change[hate] = fav;
		}
		int ans = 0;
		while (true) {
			int next = change[p];
			if (next == 0)
				break;
			if (v[next]) {
				ps.println(-1);
				ps.close();
				return;
			}
			ans++;
			v[next] = true;
			p = next;
		}

		ps.println(ans);
		ps.close();
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