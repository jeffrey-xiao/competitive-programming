package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2006_CIRCLE {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static State s1;
	static State s2;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		int k = readInt();
		State s = new State();
		s.s = next();
		for (int i = 0; i < k; i++)
			s = nextState(s.s);
		ArrayList<State> curr = new ArrayList<State>();
		ArrayList<State> next = new ArrayList<State>();
		curr.add(s);
		// System.out.println(s.s);
		for (int i = 0; i < k; i++) {
			for (State state : curr) {
				prevState(state.s);
				if (s1 != null)
					next.add(s1);
				if (s2 != null)
					next.add(s2);
			}
			curr = new ArrayList<State>(next);
			next = new ArrayList<State>();
		}
		HashSet<State> ans = new HashSet<State>();
		for (State state : curr)
			ans.add(state);
		System.out.println(ans.size());
	}

	static void prevState (String s) {
		s1 = new State();
		s2 = new State();
		s1.s += "W";
		s2.s += "B";
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'W') {
				if (s1.s.charAt(i) == 'W') {
					s1.s += 'B';
					s1.B++;
				} else {
					s1.s += 'W';
					s1.W++;
				}
				if (s2.s.charAt(i) == 'W') {
					s2.s += 'B';
					s2.B++;
				} else {
					s2.s += 'W';
					s2.W++;
				}
			} else {
				if (s1.s.charAt(i) == 'W') {
					s1.s += 'W';
					s1.W++;
				} else {
					s1.s += 'B';
					s1.B++;
				}
				if (s2.s.charAt(i) == 'W') {
					s2.s += 'W';
					s2.W++;
				} else {
					s2.s += 'B';
					s2.B++;
				}
			}
		}
		if (s1.s.charAt(n) != s1.s.charAt(0)) {
			s1 = null;
		} else {
			s1.s = s1.s.substring(0, n);
		}
		if (s2.s.charAt(n) != s2.s.charAt(0)) {
			s2 = null;
		} else {
			s2.s = s2.s.substring(0, n);
		}
	}

	static State nextState (String s) {
		State res = new State();

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) != s.charAt((i + 1) % n)) {
				res.s += 'W';
				res.W++;
			} else {
				res.s += 'B';
				res.B++;
			}
		}
		return res;
	}

	static class State {
		String s = "";
		Integer W = 0;
		Integer B = 0;

		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State) o;
				return (s.s + s.s).contains(this.s);
			}
			return false;
		}

		public int hashCode () {
			return W + B;
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
