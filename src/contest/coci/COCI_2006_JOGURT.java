package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2006_JOGURT {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] tree;
	static int[] newTree;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		tree = new int[(1 << N) - 1];
		tree[0] = 1;
		for (int i = 0; i < N - 1; i++) {
			int sz = (1 << (i + 1)) - 1;

			// shift nodes one forward
			for (int j = sz - 1; j >= 0; j--)
				tree[j + 1] = tree[j];
			tree[0] = 1;

			// duplicate nodes with 2 * n + 1
			for (int j = sz + 1; j <= 2 * sz; j++)
				tree[j] = 2 * tree[j - sz] + 1;
			
			// multiply original nodes by 2
			for (int j = 1; j <= sz; j++)
				tree[j] = 2 * tree[j];

			// swap last layer
			int lo = 1;
			int hi = sz + 1;
			
			while (hi < sz * 2 + 1) {
				if (tree[lo] >= 1 << (i + 1) && tree[hi] >= 1 << (i + 1)) {
					int temp = tree[lo];
					tree[lo] = tree[hi];
					tree[hi] = temp;
				}
				lo++;
				hi++;
			}
		}
		
		for (int i = 0; i < (1 << N) - 1; i++)
			out.printf("%d ", tree[i]);
		out.println();
		
		out.close();
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

