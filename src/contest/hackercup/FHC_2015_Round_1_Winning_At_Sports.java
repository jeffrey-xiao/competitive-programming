package contest.hackercup;

import java.util.*;
import java.io.*;

public class FHC_2015_Round_1_Winning_At_Sports {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = (int) (1e9 + 7);

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int[][] unstress = new int[2001][2001];
		for (int x = 0; x <= 2000; x++) {
			for (int y = 0; y <= 2000; y++) {
				if (y >= x)
					continue;
				if (y == 0)
					unstress[x][y] = 1;
				else if (y + 1 == x)
					unstress[x][y] = unstress[x][y - 1] % MOD;
				else
					unstress[x][y] = (unstress[x - 1][y] % MOD + unstress[x][y - 1] % MOD) % MOD;
			}
		}

		int n = readInt();
		for (int x = 1; x <= n; x++) {
			String[] s = next().split("-");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			out.printf("Case #%d: %d %d\n", x, unstress[a][b], unstress[b + 1][b]);
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

}