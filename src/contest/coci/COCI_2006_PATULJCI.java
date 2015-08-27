package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_PATULJCI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int[] n = new int[9];
		int total = 0;
		for (int x = 0; x < 9; x++)
			total += (n[x] = readInt());
		int a = 0;
		int b = 0;
		main : for (int x = 0; x < 9; x++)
			for (int y = x + 1; y < 9; y++)
				if (total - n[x] - n[y] == 100) {
					a = x;
					b = y;
					break main;
				}
		for (int x = 0; x < 9; x++)
			if (x != a && x != b)
				System.out.println(n[x]);
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
