package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2011_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[] v = new boolean[1 << 26];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		boolean[] s = new boolean[n];
		for (int x = 0; x < n; x++)
			s[x] = (readInt() == 1);
		Queue<State> q = new LinkedList<State>();
		q.offer(new State(s, 0));
		v[toIndex(s)] = true;
		while (!q.isEmpty()) {
			State curr = q.poll();
			if (toIndex(curr.s) == 0) {
				System.out.println(curr.moves);
				return;
			}
			for (int x = 0; x < n; x++) {
				if (!curr.s[x]) {
					boolean[] newS = Arrays.copyOf(curr.s, curr.s.length);
					newS[x] = true;
					int nextI = toIndex(newS);
					if (!v[nextI]) {
						v[nextI] = true;
						adjust(newS);
						q.offer(new State(newS, curr.moves + 1));
					}
				}
			}
		}

	}

	private static void adjust (boolean[] newS) {
		int count = 0;
		for (int x = 0; x < newS.length; x++) {
			if (newS[x])
				count++;
			else {
				if (count >= 4)
					for (int y = x - count; y < x; y++)
						newS[y] = false;
				count = 0;
			}
		}
		if (count >= 4)
			for (int y = newS.length - count; y < newS.length; y++)
				newS[y] = false;
	}

	private static int toIndex (boolean[] s) {
		int sum = 0;
		for (int x = 0; x < s.length; x++)
			sum += (s[x] ? 1 << x : 0);
		return sum;
	}

	static class State {
		boolean[] s;
		int moves;

		State (boolean[] s, int moves) {
			this.s = Arrays.copyOf(s, s.length);
			this.moves = moves;
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
