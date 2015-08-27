package contest.coci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2014_JANJE {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	static int[] color = new int[20];
	static int[] cnt = new int[3];
	static long res2 = 0, res1 = 0;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		long two = k * (k - 1) / 2;
		long three = k * (k - 1) * (k - 2) / 6;
		for (int i = 0; i < 20; i++)
			adj.add(new ArrayList<Integer>());
		if (n == 1) {
			// for (int i = 0; i < 19; i++) {
			// add(i, i+1);
			// }
			// color(0, 20);
			res1 = 2;
			res2 = 3 * (1 << 19) - 6;
		} else if (n == 2) {
			add(0, 1);
			add(0, 2);
			add(2, 3);
			add(2, 4);
			add(2, 5);
			add(2, 6);
			add(3, 4);
			add(5, 6);
			add(2, 7);
			color(0, 8);
		} else if (n == 3) {
			add(0, 1);
			add(1, 2);
			add(1, 3);
			color(0, 4);
			res1 = 2;
		} else if (n == 4) {
			// add(0, 1);
			// add(1, 2);
			// add(2, 3);
			// add(2, 4);
			// add(2, 5);
			// add(5, 6);
			// add(5, 7);
			// add(5, 8);
			// add(5, 9);
			// add(9, 10);
			// add(9, 11);
			// add(9, 12);
			// add(9, 13);
			// color(0, 14);
			res1 = 2;
			res2 = 24570;
		} else if (n == 5) {
			add(0, 1);
			add(0, 2);
			add(1, 2);
			add(2, 3);
			add(2, 4);
			add(3, 4);
			color(0, 5);
		} else if (n == 6) {
			res2 = 6;
		} else if (n == 7) {
			add(0, 1);
			add(1, 2);
			add(2, 3);
			add(2, 4);
			add(2, 5);
			add(2, 6);
			add(2, 7);
			add(3, 4);
			add(5, 6);
			color(0, 8);
		} else {
			res1 = 2;
			res2 = 357913942 * 3 - 6;
		}
		System.out.println(res1 * two + res2 * three);

		pr.close();
	}

	static void add (int i, int j) {
		adj.get(i).add(j);
		adj.get(j).add(i);
	}

	static void color (int i, int n) {
		if (i == n) {
			// for (int j = 0; j < n; j++)
			// System.out.print(color[j] + " ");
			// System.out.println();
			boolean valid = true;
			for (int j = 0; j < n; j++)
				for (int k : adj.get(j))
					if (color[j] == color[k])
						valid = false;
			if (valid && cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0)
				res2++;
			return;
		}
		for (int j = 0; j < 3; j++) {
			color[i] = j;
			cnt[j]++;
			color(i + 1, n);
			cnt[j]--;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
