package contest.noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NOI_1997_Perfect_Tour {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) {
		int r = nextInt();
		int c = nextInt() - 1;
		int max = 0;
		short[] grid = new short[c];
		for (int y = 0; y < c; y++) {
			grid[y] = -101;
		}
		for (int x = 0; x < r; x++)
			for (int y = 0; y < c; y++) {
				grid[y] = (short) Math.max(grid[y], nextInt());
			}
		main : for (int x = 0; x < c; x++) {
			int sum = 0;
			for (int y = x; y < c; y++) {
				if (grid[y] < 0 && x == y) {
					continue main;
				}
				sum += grid[y];
				if (sum > max)
					max = sum;
			}
		}
		System.out.println(max);
	}

	static String next () {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine().trim());
			} catch (IOException e) {
			}
		}
		return st.nextToken();
	}

	static int nextInt () {
		return Integer.parseInt(next());
	}

	static String nextLine () {
		String s = "";
		try {
			s = br.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
