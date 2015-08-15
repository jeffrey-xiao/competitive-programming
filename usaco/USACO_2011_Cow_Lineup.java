package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class USACO_2011_Cow_Lineup {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static Map<Integer, Integer> m = new HashMap<Integer, Integer>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[][] cows = new int[n][2];
		Set<Integer> i = new HashSet<Integer>();
		for (int x = 0; x < cows.length; x++) {
			cows[x][0] = readInt();
			cows[x][1] = readInt();
			i.add(cows[x][1]);
		}
		int numOfIds = i.size();
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare (int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int index1 = 0;
		int index2 = 0;
		int min = Integer.MAX_VALUE;
		while (index2 < n) {
			// System.out.println("ASD");
			while (index2 < n && m.size() < numOfIds) {
				// System.out.println(index1 + " " + index2);
				add(cows[index2][1]);
				index2++;
			}
			while (index1 < n && m.size() == numOfIds) {
				// System.out.println("DELETE "+index1 + " " + index2);
				// System.out.println("REMOVING: " + cows[index1][1] + " " +
				// m.get(cows[index1][1]));
				// System.out.println("SIZE: " + m.size());
				min = Math.min(min, cows[index2 - 1][0] - cows[index1][0]);
				remove(cows[index1][1]);

				index1++;
			}
		}
		System.out.println(min);
	}

	private static void add (int id) {
		if (m.get(id) == null)
			m.put(id, 1);
		else
			m.put(id, m.get(id) + 1);
	}

	private static void remove (int id) {
		if (m.get(id) <= 1) {
			m.remove(id);
		} else
			m.put(id, m.get(id) - 1);
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
