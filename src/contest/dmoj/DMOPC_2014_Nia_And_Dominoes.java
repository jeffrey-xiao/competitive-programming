package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Nia_And_Dominoes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int A, B, M;
	static long N;
	
	public static void main (String[] args) throws IOException {
		A = readInt();
		B = readInt();
		M = readInt();
		N = readLong();
		
		Cycle c = getCycle(new State(1, 0));
		if (N < c.start) {
			State ans = new State(1, 0);
			for (int i = 0; i < N; i++) {
				ans = f(ans);
			}
			out.println(ans.a);
		} else {
			N = (N - c.start) % c.length + c.start;
			State ans = new State(1, 0);
			for (int i = 0; i < N; i++) {
				ans = f(ans);
			}
			out.println(ans.a);
		}
		out.close();
	}

	static class Cycle {
		long start, length;
		Cycle (long start, long length) {
			this.start = start;
			this.length = length;
		}
	}

	static class State {
		long a, b;
		State (long a, long b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State)o;
				return a == s.a && s.b == b;
			}
			return false;
		}
	}
	
	static State f (State s) {
		return new State((s.a * A % M + s.b * B % M) % M, s.a);
	}
	
	static Cycle getCycle (State s) {
		long power = 1;
		long length = 1;
		State tortoise = new State(s.a, s.b);
		State hare = f(s);
		
		while (!tortoise.equals(hare)) {
			if (power == length) {
				tortoise = hare;
				power *= 2;
				length = 0;
			}
			hare = f(hare);
			length++;
		}
		
		hare = new State(s.a, s.b);
		for (int i = 0; i < length; i++)
			hare = f(hare);
		
		int start = 0;
		tortoise = new State(s.a, s.b);
		
		while (!tortoise.equals(hare)) {
			tortoise = f(tortoise);
			hare = f(hare);
			start++;
		}
		return new Cycle(start, length);
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
