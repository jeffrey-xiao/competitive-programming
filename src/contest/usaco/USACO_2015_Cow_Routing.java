package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Routing {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int a = readInt();
		int b = readInt();
		int n = readInt();
		int min = Integer.MAX_VALUE;
		for (int x = 0; x < n; x++) {
			int cost = readInt();
			int num = readInt();
			boolean isReach = false;
			for (int y = 0; y < num; y++) {
				int next = readInt();
				if (next == a)
					isReach = true;
				else if (next == b && isReach)
					min = Math.min(min, cost);
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
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
