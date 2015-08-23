package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1999_P2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 31};

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int x = 0; x < n; x++) {
			StringBuilder s = new StringBuilder(readLine());
			int index = 0;
			while (s.indexOf("/", index) != -1 && s.indexOf(".", index) != -1
					&& s.indexOf(",", index) != -1) {
				if (s.indexOf("/", index) != -1) {
					index = s.indexOf("/", index);
					System.out.println(index);
					if (index >= 3 && index <= s.length() - 6
							&& s.charAt(index - 1) == ' '
							&& s.charAt(index + 5) == ' ') {
						if (s.charAt(index + 3) == '/') {
							String month = s.substring(index - 2, index);
							String day = s.substring(index + 1, index + 3);
							String year = s.substring(index + 4, index + 6);
							if (days[Integer.parseInt(month) - 1] >= Integer
									.parseInt(day))
								s.insert(index + 4,
										Integer.parseInt(year) <= 24 ? "20"
												: "19");
						}
						System.out.println(s);
					}
					index += 8;
					System.out.println(index);
				} else if (s.indexOf(".", index) != -1) {
					index = s.indexOf(".", index);
					System.out.println(index);
					if (index >= 3 && index <= s.length() - 6
							&& s.charAt(index - 1) == ' '
							&& s.charAt(index + 5) == ' ') {
						if (s.charAt(index + 3) == '.') {
							String year = s.substring(index - 2, index);
							String month = s.substring(index + 1, index + 3);
							String day = s.substring(index + 4, index + 6);
							if (days[Integer.parseInt(month) - 1] >= Integer
									.parseInt(day))
								s.insert(index - 1,
										Integer.parseInt(year) <= 24 ? "20"
												: "19");
						}
					}
					index += 8;
					System.out.println(index);
				}
			}
			System.out.println(s);
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
