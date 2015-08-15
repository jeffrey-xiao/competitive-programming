package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2013_Crowded_Cows {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int d = readInt();
		Cow[] cows = new Cow[n];
		for (int x = 0; x < n; x++) {
			int pos = readInt();
			int height = readInt();
			cows[x] = new Cow(height, pos, x);
		}
		Arrays.sort(cows, new Comparator<Cow>() {

			@Override
			public int compare (Cow o1, Cow o2) {
				return o1.pos - o2.pos;
			}

		});
		TreeSet<Cow> loSeg = new TreeSet<Cow>();
		TreeSet<Cow> hiSeg = new TreeSet<Cow>();
		int count = 0;
		int lo = 0, hi = 0;
		for (int c = 0; c < n; c++) {
			// System.out.println(cows[c].height + " " + cows[c].pos);
			while (hi < n && cows[hi].pos <= cows[c].pos + d) {
				hiSeg.add(cows[hi]);
				hi++;
			}
			while (cows[lo].pos < cows[c].pos - d) {
				// System.out.println("REMOVING " + cows[lo].height);
				loSeg.remove(cows[lo]);
				lo++;
			}
			loSeg.add(cows[c]);
			// System.out.println("HERE C: " + c + " " +loSeg.size());
			// System.out.println(loSeg.last().height + " " +
			// hiSeg.last().height + " " + cows[c].height);
			if (loSeg.last().height >= 2 * cows[c].height
					&& hiSeg.last().height >= 2 * cows[c].height)
				count++;
			hiSeg.remove(cows[c]);
		}
		System.out.println(count);
	}

	static class Cow implements Comparable<Cow> {
		int height;
		int pos;
		int index;

		Cow (int height, int pos, int index) {
			this.height = height;
			this.pos = pos;
			this.index = index;
		}

		@Override
		public int compareTo (Cow o) {
			if (height == o.height)
				return index - o.index;
			return height - o.height;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Cow) {
				Cow c = (Cow) o;
				return c.index == index && c.height == height;
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
