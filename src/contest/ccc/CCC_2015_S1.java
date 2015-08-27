package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Stack<Integer> curr = new Stack<Integer>();
		for (int x = 0; x < n; x++) {
			int i = readInt();
			if (i == 0)
				curr.pop();
			else
				curr.push(i);
		}
		int sum = 0;
		for (Integer i : curr)
			sum += i;
		System.out.println(sum);

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
