package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2012_Gifts {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfGifts = readInt();
		int coins = readInt();
		int[][] gifts = new int[numOfGifts][2];
		for (int x = 0; x < numOfGifts; x++) {
			gifts[x][0] = readInt();
			gifts[x][1] = readInt();
		}
		Arrays.sort(gifts, new Comparator<int[]>() {
			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[0] + arg0[1] - arg1[0] - arg1[1];
			}
		});
		int counter = 0;
		int x = 0;
		int max = 0;
		for (; x < numOfGifts && gifts[x][0] + gifts[x][1] <= coins; x++) {
			coins = coins - gifts[x][0] - gifts[x][1];
			max = Math.max(max, gifts[x][0]);
			counter++;
		}
		coins += max / 2;
		for (; x < numOfGifts && gifts[x][0] + gifts[x][1] <= coins; x++) {
			coins = coins - gifts[x][0] - gifts[x][1];
			max = Math.max(max, gifts[x][0]);
			counter++;
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
