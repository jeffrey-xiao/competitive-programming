package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class COCI_2008_BST_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		TreeSet<State> ts = new TreeSet<State>();
		ts.add(new State(0, -1));
		ts.add(new State(n + 1, -1));
		long total = 0;
		for (int i = 0; i < n; i++) {
			int c = readInt();
			State lower = ts.lower(new State(c, 0));
			State higher = ts.higher(new State(c, 0));
			int nd = -1;
			if (lower != null)
				nd = Math.max(nd, lower.depth);
			if (higher != null)
				nd = Math.max(nd, higher.depth);
			nd++;
			ps.println(total += nd);
			ts.add(new State(c, nd));
		}
		ps.close();
	}

	static class State implements Comparable<State> {
		int num, depth;

		State (int num, int depth) {
			this.num = num;
			this.depth = depth;
		}

		@Override
		public int compareTo (State o) {
			return num - o.num;
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
