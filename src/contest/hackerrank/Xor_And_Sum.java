package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Xor_And_Sum {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = 1000000007;
	static final int N = 314159;

	//	static final int N = 5;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		boolean[] in1 = toBooleanArray(next());
		boolean[] in2 = toBooleanArray(next());
		int len = Math.max(in1.length, in2.length);
		boolean[] a = new boolean[len];
		boolean[] b = new boolean[len];
		for (int i = 0; i < len; i++) {
			if (i < in1.length)
				a[i] = in1[i];
			if (i < in2.length)
				b[i] = in2[i];
		}
		long ans = 0;
		long pow = 1;
		long cnt = 0;
		for (int i = 0; i < len; i++) {
			cnt += b[i] ? 1 : 0;
			if (a[i])
				ans = (ans + (N - cnt + 1) * pow % MOD) % MOD;
			else
				ans = (ans + cnt * pow) % MOD;
			pow = (pow << 1l) % MOD;
		}
		for (int i = a.length; i < N; i++) {
			ans = (ans + pow * cnt % MOD) % MOD;
			pow = (pow << 1) % MOD;
		}
		for (int i = 0; i < len; i++) {
			ans = (ans + pow * cnt % MOD) % MOD;
			pow = (pow << 1) % MOD;
			cnt -= b[i] ? 1 : 0;
		}
		out.println(ans);
		out.close();
	}

	static boolean[] toBooleanArray (String s) {
		boolean[] res = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++)
			res[s.length() - i - 1] = s.charAt(i) == '1';
		return res;
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
