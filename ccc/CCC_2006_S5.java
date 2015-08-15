package ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2006_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int m, n, a, b, c;
	static boolean[] v;
	static boolean[] isEden;
	static int[] steps;
	static int[] movex = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int end;

	public static void main (String[] args) throws IOException {
		m = readInt();
		n = readInt();
		a = readInt();
		b = readInt();
		c = readInt();
		boolean[][] state = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			char[] in = next().toCharArray();
			for (int j = 0; j < n; j++) {
				state[i][j] = in[j] == '*';
			}
		}
		end = getIndex(state);
		steps = new int[1 << (m * n)];
		v = new boolean[1 << (m * n)];
		isEden = new boolean[1 << (m * n)];
		for (int i = 0; i < (1 << (m * n)); i++) {
			steps[i] = -1;
			isEden[i] = true;
		}
		for (int i = 0; i < (1 << (m * n)); i++) {
			if (!v[i]) {
				compute(i, true);

			}
			int ni = getIndex(nextState(getState(i)));
			if (ni != i)
				isEden[ni] = false;
		}
		int res = 1 << 30;
		for (int i = 0; i < (1 << (m * n)); i++) {

			if (isEden[i] && steps[i] != -1) {
				// System.out.println("NEXT");
				res = Math.min(res, steps[i]);
				// print(getState(i));
				// System.out.println(steps[i]);
				// print(nextState(getState(i)));
				// System.out.println(steps[getIndex(nextState(getState(i)))]);
			}
		}
		System.out.println(res == 1 << 30 ? -1 : res);
	}

	private static int compute (int s, boolean b) {
		v[s] = true;
		if (s == end) {
			// print(getState(s));
			return steps[s] = 0;
		}
		int ns = getIndex(nextState(getState(s)));
		// print(getState(s));
		// print(getState(ns));
		// System.out.println("DONE");
		if (v[ns]) {
			if (steps[ns] == -1)
				return steps[s] = -1;
			return steps[s] = steps[ns] + 1;
		}
		int next = compute(ns, false);
		if (next == -1)
			return steps[s] = -1;
		return (steps[s] = next + 1);
	}

	private static void print (boolean[][] ds) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(ds[i][j] ? 1 : 0);
			System.out.println();
		}
		System.out.println();
	}

	static boolean[][] nextState (boolean[][] s) {
		boolean[][] ns = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int count = 0;
				for (int k = 0; k < 8; k++) {
					int nx = i + movex[k];
					int ny = j + movey[k];
					if (nx < 0 || ny < 0 || nx >= m || ny >= n)
						continue;
					count += s[nx][ny] ? 1 : 0;
				}
				if (s[i][j]) {
					if (count < a || count > b)
						ns[i][j] = false;
					else
						ns[i][j] = true;
				} else {
					if (count > c)
						ns[i][j] = true;
					else
						ns[i][j] = false;
				}
			}
		}
		return ns;
	}

	static int getIndex (boolean[][] state) {
		int total = 0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				total = 2 * total + (state[i][j] ? 1 : 0);
		return total;
	}

	static boolean[][] getState (int index) {
		boolean[][] state = new boolean[m][n];
		for (int i = m - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--) {
				state[i][j] = (index % 2) == 1;
				index >>= 1;
			}
		return state;
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
