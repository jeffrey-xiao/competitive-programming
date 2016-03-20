package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Great_Sequence {

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
		int q = readInt();
		int[] sum = new int[n + 1];
		ArrayList<TreeSet<Integer>> list = new ArrayList<TreeSet<Integer>>();
		for (int i = 0; i <= 2001; i++) {
			list.add(new TreeSet<Integer>());
		}

		for (int i = 1; i <= n; i++) {
			int val = readInt();
			list.get(val + 1000).add(i);
			sum[i] = val + sum[i - 1];
		}
		for (int i = 0; i < q; i++) {
			int a = readInt();
			int b = readInt();
			int x = readInt();
			int y = readInt();
			Integer low1 = list.get(a + 1000).floor(y);
			Integer low2 = list.get(b + 1000).floor(y);
			if (low1 == null || low2 == null || low1 < x || low2 < x || sum[y] - sum[x - 1] <= k)
				out.println("No");
			else
				out.println("Yes");
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
