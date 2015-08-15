package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_GO_Faster {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] on = new int[n - 1];
		int[] off = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			on[i] = readInt();
			off[i] = readInt();
		}
		int first = on[0];
		int first1 = on[0];
		int excess1 = 0;
		int excess = 0;
		for (int i = 1; i < n - 1; i++) {
			if (excess >= off[i])
				excess -= off[i];
			else {
				first -= (off[i] - excess);
				excess = 0;
			}
			if (first1 >= off[i])
				first1 -= off[i];
			else {
				excess1 -= (off[i] - first1);
				first1 = 0;
			}

			excess += on[i];
		}
		System.out.println(first1);
		System.out.println(first);
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
