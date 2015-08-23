package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2013_Party_Invitation {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		/*
		 * 10 4 2 1 3 2 3 4 6 1 2 3 4 6 7 4 4 3 2 1
		 */
		int n = readInt();
		ArrayList<ArrayList<Integer>> inverse = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++)
			inverse.add(new ArrayList<Integer>());
		int numGroups = readInt();

		ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < numGroups; x++) {
			groups.add(new ArrayList<Integer>());
			int numOfCows = readInt();
			for (int y = 0; y < numOfCows; y++) {
				int a = readInt() - 1;
				groups.get(x).add(a);
				inverse.get(a).add(x);
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);
		int counter = 0;
		while (!q.isEmpty()) {

			int next = q.poll();
			counter++;
			for (int x = 0; x < inverse.get(next).size(); x++) {
				int index = inverse.get(next).get(x);
				groups.get(index).remove((Integer) next);
				if (groups.get(index).size() == 1) {
					if (!q.contains(groups.get(index).get(0)))
						q.offer(groups.get(index).get(0));
				}
			}
		}
		System.out.println(counter);
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
