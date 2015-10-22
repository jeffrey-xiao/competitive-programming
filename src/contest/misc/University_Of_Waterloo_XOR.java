package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class University_Of_Waterloo_XOR {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// XOR IS ASSOCIATIVE
	// XOR(A,B) = XOR(XOR(1,B), XOR(1, A-1)
	public static void main (String[] args) throws IOException {
		for (int q = readInt(); q > 0; q--) {
			System.out.println(XOR(readInt(), readInt()));
		}
	}

	public static long XOR (int a, int b) {
		return XOR(b) ^ XOR(a - 1);
	}

	public static long XOR (int a) {
		int[] value = {1, a + 1, 0, a};
		return value[(a + 1) % 4];
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
