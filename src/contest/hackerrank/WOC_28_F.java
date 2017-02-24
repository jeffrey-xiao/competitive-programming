package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_28_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int G, ans;
	static char[] sorted;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		G = readInt();
		long start = System.currentTimeMillis();
		//		String s = next();
		for (int g = 1; g <= G; g++) {
			String s = next();
			//			String s = "";
			//			for (int i = 0; i < 1000; i++)
			//				s += "abcdefghijklmnopqrstuvwxyz".charAt((int)(Math.random() * 26));
			ans = 1 << 30;
			s = preprocess(s);

			out.println(solve(s));
		}
		out.println(System.currentTimeMillis() - start);
		out.close();
	}

	static String preprocess (String s) {
		char[] sorted = s.toCharArray();
		Arrays.sort(sorted);
		int index = 0;
		int best = 0;
		while (best < s.length() && s.charAt(best) == sorted[index]) {
			index++;
			best++;
		}
		return s.substring(best);
	}

	static int solve (String s) {
		if (s.equals(""))
			return 0;
		String ss = s + s;

		ArrayList<String> candidates = new ArrayList<String>();

		for (int i = ss.length() - 1; i >= s.length(); i--)
			candidates.add(ss.substring(i - s.length() + 1, i + 1));

		for (String w : candidates) {
			ans = Math.min(ans, compute(new StringBuilder(new String(w))));
		}
		return ans;
	}

	static int compute (StringBuilder w) {
		Interval prev = null;
		ArrayList<ArrayList<Interval>> intervals = new ArrayList<ArrayList<Interval>>();

		for (int i = 0; i < 26; i++)
			intervals.add(new ArrayList<Interval>());

		for (int i = 0; i < w.length();) {
			char c = w.charAt(i++);
			Interval curr = new Interval(1);
			while (i < w.length() && w.charAt(i) == c) {
				curr.sz++;
				i++;
			}
			curr.left = prev;
			if (prev != null)
				prev.right = curr;
			intervals.get(c - 'a').add(curr);
			prev = curr;
		}

		int currAns = 0;
		int active = -2;
		for (int i = 0; i < 26; i++) {
			if (intervals.get(i).size() == 0)
				continue;
			int canSub = 0;
			for (int j = intervals.get(i).size() - 2; j >= 0; j--) {
				Interval curr = intervals.get(i).get(j);
				if (curr.right == intervals.get(i).get(j + 1)) {
					curr.sz += curr.right.sz;
					curr.right = curr.right.right;
					intervals.get(i).remove(j + 1);
				}
			}

			currAns += intervals.get(i).size();
			for (int j = 0; j < intervals.get(i).size(); j++) {
				Interval curr = intervals.get(i).get(j);
				if (curr.canSub == active)
					canSub++;
			}

			if (canSub > 0) {
				currAns--;
			}

			for (int j = 0; j < intervals.get(i).size(); j++) {
				Interval curr = intervals.get(i).get(j);
				if (canSub >= 2 || canSub == 0) {
					if (curr.right != null) {
						curr.right.canSub = i;
					}
				} else if ((canSub == 1 && curr.canSub != active) || intervals.get(i).size() == 1) {
					if (curr.right != null) {
						curr.right.canSub = i;
					}
				}

				if (curr.left != null)
					curr.left.right = curr.right;
				if (curr.right != null)
					curr.right.left = curr.left;
			}
			active = i;
		}

		return currAns;
	}

	static class Interval {
		int sz;
		int canSub;
		Interval left, right;

		Interval (int sz) {
			this.sz = sz;
			this.canSub = -1;
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
