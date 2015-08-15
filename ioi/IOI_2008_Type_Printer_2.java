package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class IOI_2008_Type_Printer_2 {
	static Scan scan = new Scan();

	public static void main (String[] args) {
		int num = scan.nextInt();
		String[] words = new String[num];
		int longest = -1;
		int index = 0;
		for (int x = 0; x < words.length; x++) {
			String s = scan.next();
			words[x] = s;
			if (words[x].length() > longest) {
				longest = words[x].length();
				index = x;
			}
		}
		String temp = words[0];
		words[0] = words[index];
		words[index] = temp;
		final String longestS = words[0];
		Arrays.sort(words, 1, words.length, new Comparator<String>() {
			@Override
			public int compare (String o1, String o2) {
				int counter1 = 0;
				int counter2 = 0;
				for (int x = 0; x < Math.min(o1.length(), o2.length()); x++) {
					if (longestS.charAt(x) == o1.charAt(x))
						counter1++;
					if (longestS.charAt(x) == o2.charAt(x))
						counter2++;
					if (longestS.charAt(x) != o1.charAt(x)
							|| longestS.charAt(x) != o2.charAt(x))
						break;
				}
				if (counter1 == counter2) {
					for (int x = 0; x < Math.min(o1.length(), o2.length()); x++) {
						if (o1.charAt(x) < o2.charAt(x))
							return 1;
						else if (o1.charAt(x) > o2.charAt(x))
							return -1;
					}
					return o2.length() - o1.length();
				}
				return counter2 - counter1;
			}
		});
		int nextIndex = 0;
		String finalS = "";
		int operations = 0;
		main : for (int x = words.length - 1; x >= 0; x--) {
			int common = 0;
			if (x == 0) {
				String ss = words[x].substring(nextIndex, words[x].length());
				finalS += ss + "P";
				operations += ss.length() + 1;

				break main;

			}
			for (int y = 0; y < words[x].length(); y++) {
				if (words[x - 1].length() > y
						&& words[x - 1].charAt(y) == words[x].charAt(y))
					common++;
				else
					break;
			}
			String ss = words[x].substring(nextIndex, words[x].length());
			finalS += ss + "P";
			operations += ss.length() + 1;
			String repeat = new String(new char[words[x].length() - common])
					.replace("\0", "-");
			finalS += repeat;
			operations += repeat.length();

			nextIndex = common;
		}
		System.out.println(operations);
		System.out.println(finalS);
	}

	public static class Scan {
		BufferedReader br;
		StringTokenizer st;

		public Scan () {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next () {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine().trim());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt () {
			return Integer.parseInt(next());
		}
	}
}
