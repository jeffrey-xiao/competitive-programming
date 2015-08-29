package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IOI_1995_Letter_Game {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static HashSet<String> dict = new HashSet<String>();
	static int[] val = {2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		String in = next();
		while (!in.equals(".")) {
			dict.add(in);
			in = readLine();
		}
		dict.add("");
		in = next();
		TreeSet<Pair> res = new TreeSet<Pair>();
		int max = 0;
		for (int k = 0; k < 1 << in.length(); k++) {
			for (int i = 0; i < 1 << in.length(); i++) {
				String a = "";
				String b = "";
				for (int j = 0; j < in.length(); j++) {
					if ((k & (1 << j)) > 0)
						continue;
					if ((i & (1 << j)) > 0)
						a += in.charAt(j);
					else
						b += in.charAt(j);
				}
				for (String s1 : permute(a)) {
					for (String s2 : permute(b)) {
						if (dict.contains(s1) && dict.contains(s2)) {
							int newScore = getScore(s1) + getScore(s2);
							if (newScore > max) {
								max = newScore;
								res.clear();
							}
							if (newScore == max) {
								res.add(new Pair(s1, s2));
							}
						}
					}
				}
			}
		}
		System.out.println(max);
		for (Pair p : res) {
			if (p.s1.equals(""))
				System.out.println(p.s2);
			else
				System.out.println(p.s1 + " " + p.s2);
		}
	}

	static class Pair implements Comparable<Pair> {
		String s1, s2;

		Pair (String s1, String s2) {
				if (s1.compareTo(s2) < 0 && !s1.equals("") || s2.equals("")) {
					this.s1 = s1;
					this.s2 = s2;
				} else {
					this.s1 = s2;
					this.s2 = s1;
				}
		}

		public int compareTo (Pair p) {
			if (s1.compareTo(p.s1) < 0)
				return -1;
			else if (s1.compareTo(p.s1) > 0)
				return 1;
			if (s2.compareTo(p.s2) < 0)
				return -1;
			else if (s2.compareTo(p.s2) > 0)
				return 1;
			return 0;
		}
	}

	static int getScore (String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++)
			res += val[s.charAt(i) - 'a'];
		return res;
	}

	static Queue<String> permute (String s) {
		Queue<String> res = new ArrayDeque<String>();
		permute(s.toCharArray(), 0, res);
		return res;
	}

	static void permute (char[] s, int i, Queue<String> res) {
		if (i >= s.length - 1) {
			res.offer(new String(s));
			return;
		}
		for (int j = i + 1; j < s.length; j++) {
			swap(s, i, j);
			permute(s, i + 1, res);
			swap(s, i, j);
		}
		permute(s, i + 1, res);
	}

	static void swap (char[] c, int i, int j) {
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
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