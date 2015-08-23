package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2003_Balancing_Act {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static boolean[] visited = new boolean[0];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();

		for (int x = 0; x < n; x++)
			nodes.add(new ArrayList<Integer>());
		for (int x = 0; x < n - 1; x++) {

			int a = readInt() - 1;
			int b = readInt() - 1;
			nodes.get(a).add(b);
			nodes.get(b).add(a);
		}
		/*
		 * for(int x = 0; x < nodes.size(); x++){ System.out.print(x+1 + " : ");
		 * for(int y = 0; y < nodes.get(x).size(); y++)
		 * System.out.print(nodes.get(x).get(y)+1 + " "); System.out.println();
		 * }
		 */
		int length = Integer.MAX_VALUE;
		int index = 0;
		for (int y = 0; y < n; y++) {
			visited = new boolean[n];
			int exLength = -1;
			int exIndex = 0;
			for (int x = 0; x < n; x++) {
				if (x != y) {
					int newLength = getLength(nodes, x, y, 0);
					if (newLength > exLength) {
						exLength = newLength;
						exIndex = y;
					}
					// System.out.println((y+1) + " " + (x+1) + " " +
					// newLength);
				}
			}
			if (exLength < length) {
				index = exIndex;
				length = exLength;
			}
		}
		System.out.println((index + 1) + " " + (length + 1));
	}

	private static int getLength (ArrayList<ArrayList<Integer>> nodes, int curr,
			int ex, int num) {
		// System.out.println("IN DFS " + (curr+1) + " " + (ex+1) + " ");
		if (visited[curr] || curr == ex)
			return num - 1;
		visited[curr] = true;
		for (int z : nodes.get(curr))
			num = getLength(nodes, z, ex, num + 1);
		return num;
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
