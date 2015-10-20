package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Luxurious_Smores {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		int l = readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			int variance = 0;
			variance = Math.max(variance, Math.abs(a[i] - a[(i - 1 + n) % n]));
			variance = Math.max(variance, Math.abs(a[i] - a[(i + 1) % n]));
			if (a[i] >= k ^ variance <= l)
				cnt++;
		}
		out.println(cnt);

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
