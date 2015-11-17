package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2007_CUDAK {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static HashMap<State, Long> hm = new HashMap<State, Long>();
	static HashMap<State, Long> hm2 = new HashMap<State, Long>();

	public static void main (String[] args) throws IOException {
		long a = readLong();
		long b = readLong();
		int n = readInt();
		Long ans1 = dp2(new State(b, n));
		Long ans2 = dp2(new State(a - 1, n));
		System.out.println(ans1 - ans2);
		long lo = 0;
		long hi = Long.MAX_VALUE;
		while (lo <= hi) {
			long mid = lo + (hi - lo) / 2l;
			if (dp2(new State(mid, n)) - ans2 >= 1) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println(lo);
	}

	static long dp2 (State st) {
		long len = Long.toString(st.l).length();
		int firstD = Long.toString(st.l).charAt(0) - '0';
		if (st.s > len * 9 || st.s < 0)
			return 0;
		if (st.l < 10)
			return st.l >= st.s ? 1 : 0;
		if (hm2.containsKey(st))
			return hm2.get(st);

		long res = 0;
		for (int i = st.s; i >= st.s - firstD + 1; i--) {
			res += dp1(new State(len - 1, i));
		}
		res += dp2(new State(st.l - firstD * (long) Math.pow(10, len - 1), st.s - firstD));
		hm2.put(st, res);
		return res;
	}

	static long dp1 (State st) {
		if (hm.containsKey(st))
			return hm.get(st);
		if ((st.l >= 0) && st.s == 0) {
			return 1;
		}
		if (st.s > 9 * st.l || st.s < 0)
			return 0;

		Long res = 0l;
		for (int i = 0; i <= 9; i++) {
			res += dp1(new State(st.l - 1, st.s - i));
		}
		hm.put(st, res);
		return res;
	}

	static class State {
		long l;
		int s;

		State (long l, int s) {
			this.l = l;
			this.s = s;
		}

		public boolean equals (Object o) {
			if (o instanceof State) {
				State st = (State) o;
				return l == st.l && s == st.s;
			}
			return false;
		}

		public int hashCode () {
			return new Long(l).hashCode() * 31 + new Integer(s).hashCode();
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
