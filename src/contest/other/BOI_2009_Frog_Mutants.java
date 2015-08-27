package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOI_2009_Frog_Mutants {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] h = new int[n];
		int[] j = new int[n];
		for (int x = 0; x < n; x++)
			h[x] = readInt();
		for (int x = 0; x < n; x++)
			j[x] = readInt();
		ArrayList<Integer> heights = new ArrayList<Integer>();
		int[] ans = new int[n];
		for (int x = n - 1; x >= 0; x--) {
			while (heights.size() != 0 && h[x] >= heights.get(heights.size() - 1))
				heights.remove(heights.get(heights.size() - 1));
			if (heights.size() == 0 || heights.size() - j[x] < 0)
				ans[x] = -1;
			else
				ans[x] = heights.get(heights.size() - j[x]);
			heights.add(h[x]);
		}
		for (int x = 0; x < n; x++)
			System.out.print(ans[x] + " ");
	}

	static class State {
		int h, j;
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
