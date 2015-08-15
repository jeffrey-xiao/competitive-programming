package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2014_Auto_Complete {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		Entry[] entries = new Entry[n];
		for (int x = 0; x < n; x++)
			entries[x] = new Entry(next(), x + 1);
		Arrays.sort(entries, new Comparator<Entry>() {
			@Override
			public int compare (Entry arg0, Entry arg1) {
				String s1 = arg0.word;
				String s2 = arg1.word;
				for (int x = 0; x < Math.min(s1.length(), s2.length()); x++)
					if (s1.charAt(x) != s2.charAt(x))
						return (s1.charAt(x)) - (s2.charAt(x));
				return s1.length() - s2.length();
			}
		});
		/*
		 * for(int x = 0; x < entries.length; x++)
		 * System.out.println(entries[x].word);
		 */
		for (int x = 0; x < m; x++) {
			int position = readInt();
			String s = next();

			int firstPos = binarySearch(s, entries);
			// System.out.println(firstPos);
			if (firstPos + position - 1 >= n
					|| entries[firstPos + position - 1].word.indexOf(s) != 0)
				System.out.println(-1);
			else {
				// System.out.println();
				// System.out.println(firstPos);
				// System.out.println(entries[firstPos+position-1].word);
				System.out.println(entries[firstPos + position - 1].pos);
			}
			/*
			 * int count = 0; boolean found = false; for(int y = firstPos; y <
			 * n; y++){ if(entries[y].word.indexOf(s) == 0) count++; if(count ==
			 * position){ System.out.println(entries[y].pos); found = true;
			 * break; } if(count > 0 && entries[y].word.indexOf(s) != 0 ) break;
			 * } if(!found) System.out.println(-1);
			 */
		}
	}

	private static int binarySearch (String s, Entry[] entries) {
		int lower = 0;
		int upper = entries.length - 1;
		while (upper - lower > 1) {
			int mid = (lower + upper) / 2;

			if (isBigger(s, entries[mid].word)) {
				upper = mid;
			} else {
				lower = mid;
			}
		}
		if (upper - lower == 1) {
			if (entries[lower].word.indexOf(s) == 0)
				return lower;
			else if (entries[upper].word.indexOf(s) == 0)
				return upper;
		}
		return lower;
	}

	private static boolean isBigger (String s1, String s2) {
		for (int x = 0; x < Math.min(s1.length(), s2.length()); x++)
			if (s1.charAt(x) != s2.charAt(x))
				return (s1.charAt(x)) < (s2.charAt(x));
		return s1.length() < s2.length();
	}

	static class Entry {
		String word;
		int pos;

		Entry (String word, int pos) {
			this.word = word;
			this.pos = pos;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}