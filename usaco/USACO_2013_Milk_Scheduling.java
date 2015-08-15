package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2013_Milk_Scheduling {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfCows = readInt();
		PriorityQueue<Integer> maxGall = new PriorityQueue<Integer>(numOfCows,
				new Comparator<Integer>() {

					@Override
					public int compare (Integer o1, Integer o2) {
						return o2 - o1;
					}

				});
		int[][] cows = new int[numOfCows][2];
		for (int x = 0; x < numOfCows; x++) {
			int gallon = readInt();
			int time = readInt();
			cows[x][0] = gallon;
			cows[x][1] = time;
		}
		Arrays.sort(cows, new Comparator<int[]>() {

			@Override
			public int compare (int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}

		});
		int index = 0;
		int total = 0;
		for (int n = 10000; n >= 1; n--) {
			while (index < numOfCows && cows[index][1] >= n) {

				maxGall.offer(cows[index][0]);
				// System.out.println(cows[index][0] + " " + n);
				index++;

			}
			if (!maxGall.isEmpty())
				total += maxGall.poll();
		}
		System.out.println(total);
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
