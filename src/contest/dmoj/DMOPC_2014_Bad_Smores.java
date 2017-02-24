package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Bad_Smores {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long xf, yf, r, xw, yw, l;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		xf = readInt();
		yf = readInt();
		r = readInt();

		xw = readInt();
		yw = readInt();
		l = readInt();

		long ans = 0;

		for (long i = yf - r; i <= yf + r; i++) {
			long fl = xf - r;
			long fr = xf + r;
			if (i != yf) {
				fl = (long)(Math.ceil(xf - Math.sqrt(r * r - (yf - i) * (yf - i))));
				fr = (long)(Math.floor(xf + Math.sqrt(r * r - (yf - i) * (yf - i))));
			}
			long range = l - Math.abs(i - yw);
			if (range < 0)
				continue;
			long wl = xw - range;
			long wr = xw + range;
			ans += getOverlap(fl, fr, wl, wr);
		}

		long dx = (xf - xw);
		long dy = (yf - yw);

		int decCnt = 0;

		if (dx * dx + dy * dy <= r * r) {
			ans--;
			decCnt++;
		}

		if (Math.abs(dx) + Math.abs(dy) <= l) {
			ans--;
			decCnt++;
		}

		if (decCnt == 2 && dx == 0 && dy == 0)
			ans++;

		out.println(ans);
		out.close();
	}

	static long getOverlap (long min1, long max1, long min2, long max2) {
		return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2) + 1);
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
