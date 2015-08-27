package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Glenforest_Pursuit_Of_Knowledge {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int t = readInt();
		int[][] time = new int[n][n];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			for (int j = 0; j < n; j++) {
				time[i][j] = 1 << 30;
			}
		}
		for (int i = 0; i < m; i++)
			adj.get(readInt() - 1).add(readInt() - 1);
		for (int i = 0; i < n; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(i);
			time[i][i] = 0;
			while (!q.isEmpty()) {
				int curr = q.poll();
				for (Integer next : adj.get(curr)) {
					if (time[i][next] <= time[i][curr] + 1)
						continue;
					time[i][next] = time[i][curr] + 1;
					q.offer(next);
				}
			}
		}
		int q = readInt();
		for (int i = 0; i < q; i++) {
			int res = time[readInt() - 1][readInt() - 1];
			System.out.println(res == 1 << 30 ? "Not enough hallways!" : res * t);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
