package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_Reordering_The_Cows {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[] visited = new boolean[0];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		visited = new boolean[n];
		int[] start = new int[n];
		int[] end = new int[n];
		int[] endLoc = new int[n];
		for (int x = 0; x < n; x++)
			start[x] = readInt();
		for (int x = 0; x < n; x++) {
			end[x] = readInt();
			endLoc[end[x] - 1] = x;
		}
		int max = 0;
		int count = 0;
		for (int x = 0; x < n; x++) {
			if (start[x] != end[x] && !visited[x]) {
				max = Math.max(getCycle(x, endLoc, start), max);
				count++;
			}
		}
		System.out.println(count + " " + (max == 0 ? -1 : max));
	}

	public static int getCycle (int start, int[] endLoc, int[] startLoc) {
		int count = 0;
		int curr = start;
		while (start != curr || count == 0) {
			visited[curr] = true;
			curr = endLoc[startLoc[curr] - 1];
			count++;
		}
		return count;
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
