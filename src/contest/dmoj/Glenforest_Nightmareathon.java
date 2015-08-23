package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Glenforest_Nightmareathon {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int q = readInt();
		int[] rating = new int[n + 2];
		int[] maxl = new int[n + 2], maxr = new int[n + 2];
		int[] cl = new int[n + 2], cr = new int[n + 2];
		maxl[0] = maxl[n + 1] = maxr[0] = maxr[n + 1] = -1;
		for (int i = 1; i <= n; i++)
			rating[i] = readInt();
		for (int i = 1; i <= n; i++) {
			if (rating[i] > maxl[i - 1]) {
				maxl[i] = rating[i];
				cl[i] = 1;
			} else if (rating[i] == maxl[i - 1]) {
				maxl[i] = rating[i];
				cl[i] = cl[i - 1] + 1;
			} else {
				maxl[i] = maxl[i - 1];
				cl[i] = cl[i - 1];
			}
		}
		for (int i = n; i >= 1; i--) {
			if (rating[i] > maxr[i + 1]) {
				maxr[i] = rating[i];
				cr[i] = 1;
			} else if (rating[i] == maxr[i + 1]) {
				maxr[i] = rating[i];
				cr[i] = cr[i + 1] + 1;
			} else {
				maxr[i] = maxr[i + 1];
				cr[i] = cr[i + 1];
			}
		}
		for (int i = 0; i < q; i++) {
			int a = readInt() - 1;
			int b = readInt() + 1;
			if (maxl[a] == maxr[b])
				System.out.println(maxl[a] + " " + (cl[a] + cr[b]));
			else if (maxl[a] < maxr[b])
				System.out.println(maxr[b] + " " + cr[b]);
			else
				System.out.println(maxl[a] + " " + cl[a]);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
