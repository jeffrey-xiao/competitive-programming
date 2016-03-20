package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_336B_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] tree1, tree2;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String s1 = readLine();
		String s2 = readLine();

		tree1 = new int[200001];
		tree2 = new int[200001];

		for (int i = 0; i < s2.length(); i++) {
			int max = Math.min(i + 1, s1.length());
			int min = Math.max(s1.length() - (s2.length() - 1 - i), 1);
			if (s2.charAt(i) == '0')
				update(tree1, min, max, 1);
			else
				update(tree2, min, max, 1);
		}
		long total = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == '0')
				total += query(tree2, i + 1);
			else
				total += query(tree1, i + 1);
		}
		out.println(total);
		out.close();
	}

	public static void update (int[] tree, int idx, int val) {
		for (int x = idx; x < 200001; x += (x & -x))
			tree[x] += val;
	}

	public static void update (int[] tree, int x1, int x2, int val) {
		update(tree, x1, val);
		update(tree, x2 + 1, -val);
	}

	public static int query (int[] tree, int idx) {
		int sum = 0;
		for (int x = idx; x > 0; x -= (x & -x))
			sum += tree[x];
		return sum;
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
