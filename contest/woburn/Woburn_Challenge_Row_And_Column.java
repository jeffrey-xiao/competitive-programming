package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Woburn_Challenge_Row_And_Column {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			int r = readInt();
			int c = readInt();
			char[][] grid = new char[r][c];
			String s = next();
			int x = 0;
			int y = 0;
			int index = 0;
			while (y < c) {
				int count = 0;
				if (s.charAt(index) == '1' && s.charAt(index + 1) == '0') {
					count = 10;
					index += 2;
				} else if (s.charAt(index) - '0' <= 9) {
					count = s.charAt(index) - '0';
					index++;
				} else
					count = 1;
				// System.out.println(count);
				for (; count > 0; count--, x++) {
					grid[x][y] = s.charAt(index);
				}
				index++;
				if (x >= r) {
					x = 0;
					y++;
				}
			}
			// for(char[] ca: grid){
			// for(char cc: ca){
			// System.out.print(cc);
			// }
			// System.out.println();
			// }
			String result = "";
			for (x = 0; x < r; x++) {
				char curr = ' ';
				int count = 1;
				for (y = 0; y < c; y++) {
					if (grid[x][y] != curr || curr == ' ') {
						// System.out.println(count + " " + curr);
						result += (count == 1 ? "" : "" + count)
								+ (curr == ' ' ? "" : curr);
						curr = grid[x][y];
						count = 1;
					} else {
						count++;
					}
				}
				result += (count == 1 ? "" : "" + count) + curr;
			}
			System.out.println(result);
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
