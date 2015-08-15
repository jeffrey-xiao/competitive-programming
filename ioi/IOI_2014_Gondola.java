package ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class IOI_2014_Gondola {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		if (n <= 3) {
			int s = readInt();
			int[] input = new int[s];
			for (int x = 0; x < s; x++)
				input[x] = readInt();
			System.out.println(valid(s, input));
		} else if (n <= 6) {
			int s = readInt();
			int[] input = new int[s];
			for (int x = 0; x < s; x++)
				input[x] = readInt();
			ArrayList<Entry> result = replacement(s, input);
			System.out.print(result.size() + " ");
			for (Entry i : result)
				System.out.print(i.original + " ");
		}
	}

	public static int valid (int n, int[] inputSeq) {
		HashSet<Integer> hs = new HashSet<Integer>();
		int r = 0;
		for (int x = 0; x < n; x++)
			if (inputSeq[x] <= n) {
				r = x;
				break;
			}
		hs.add(inputSeq[r]);
		for (int x = 0; x < n; x++) {
			if (inputSeq[x % n] <= n
					&& inputSeq[x % n] % n != (inputSeq[r] - r + x) % n) {
				return 0;
			}
			hs.add(inputSeq[x]);
		}
		if (hs.size() == n)
			return 1;
		return 0;
	}

	public static ArrayList<Entry> replacement (int n, int[] seq) {
		ArrayList<Entry> ts = new ArrayList<Entry>();
		HashSet<Integer> hs = new HashSet<Integer>();
		int largest = 0;
		int r = -1;
		for (int x = 0; x < n; x++) {
			if (seq[x] <= n) {
				r = x;
				break;
			}
		}
		if (r == -1) {
			for (int x = 0; x < n; x++) {
				hs.add(seq[x]);
				ts.add(new Entry(x + 1, seq[x]));
				if (seq[x % n] > largest) {
					largest = seq[x % n];
				}
			}
			for (int x = n + 1; x < largest; x++) {
				if (!hs.contains(x)) {
					ts.add(new Entry(x, x + largest));
				}
			}
		} else {
			for (int x = r; x < n + r; x++)
				if (seq[x % n] > n) {
					hs.add(seq[x % n]);
					int add = (seq[r] + x - r) % n;
					if (add == 0)
						add = n;
					ts.add(new Entry(add, seq[x % n]));
					if (seq[x % n] > largest) {
						largest = seq[x % n];
					}
				}
			for (int x = n + 1; x < largest; x++) {
				if (!hs.contains(x)) {
					ts.add(new Entry(x, x + largest));
				}
			}
		}
		Collections.sort(ts);
		return ts;
	}

	static class Entry implements Comparable<Entry> {
		int original;
		int replacement;

		Entry (int original, int replacement) {
			this.original = original;
			this.replacement = replacement;
		}

		@Override
		public int compareTo (Entry o) {
			return replacement - o.replacement;
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
