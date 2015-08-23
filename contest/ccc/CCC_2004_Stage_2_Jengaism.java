package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2004_Stage_2_Jengaism {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		boolean[][] blocks = new boolean[60][3];
		int top = 18;
		for (int x = 1; x <= 18; x++)
			for (int y = 0; y < 3; y++)
				blocks[x][y] = true;
		for (int x = 0; x < n; x++) {
			// remove
			String s = next();
			int l = s.length();
			int row = Integer.parseInt(s.substring(0, l - 1));
			int col = s.charAt(l - 1) - 'A';
			top = Math.max(row, top);
			blocks[row][col] = false;
			if (row != top
					&& ((!blocks[row][0] && !blocks[row][1]) || (!blocks[row][1] && !blocks[row][2]))) {
				System.out.println("The tower collapses after removing " + s);
				return;
			}
			// add
			s = next();
			l = s.length();
			row = Integer.parseInt(s.substring(0, l - 1));
			top = Math.max(row, top);
			col = s.charAt(s.length() - 1) - 'A';
			blocks[row][col] = true;
			if (row != top
					&& ((!blocks[row][0] && !blocks[row][1]) || (!blocks[row][1] && !blocks[row][2]))) {
				System.out.println("The tower collapses after placing " + s);
				return;
			}
		}
		System.out.println("The tower never collapses");
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
