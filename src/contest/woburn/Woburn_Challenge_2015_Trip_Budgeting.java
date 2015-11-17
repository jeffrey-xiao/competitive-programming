package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Trip_Budgeting {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int emin = readInt();
		int tbase = readInt();
		int hbase = readInt();
		int fbase = readInt();

		int n = readInt();
		int[] e = new int[n], t = new int[n], h = new int[n], f = new int[n];
		for (int i = 0; i < n; i++) {
			e[i] = readInt();
			t[i] = readInt();
			h[i] = readInt();
			f[i] = readInt();
		}
		int ans = 1 << 30;
		for (int i = 0; i < 1 << n; i++) {
			int ecurr = 0;
			int tcurr = tbase;
			int hcurr = hbase;
			int fcurr = fbase;
			for (int j = 0; j < n; j++)
				if ((i & 1 << j) > 0) {
					ecurr += e[j];
					tcurr += t[j];
					hcurr = Math.max(hcurr, h[j]);
					fcurr = Math.max(0, fcurr - f[j]);
				}
			if (ecurr >= emin)
				ans = Math.min(ans, tcurr + hcurr + fcurr);
		}
		out.println(ans);
		out.close();
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
