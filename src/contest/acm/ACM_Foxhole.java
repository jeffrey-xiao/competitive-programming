package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Foxhole {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int r = readInt();
			int c = readInt();
			int q = readInt();
			char[][] g = new char[r + 1][c];
			for (int x = 1; x <= r; x++)
				g[x] = next().toCharArray();
			int x = 0;
			int y = 0;
			int count = 0;
			for (; q > 0; q--) {
				char command = next().charAt(0);
				if (command == 'R' && (x + 1 < c && g[y][x + 1] != 'S'))
					x = x + 1;
				else if (command == 'L' && (x - 1 >= 0 && g[y][x - 1] != 'S'))
					x = x - 1;
				else if (command == 'D' && (y + 1 <= r && g[y + 1][x] != 'S'))
					y = y + 1;
				if (g[y][x] == 'T')
					count++;
				g[y][x] = 'E';
				while (y + 1 <= r && g[y + 1][x] == 'E')
					y++;
			}
			System.out.println(count);
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
