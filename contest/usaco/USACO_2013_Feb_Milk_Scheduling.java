package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2013_Feb_Milk_Scheduling {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] cows;
	static int[] totalTime;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> depend = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		cows = new int[n];
		totalTime = new int[n];
		visited = new boolean[n];
		for (int x = 0; x < n; x++) {
			cows[x] = readInt();
			depend.add(new ArrayList<Integer>());
		}
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			depend.get(b).add(a);
		}
		int max = 0;
		for (int x = 0; x < n; x++) {
			if (!visited[x]) {
				max = Math.max(max, compute(x));
			}
		}
		System.out.println(max);
	}

	private static int compute (int x) {
		if (visited[x])
			return totalTime[x];

		int total = cows[x];
		int max = 0;
		for (int z = 0; z < depend.get(x).size(); z++) {
			max = Math.max(max, compute(depend.get(x).get(z)));
		}
		total += max;
		visited[x] = true;
		totalTime[x] = total;
		return total;
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
