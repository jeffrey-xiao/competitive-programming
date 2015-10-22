package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class New_Year_Message_To_Mars {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static String text;
	static ArrayList<HashSet<Integer>> cnt = new ArrayList<HashSet<Integer>>();

	public static void main (String[] args) throws IOException {
		text = readLine();
		int n = readInt();

		for (int i = 0; i <= text.length(); i++)
			cnt.add(new HashSet<Integer>());
		for (int i = 0; i < n; i++) {
			String next = next();
			search(next);
		}
		long ans = 0;
		for (int i = 0; i <= text.length(); i++) {
			boolean[] v = new boolean[text.length() + 1];
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(i);
			int sz = 0;
			while (!q.isEmpty()) {
				Integer curr = q.poll();
				for (int next : cnt.get(curr)) {
					if (!v[next]) {
						v[next] = true;
						q.offer(next);
						sz++;
					}
				}
			}
			ans += sz;
		}
		System.out.println(ans);
	}

	static class Seg implements Comparable<Seg> {
		int l, r;

		Seg (int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo (Seg s) {
			return l - s.l;
		}
	}

	public static void search (String pattern) {
		int[] LCP = buildLCP(pattern);
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j))
				j = LCP[j - 1];
			if (text.charAt(i) == pattern.charAt(j))
				j++;
			if (j == pattern.length()) {
				cnt.get(i + 1).add(i - j + 1);
				j = LCP[j - 1];
			}
		}
	}

	private static int[] buildLCP (String pattern) {
		int[] LCP = new int[pattern.length()];
		for (int i = 1; i < pattern.length(); i++) {
			int j = LCP[i - 1];
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = LCP[j - 1];
			if (pattern.charAt(i) == pattern.charAt(j))
				j++;
			LCP[i] = j;
		}
		return LCP;
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