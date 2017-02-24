package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_Server_Hacking {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] A;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		A = new int[N];

		for (int i = 0; i < N; i++)
			A[i] = readInt();

		int lo = 0;
		int hi = N - 1;

		while (lo < hi) {
			int mid1 = (lo + lo + hi) / 3;
			int mid2 = (lo + hi + hi + 2) / 3;
			if (compare(A[mid1], A[mid2]) < 0) {
				hi = mid2 - 1;
			} else {
				lo = mid1 + 1;
			}
		}

		out.println(lo + 1);
		out.close();
	}

	static int compare (int x, int y) {
		for (int i = 2; i * i <= Math.max(x, y) && x > 1 && y > 1; i++) {
			int cnt1 = 0;
			int cnt2 = 0;

			while (x % i == 0) {
				cnt1++;
				x /= i;
			}

			while (y % i == 0) {
				cnt2++;
				y /= i;
			}

			if (cnt1 == 0 && cnt2 == 0 || cnt1 == cnt2)
				continue;
			if (cnt1 == 0)
				return 1;
			if (cnt2 == 0)
				return -1;
			return cnt1 - cnt2;
		}
		return x - y;
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
