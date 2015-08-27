package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2007_PRINCEZA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[] before1;
	static int[] after1;
	static int[] before2;
	static int[] after2;
	static int n, k;
	static int[] i1;
	static int[] i2;

	public static void main (String[] args) throws IOException {
		n = readInt();
		k = readInt();
		char[] moves = next().toCharArray();
		State[] s1 = new State[n];
		State[] s2 = new State[n];
		final int[] X = new int[n];
		final int[] Y = new int[n];
		i1 = new int[n];
		i2 = new int[n];
		before1 = new int[n];
		after1 = new int[n];
		before2 = new int[n];
		after2 = new int[n];
		for (int i = 0; i < n; i++) {
			X[i] = readInt();
			Y[i] = readInt();
			s1[i] = new State(i, X[i] + Y[i]);
			s2[i] = new State(i, X[i] - Y[i]);
		}
		Arrays.sort(s1, new Comparator<State>() {
			@Override
			public int compare (State o1, State o2) {
				return o1.value == o2.value ? X[o1.index] - X[o2.index] : o1.value - o2.value;
			}
		});
		Arrays.sort(s2, new Comparator<State>() {
			@Override
			public int compare (State o1, State o2) {
				return o1.value == o2.value ? X[o1.index] - X[o2.index] : o1.value - o2.value;
			}
		});
		for (int i = 0; i < n; i++) {
			i1[s1[i].index] = i;
			i2[s2[i].index] = i;
			before1[i] = i - 1;
			after1[i] = i + 1;
			before2[i] = i - 1;
			after2[i] = i + 1;
		}
		int curr = 0;
		for (int i = 0; i < moves.length; i++) {
			if (moves[i] == 'A') {
				int p = after2[i2[curr]];
				if (p == n || s2[p].value != s2[i2[curr]].value) {
					continue;
				}

				adjust(curr);
				curr = s2[p].index;
			} else if (moves[i] == 'B') {
				int p = after1[i1[curr]];
				if (p == n || s1[p].value != s1[i1[curr]].value) {
					continue;
				}

				adjust(curr);
				curr = s1[p].index;
			} else if (moves[i] == 'D') {
				int p = before2[i2[curr]];
				if (p == -1 || s2[p].value != s2[i2[curr]].value) {
					continue;
				}

				adjust(curr);
				curr = s2[p].index;
			} else if (moves[i] == 'C') {
				int p = before1[i1[curr]];
				if (p == -1 || s1[p].value != s1[i1[curr]].value) {
					continue;
				}

				adjust(curr);
				curr = s1[p].index;
			}
		}
		System.out.println(X[curr] + " " + Y[curr]);

	}

	static class State {
		int index, value;

		State (int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	static void adjust (int x) {
		int b1 = before1[i1[x]];
		int a1 = after1[i1[x]];
		int b2 = before2[i2[x]];
		int a2 = after2[i2[x]];
		if (b1 != -1) {
			after1[b1] = a1;
		}
		if (a1 != n) {
			before1[a1] = b1;
		}

		if (b2 != -1) {
			after2[b2] = a2;
		}
		if (a2 != n) {
			before2[a2] = b2;
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
