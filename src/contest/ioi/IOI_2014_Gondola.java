package contest.ioi;

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

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final long MOD = 1000000009;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int s = readInt();
		int[] input = new int[s];
		for (int x = 0; x < s; x++)
			input[x] = readInt();

		if (n <= 3) {
			ps.println(valid(s, input));
		} else if (n <= 6) {
			ArrayList<Entry> res = replacement(s, input);
			ps.print(res.size() + " ");
			for (Entry e : res)
				ps.print(e.original + " ");
			ps.println();
		} else {
			ps.println(countReplacement(s, input));
		}
		ps.close();
	}

	public static long countReplacement (int n, int[] seq) {
		if (valid(n, seq) == 0)
			return 0;
		boolean fixed = false;
		ArrayList<Integer> replaced = new ArrayList<Integer>();
		long total = 1;
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (seq[i] <= n) {
				fixed = true;
				max = Math.max(max, seq[i]);
			} else {
				replaced.add(seq[i]);
			}
		}
		Collections.sort(replaced);
		int hidden = 0;
		int prevVal = n;
		for (int i = 0; i < replaced.size(); i++) {
			hidden = replaced.get(i) - prevVal - 1;
			prevVal = replaced.get(i);
			total = (total * pow(replaced.size() - i, hidden)) % MOD;
		}
		if (!fixed)
			return total * n % MOD;
		return total;
	}

	public static long pow (long n, long m) {
		if (m == 0)
			return 1;
		if (m == 1)
			return n;
		if (m % 2 == 0)
			return pow(n * n % MOD, m / 2);
		return n * pow(n * n % MOD, m / 2) % MOD;
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
			if (inputSeq[x % n] <= n && inputSeq[x % n] % n != (inputSeq[r] - r + x) % n)
				return 0;
			hs.add(inputSeq[x]);
		}
		if (hs.size() == n)
			return 1;
		return 0;
	}

	public static ArrayList<Entry> replacement (int n, int[] seq) {
		int maxValue = 0;
		int original = 0;
		HashSet<Integer> hs = new HashSet<Integer>();
		ArrayList<Integer> missing = new ArrayList<Integer>();
		ArrayList<Entry> list = new ArrayList<Entry>();
		boolean fillMissing = false;
		for (int i = 0; i < n; i++) {
			if (seq[i] <= n)
				original = i;
			maxValue = Math.max(maxValue, seq[i]);
			hs.add(seq[i]);
		}
		for (int i = n + 1; i <= maxValue; i++) {
			if (!hs.contains(i))
				missing.add(i);
		}
		Collections.sort(missing);
		for (int i = 0; i < n; i++) {
			int og = (seq[original] - 1 + (i - original + n) % n) % n + 1;
			if (og != seq[i]) {
				if (!fillMissing && missing.size() > 0 && seq[i] > missing.get(missing.size() - 1)) {
					for (int j = 0; j < missing.size(); j++) {
						list.add(j == 0 ? new Entry(og, missing.get(j)) : new Entry(missing.get(j - 1), missing.get(j)));
					}
					list.add(new Entry(missing.get(missing.size() - 1), seq[i]));
					fillMissing = true;
				} else {
					list.add(new Entry(og, seq[i]));
				}

			}
		}
		Collections.sort(list);
		return list;
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
