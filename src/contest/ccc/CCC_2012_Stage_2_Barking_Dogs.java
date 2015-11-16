package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2012_Stage_2_Barking_Dogs {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] wait = new int[n];
		int[] coolDown = new int[n];
		int[] totalTime = new int[n];
		boolean[] justChanged;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++) {
			wait[x] = readInt();
			adj.add(new ArrayList<Integer>());
			coolDown[x] = Integer.MAX_VALUE;
		}
		coolDown[0] = 0;
		int k = readInt();
		for (int x = 0; x < k; x++)
			adj.get(readInt() - 1).add(readInt() - 1);
		int t = readInt();
		for (int x = 0; x <= t; x++) {
			justChanged = new boolean[n];
			for (int y = 0; y < n; y++) {
				if (coolDown[y] == 0 && !justChanged[y]) {
					for (Integer i : adj.get(y)) {
						if (coolDown[i] >= 20000) {
							coolDown[i] = wait[i];
							justChanged[i] = true;
						}
					}
					totalTime[y]++;
				}
			}
			for (int y = 0; y < n; y++) {
				coolDown[y]--;
				if (coolDown[y] < 0)
					coolDown[y] = Integer.MAX_VALUE;
			}
		}
		for (int x = 0; x < n; x++)
			System.out.println(totalTime[x]);
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
