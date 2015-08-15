package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2013_Cow_Lineup {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static Map<Integer, Integer> lineup = null;

	public static void main (String[] args) throws IOException {
		int numOfCows = readInt();
		int maxToRemove = readInt() + 1;
		Queue<Integer> cows = new LinkedList<Integer>();
		lineup = new HashMap<Integer, Integer>();
		int max = 1;
		for (int x = 0; x < numOfCows; x++) {
			int nextCow = readInt();
			cows.offer(nextCow);
			add(nextCow);
			while (lineup.size() > maxToRemove) {
				int nextToRemove = cows.poll();
				remove(nextToRemove);
			}
			int curr = lineup.get(nextCow);
			// if(curr == 6)
			// System.out.println(nextCow);
			max = Math.max(curr, max);
		}
		System.out.println(max);
	}

	private static void remove (int nextCow) {
		if (lineup.get(nextCow) == 1)
			lineup.remove(nextCow);
		else
			lineup.put(nextCow, lineup.get(nextCow) - 1);
	}

	private static void add (int nextCow) {
		if (lineup.get(nextCow) == null)
			lineup.put(nextCow, 1);
		else
			lineup.put(nextCow, lineup.get(nextCow) + 1);

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
