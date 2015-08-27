package contest.dmoj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DMOPC_2014_Revenge_Of_The_Bins {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] in = new int[n];
		for (int i = 0; i < n; i++)
			in[i] = readInt();
		ArrayList<State> s = new ArrayList<State>();
		for (int i = n / 2; i < n; i++)
			s.add(new State(in[i], 0));
		Collections.sort(s);
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < s.size(); j++)
				if (in[i] > s.get(j).value)
					s.get(j).cnt++;
		}
		int index = n - 1;
		while (s.size() != 1) {
			boolean valid = true;
			for (int i = 0; i < s.size(); i++) {
				System.out.println(index + " " + s.get(i).value + " " + s.get(i).cnt);
				if (s.get(i).cnt < i + 1)
					valid = false;
			}
			if (valid) {
				System.out.println(s.size());
				return;
			}
			s.remove(new State(in[index--], 0));
			s.remove(new State(in[index--], 0));
			for (int i = 0; i < s.size(); i++) {
				if (in[index / 2] > s.get(i).value)
					s.get(i).cnt--;
			}
			s.add(new State(in[index / 2], 0));
			// for (int i = 0; i < index/2; i++)
		}
		if (in[0] > in[1])
			System.out.println(1);
		else
			System.out.println(0);
		pr.close();
	}

	static class State implements Comparable<State> {
		int value, cnt;

		State (int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}

		@Override
		public int compareTo (State o) {
			return value - o.value;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State) o;
				return s.value == value;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
