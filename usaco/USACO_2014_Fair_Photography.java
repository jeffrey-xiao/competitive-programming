package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2014_Fair_Photography {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[][] cows = new int[n][2];// 1 is white, -1 is spotted
		for (int x = 0; x < n; x++) {
			int pos = readInt();
			int cow = next().equals("W") ? 1 : -1;
			cows[x][0] = pos;
			cows[x][1] = cow;
		}
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		int[] totalSum = new int[n + 1];
		for (int x = 1; x <= n; x++) {
			totalSum[x] = totalSum[x - 1] + cows[x - 1][1];
			// System.out.println(totalSum[x]);
		}
		// int lower = 0;
		// int upper = n;
		int max = 0;
		for (int mid = 2; mid <= n; mid += 2) {
			// System.out.println(mid);
			for (int x = 0, y = x + mid; y <= n; y++, x++) {
				// System.out.println(totalSum[y] + " " + totalSum[x]);
				if (totalSum[y] - totalSum[x] >= 0
						&& (totalSum[y] - totalSum[x]) % 2 == 0) {
					max = Math.max(max, cows[y - 1][0] - cows[x][0]);
				}
			}
		}

		System.out.println(max);
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
/*
 * while(upper <= n){ if(totalSum[upper]-totalSum[lower] < 0){ if(upper-lower ==
 * 1){ lower++; upper++; }else lower++; }else{
 * if((totalSum[upper]-totalSum[lower])%2 ==0){
 * 
 * max = Math.max(max,cows[upper-1][0]-cows[lower][0]);
 * //System.out.println(cows[upper-1][0] + " " + cows[lower][0]); } upper++; } }
 *//*
	 * int lower = 0; int upper = n; int max = 0; main:while(upper-lower > 1){
	 * int mid = (upper+lower)/2; if(mid%2 == 1){ upper++; lower++; mid =
	 * (upper+lower)/2; } boolean poss = false; //System.out.println(mid);
	 * for(int x = 0, y = x+mid; y <= n; y++, x++){ if(totalSum[y]-totalSum[x]
	 * >= 0 && (totalSum[y]-totalSum[x])%2 ==0){ poss = true; max =
	 * Math.max(max,cows[y-1][0]-cows[x][0]); } } if(poss){ lower = mid; }else{
	 * upper = mid; } }
	 */