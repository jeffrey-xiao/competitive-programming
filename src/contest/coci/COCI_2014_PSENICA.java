package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class COCI_2014_PSENICA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();
		int[] cnt = new int[100001];
		for (int i = 0; i < n; i++) {
			cnt[readInt()]++;
		}
		LinkedList<State> s = new LinkedList<State>();
		for (int i = 1; i <= 100000; i++)
			if (cnt[i] > 0)
				s.add(new State(i, cnt[i]));
		Collections.sort(s);
		State s1 = new State(0, 0);
		State s2 = new State(0, 0);
		while (s.size() >= 3) {
			s1 = s.pollFirst();
			s2 = s.pollLast();
			if (s1.count <= s2.count) {
				s2.count -= s1.count;
				s.peekFirst().count += s1.count;
				s.peekLast().count += s1.count;
				if (s.size() == 1) {
					System.out.println("Mirko");
					s.addLast(s2);
					System.out.println(s.peekFirst().height + " " + s.peekLast().height);
					return;
				}
				if (s2.count > 0)
					s.addLast(s2);
			} else {
				s1.count -= s2.count;
				s.peekFirst().count += s2.count;
				s.peekLast().count += s2.count;
				s.addFirst(s1);
			}
		}
		System.out.println("Slavko");
		System.out.println(s.peekFirst().height + " " + s.peekLast().height);
	}

	static class State implements Comparable<State> {
		int height, count;

		State (int height, int count) {
			this.height = height;
			this.count = count;
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