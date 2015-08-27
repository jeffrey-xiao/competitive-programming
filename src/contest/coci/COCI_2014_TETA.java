package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_TETA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int k = readInt();
		int[] price = new int[k];
		boolean[] in = new boolean[k];
		for (int i = 0; i < k; i++)
			price[i] = readInt();
		int p = readInt();
		int a = readInt() - 1, b = readInt() - 1, c = readInt() - 1, d = readInt() - 1;
		in[a] = true;
		in[b] = true;
		in[c] = true;
		in[d] = true;
		int t = readInt();
		int[] cnt = new int[k];
		int total = 0;
		for (int i = 0; i < t; i++) {
			int j = readInt() - 1;
			if (in[j]) {
				cnt[j]++;
			} else
				total += price[j];
		}
		while (cnt[a] > 0 || cnt[b] > 0 || cnt[c] > 0 || cnt[d] > 0) {
			int indiv = 0;
			indiv += cnt[a] > 0 ? price[a] : 0;
			indiv += cnt[b] > 0 ? price[b] : 0;
			indiv += cnt[c] > 0 ? price[c] : 0;
			indiv += cnt[d] > 0 ? price[d] : 0;
			total += Math.min(p, indiv);
			cnt[a]--;
			cnt[b]--;
			cnt[c]--;
			cnt[d]--;
		}
		ps.println(total);
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