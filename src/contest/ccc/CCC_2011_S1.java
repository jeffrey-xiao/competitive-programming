package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2011_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int t = 0;
		int s = 0;
		for (int x = 0; x < n; x++) {
			String st = readLine();
			for (int y = 0; y < st.length(); y++) {
				char curr = st.charAt(y);
				if (curr == 'S' || curr == 's')
					s++;
				else if (curr == 'T' || curr == 't')
					t++;
			}
		}
		System.out.println(t > s ? "English" : "French");
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
