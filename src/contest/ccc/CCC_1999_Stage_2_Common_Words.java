package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CCC_1999_Stage_2_Common_Words {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int testCases = readInt(); testCases > 0; testCases--) {
			int n = readInt();
			int m = readInt();
			if (m % 100 < 20 && m % 100 > 10 || m > 1000)
				System.out.print(m + "th");
			else {
				int temp = m % 10;
				switch (temp) {
					case 1:
						System.out.print(m + "st");
						break;
					case 2:
						System.out.print(m + "nd");
						break;
					case 3:
						System.out.print(m + "rd");
						break;
					default:
						System.out.print(m + "th");
				}
			}
			System.out.println(" most common word(s):");
			ArrayList<Word> words = new ArrayList<Word>();
			for (int x = 0; x < n; x++) {
				String next = next();
				if (!words.contains(new Word(next))) {
					words.add(new Word(next));
				} else {
					words.get(words.indexOf(new Word(next))).incOcc();
				}
			}
			Collections.sort(words);
			if (m - 1 >= words.size() || (m - 2 >= 0 && words.get(m - 2).occ == m - 1)) {
				System.out.println();
				continue;
			}
			int occ = words.get(m - 1).occ;
			for (int x = m - 1; x < words.size(); x++) {

				int next = words.get(x).occ;
				if (next == occ)
					System.out.println(words.get(x).word);
				else
					break;
			}
			System.out.println();
		}
	}

	static class Word implements Comparable<Word> {
		String word;
		int occ;

		Word (String word) {
			this.word = word;
			occ = 1;
		}

		void incOcc () {
			occ++;
		}

		@Override
		public int compareTo (Word o) {
			if (o.occ == occ)
				return compare(word, o.word);
			return o.occ - occ;
		}

		private int compare (String s1, String s2) {
			for (int x = 0; x < Math.min(s1.length(), s2.length()); x++) {
				if (s1.charAt(x) < s2.charAt(x))
					return 1;
				else if (s1.charAt(x) < s2.charAt(x))
					return -1;
			}
			return s2.length() - s1.length();
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Word) {
				Word w = (Word) o;
				return word.equals(w.word);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
