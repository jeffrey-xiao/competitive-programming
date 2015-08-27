package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2010_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[] chars = new char[n];
		String[] binarySeq = new String[n];
		for (int x = 0; x < n; x++) {
			chars[x] = next().charAt(0);
			binarySeq[x] = next();
		}
		String seq = next();
		String ans = "";
		int x = 0;
		while (seq.length() > 0) {
			while (!seq.startsWith(binarySeq[x]))
				x = (x + 1) % binarySeq.length;
			ans += chars[x];
			seq = seq.substring(binarySeq[x].length());
		}
		System.out.println(ans);
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
