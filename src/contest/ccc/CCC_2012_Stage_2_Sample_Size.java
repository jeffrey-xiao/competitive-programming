package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2012_Stage_2_Sample_Size {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] p = new int[n];
		for (int x = 0; x < n; x++)
			p[x] = readInt();
		inner : for (int x = 1;; x++) {
			main : for (int z = 0; z < n; z++) {
				for (double y = 0; y <= x; y++) {
					if ((int) (Math.round(y / x * 100 + 0.001)) == p[z])
						continue main;
				}
				continue inner;
			}
			System.out.println(x);
			break;
		}
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
