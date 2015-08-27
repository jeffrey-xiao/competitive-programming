package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valentines_P1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int count = 0;
		ArrayList<Group> g = new ArrayList<Group>();
		int i = 0;
		for (int x = 0; x < n; x++) {
			String s = readLine();
			if (isPrincess(s, "Princess")) {
				count++;
			} else {
				if (count > 0)
					g.add(new Group(count, 0, i++));
				count = 0;
			}
		}
		if (count > 0)
			g.add(new Group(count, 0, i++));
		for (int x = 0; x < g.size(); x++) {
			g.get(x).time = readInt();
		}
		Collections.sort(g);
		ArrayList<Group> poss = new ArrayList<Group>();
		int curr = 0;
		for (int x = 0; x < g.size(); x++) {
			// System.out.println(g.get(x).time + " " + g.get(x).size + " " +
			// g.get(x).index);
			if (g.get(x).time >= curr + 1) {
				poss.add(g.get(x));
				curr++;
			}
		}
		for (int x = 0; x < poss.size(); x++) {
			for (int y = x - 1; y >= 0; y--) {
				if (poss.get(y).time >= y + 2 && ((poss.get(y + 1).size > poss.get(y).size) || (poss.get(y + 1).index < poss.get(y).index && poss.get(y).size == poss.get(y + 1).size))) {
					Group gr = poss.get(y + 1);
					poss.set(y + 1, poss.get(y));
					poss.set(y, gr);

				}
			}
		}
		for (Group gr : poss)
			System.out.println(gr.index + 1);
	}

	private static boolean isPrincess (String match, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(match);
		int count = 0;
		while (m.find()) {
			count += 1;
		}
		return count == 1;
	}

	static class Group implements Comparable<Group> {
		int size, time, index;

		Group (int size, int time, int index) {
			this.size = size;
			this.time = time;
			this.index = index;
		}

		@Override
		public int compareTo (Group o) {
			if (time == o.time && o.size == size)
				return index - o.index;
			if (time == o.time)
				return o.size - size;
			return time - o.time;
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
