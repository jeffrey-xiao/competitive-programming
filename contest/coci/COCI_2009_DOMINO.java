package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2009_DOMINO {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// int sum = 0;
		// for(int x = readInt(); x > 0; x--)
		// sum+= (x*(x+1)+(x+1)*(x)/2);
		// System.out.println(sum);
		int n = readInt();
		System.out.println((n) * (n + 1) * (n + 2) / 2);
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
