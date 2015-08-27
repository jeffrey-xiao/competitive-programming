package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Woburn_Challenge_1999_Fight_Club {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int x, y;
		main : while ((x = readInt()) != -1 && (y = readInt()) != -1) {
			ArrayList<Pair> l = new ArrayList<Pair>();
			TreeSet<Integer> ts = new TreeSet<Integer>();
			while (x != 0 && y != 0) {
				l.add(new Pair(x, y));
				ts.add(x);
				ts.add(y);
				x = readInt();
				y = readInt();
			}
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> rev = new HashMap<Integer, Integer>();
			int cnt = 0;
			for (int i : ts) {
				hm.put(i, cnt);
				rev.put(cnt++, i);
			}
			int[] out = new int[cnt];
			for (int i = 0; i < l.size(); i++) {
				out[hm.get(l.get(i).b)]++;
			}
			ArrayList<Integer> pitt = new ArrayList<Integer>();
			for (int i = 0; i < cnt; i++) {
				if (out[i] > 1) {
					System.out.println("INVALID");
					continue main;
				}
				if (out[i] == 0)
					pitt.add(i);
			}
			if (pitt.size() != 1)
				System.out.println("INVALID");
			else
				System.out.println(rev.get(pitt.get(0)));
		}
		pr.close();
	}

	static class Pair {
		int a, b;

		Pair (int a, int b) {
			this.a = a;
			this.b = b;
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
