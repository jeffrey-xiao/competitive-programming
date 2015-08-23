package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Game_Show_Math {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int target;
	static int[] nums;
	static HashSet<State> hs = new HashSet<State>();

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			// System.out.println(t);
			hs.clear();
			int n = readInt();
			nums = new int[n];
			for (int x = 0; x < n; x++)
				nums[x] = readInt();
			target = readInt();
			if (!compute(1, n, nums[0], nums[0] + ""))
				System.out.println("NO EXPRESSION");
		}
	}

	public static boolean compute (int i, int n, int value, String s) {
		// System.out.println(i + " " + value);
		if (n == i) {
			if (value == target) {
				System.out.println(s + "=" + target);
				return true;
			}
			return false;
		}
		if (hs.contains(new State(i, value)))
			return false;
		hs.add(new State(i, value));
		if (compute(i + 1, n, value + nums[i], s + "+" + nums[i])
				|| compute(i + 1, n, value - nums[i], s + "-" + nums[i])
				|| compute(i + 1, n, value * nums[i], s + "*" + nums[i]))
			return true;
		if (value % nums[i] == 0
				&& compute(i + 1, n, value / nums[i], s + "/" + nums[i]))
			return true;
		return false;
	}

	static class State {
		int i, value;

		State (int i, int value) {
			this.i = i;
			this.value = value;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State) o;
				return i == s.i && value == s.value;
			}
			return false;
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
