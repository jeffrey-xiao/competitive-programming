package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		long cnt = 0;
		long[] a = new long[n];
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			long m = a[i] = readLong();
			max = Math.max(max, m);
			min = Math.min(min, m);
			cnt += m;
		}
		if (cnt % n != 0) {
			out.println("Impossible");
		} else {
			long ans = 0;
			for (int i = 0; i < n; i++) {
				ans += Math.abs(cnt / n - a[i]);
			}
			
			out.println(ans/2);
		}
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

