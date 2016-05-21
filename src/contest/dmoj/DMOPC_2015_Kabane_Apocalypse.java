package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Kabane_Apocalypse {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[][] g;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		g = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] in = readLine().toCharArray();

			for (int j = 0; j < N; j++) {
				if (in[j] == '.')
					g[i][j] = 0;
				else
					g[i][j] = 1 << (in[j] - 'A');
			}
		}

		int ans = 0;
		for (int i = 0; i < 1 << 5; i++) {
			int sz = 0;
			for (int j = 0; j < 5; j++)
				if ((i & 1 << j) > 0)
					sz++;
			if (sz % 2 == 1)
				ans -= getSubarrays(i);
			else
				ans += getSubarrays(i);
		}
		
		out.println(ans);
		out.close();
	}

	static int getSubarrays (int val) {
		int[][] occ = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((val & g[i][j]) == 0) {
					occ[i][j] = 1;
					if (i > 0)
						occ[i][j] += occ[i - 1][j];
				}
			}
		}

		int ret = 0;

		Stack<State> s = new Stack<State>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				State add = new State(j, occ[i][j]);


				while (!s.isEmpty() && s.peek().height >= add.height) {
					State state = s.pop();
					int diffWidth = j - state.index;
					int diffHeight = state.height;
					if (s.isEmpty() || s.peek().height < add.height)
						diffHeight -= occ[i][j];
					else
						diffHeight -= s.peek().height;
					add.index = state.index;
					ret += diffWidth * (diffWidth + 1) / 2 * diffHeight;
				}

				s.push(add);
			}
			while (!s.isEmpty()) {
				State state = s.pop();
				int diffWidth = N - state.index;
				int diffHeight = state.height;
				if (s.isEmpty())
					diffHeight -= 0;
				else
					diffHeight -= s.peek().height;
				ret += diffWidth * (diffWidth + 1) / 2 * diffHeight;
			}
		}
		return ret;
	}

	static class State implements Comparable<State> {
		int index, height;
		State (int index, int height) {
			this.index = index;
			this.height = height;
		}
		@Override
		public int compareTo (State s) {
			return height - s.height;
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

