package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_142C {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		int[] a = new int[m];
		int[] b = new int[m];
		long[] in = new long[n];
		long N = (long)n;
		for (int i = 0; i < m; i++) {
			a[i] = readInt() - 1;
			b[i] = readInt() - 1;
			in[a[i]]++;
			in[b[i]]++;
		}
		long cnt = N * (N - 1) * (N - 2) / 6;

		long total = 0;
		for (int i = 0; i < n; i++) {
			if (in[i] != 0)
				total += (in[i] * (N - 1 - in[i]));
		}
		System.out.println(cnt - (total / 2));
		pr.close();
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
