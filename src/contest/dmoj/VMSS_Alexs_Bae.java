package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class VMSS_Alexs_Bae {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		char[] s1 = readLine().replace(" ", "").toLowerCase().toCharArray();
		char[] s2 = readLine().replace(" ", "").toLowerCase().toCharArray();
		Arrays.sort(s1);
		Arrays.sort(s2);
		if (s1.length != s2.length) {
			System.out.println("no");
			return;
		}
		for (int i = 0; i < Math.min(s1.length, s2.length); i++) {
			if (s1[i] != s2[i]) {
				System.out.println("no");
				return;
			}
		}
		System.out.println("yes");
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
