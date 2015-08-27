package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2005_Sum_Em_Up {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			int n1 = readInt();
			int n2 = readInt();
			if (n1 > n2) {
				int temp = n1;
				n1 = n2;
				n2 = temp;
			}
			int total = 0;
			int x = 0;
			for (x = n1; x < n2; x++, total += x) {
				System.out.print(x + "+");
			}
			System.out.println(x + "=" + (total + n1));
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
