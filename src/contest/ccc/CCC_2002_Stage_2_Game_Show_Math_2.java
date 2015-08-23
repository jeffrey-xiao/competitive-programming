package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Game_Show_Math_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			boolean[][] poss = new boolean[2][64001];
			ArrayList<HashMap<Short, State>> a = new ArrayList<HashMap<Short, State>>();
			int[] nums = new int[n];
			for (int x = 0; x < n; x++) {
				nums[x] = readInt();
				a.add(new HashMap<Short, State>());
			}

			int target = readInt();
			poss[0][32000 + nums[0]] = true;
			a.get(0).put((short) (nums[0] + 32000), new State(' ', -1));
			int next = 0;
			for (int x = 1; x < n; x++) {
				poss[x % 2] = new boolean[64001];
				for (int y = 0; y <= 64000; y++) {
					if (poss[(x - 1) % 2][y]) {
						next = (y - 32000) + nums[x] + 32000;
						// System.out.println(next -32000 + " " + x);
						if (next >= 0 && next <= 64000 && !poss[x % 2][next]) {
							poss[x % 2][next] = true;
							a.get(x).put((short) next, new State('+', y));
						}
						next = (y - 32000) - nums[x] + 32000;
						// System.out.println(next -32000 + " " + x);
						if (next >= 0 && next <= 64000 && !poss[x % 2][next]) {
							poss[x % 2][next] = true;
							a.get(x).put((short) next, new State('-', y));
						}
						next = (y - 32000) * nums[x] + 32000;
						// System.out.println(next -32000 + " " + x);
						if (next >= 0 && next <= 64000 && !poss[x % 2][next]) {
							poss[x % 2][next] = true;
							a.get(x).put((short) next, new State('*', y));
						}
						next = (y - 32000) / nums[x] + 32000;
						// System.out.println(next -32000 + " " + x);
						if (next >= 0 && next <= 64000
								&& (y - 32000) % nums[x] == 0
								&& !poss[x % 2][next]) {
							poss[x % 2][next] = true;
							a.get(x).put((short) next, new State('/', y));
						}
					}
				}
			}
			if (!poss[(n - 1) % 2][target + 32000]) {
				System.out.println("NO EXPRESSION");
			} else {
				Stack<String> s = new Stack<String>();
				int curr = target + 32000;
				int i = n - 1;
				while (curr != -1) {
					s.add(nums[i] + "");
					State prevState = a.get(i).get((short) curr);
					i--;
					if (prevState.operator != ' ')
						s.add(prevState.operator + "");
					curr = prevState.prev;
				}
				for (int x = s.size(); x > 0; x--)
					System.out.print(s.pop());
				System.out.println("=" + target);
			}
		}
	}

	static class State {
		int prev;
		char operator;

		State (char operator, int prev) {
			this.operator = operator;
			this.prev = prev;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
