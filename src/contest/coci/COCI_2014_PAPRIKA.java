package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_PAPRIKA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int n = readInt();
		int x = readInt();
		int[] age = new int[n];
		int[] purpose = new int[n];
		for (int i = 0; i < n; i++) {
			age[i] = readInt();
			purpose[i] = readInt();
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (i != n - 1) {
				int max = Math.max(age[i + 1], age[i]);
				int min = Math.min(age[i + 1], age[i]);
				if (purpose[i] == 0 && purpose[i + 1] == 1) {
					age[i] = max;
					age[i + 1] = min;
				} else if (purpose[i] == 1 && purpose[i + 1] == 0) {
					age[i] = min;
					age[i + 1] = max;
				}
			}
			if (purpose[i] == 1 && age[i] <= x)
				ans++;
			else if (purpose[i] == 0 && age[i] > x)
				ans++;
		}
		ps.println(ans);
		ps.close();
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