package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class IOI_1996_Job_Processing {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int tasks = readInt();
		int numOfA = readInt();
		PriorityQueue<int[]> moves = new PriorityQueue<int[]>(numOfA,
				new Comparator<int[]>() {

					@Override
					public int compare (int[] arg0, int[] arg1) {
						return arg0[0] + arg0[1] - arg1[0] - arg1[1];
					}

				});
		for (int x = 0; x < numOfA; x++) {
			int a = readInt();
			moves.offer(new int[] {0, a});
		}
		ArrayList<Integer> finishTimes = new ArrayList<Integer>();
		int max = 0;
		for (int x = 0; x < tasks; x++) {
			int[] curr = moves.poll();
			max = Math.max(curr[0] + curr[1], max);
			finishTimes.add(curr[0] + curr[1]);
			moves.offer(new int[] {curr[0] + curr[1], curr[1]});
		}

		// GREEDILY ASSIGN B

		int numOfB = readInt();
		moves = new PriorityQueue<int[]>(numOfA, new Comparator<int[]>() {

			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[0] + arg0[1] - arg1[0] - arg1[1];
			}

		});
		for (int x = 0; x < numOfB; x++) {
			int b = readInt();
			moves.offer(new int[] {0, b});
		}
		ArrayList<Integer> finishTimesB = new ArrayList<Integer>();
		int maxB = 0;
		for (int x = 0; x < tasks; x++) {
			int[] curr = moves.poll();
			maxB = Math.max(curr[0] + curr[1], maxB);
			finishTimesB.add(curr[0] + curr[1]);
			// System.out.println(curr[0] + curr[1]);
			moves.offer(new int[] {curr[0] + curr[1], curr[1]});
		}
		Collections.reverse(finishTimesB);

		for (int x = 0; x < tasks; x++) {
			maxB = Math.max(finishTimesB.get(x) + finishTimes.get(x), maxB);
		}
		System.out.println(max + "\n" + maxB);
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
