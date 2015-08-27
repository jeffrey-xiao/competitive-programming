package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CCC_2014_S5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = nextInt();
		int[][] points = new int[n + 1][];
		points[0] = new int[] {0, 0};
		int counter = 0;
		int[][] pairs = new int[n * (n + 1) / 2][];
		for (int x = 1; x < n + 1; x++) {
			if (x != 0)
				points[x] = new int[] {nextInt(), nextInt()};
			for (int y = x - 1; y >= 0; y--) {
				int x1 = points[x][0] - points[y][0];
				int y1 = points[x][1] - points[y][1];
				pairs[counter] = new int[] {y, x, x1 * x1 + y1 * y1};
				counter++;
			}
		}

		// for(int x = 0; x < n+1; x++){
		// for(int y = x+1; y < n+1; y++){
		// int x1 = points[x][0]-points[y][0];
		// int y1 = points[x][1]-points[y][1];
		// pairs[counter] = new int[]{x,y,x1*x1+y1*y1};
		// counter++;
		// }
		// }
		Arrays.sort(pairs, new Comparator<int[]>() {
			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[2] - arg1[2];
			}
		});
		/*
		 * for(int[] x:pairs){ System.out.println(x[0] + " " + x[1] + " " +
		 * x[2]); }
		 */
		int[] bestDist = new int[n + 1];
		int[] prevbestNum = new int[n + 1];
		int[] bestNum = new int[n + 1];
		for (int x = 0; x < pairs.length; x++) {
			int[] next = pairs[x];
			if (next[2] > bestDist[next[0]]) {
				bestDist[next[0]] = next[2];
				prevbestNum[next[0]] = bestNum[next[0]];
			}
			if (next[2] > bestDist[next[1]]) {
				bestDist[next[1]] = next[2];
				prevbestNum[next[1]] = bestNum[next[1]];
			}
			// adding necessary previous row memorization in order to calc curr
			// row
			if (next[0] == 0) {

				bestNum[next[0]] = Math.max(bestNum[next[0]], prevbestNum[next[1]]);
			} else {

				bestNum[next[0]] = Math.max(bestNum[next[0]], prevbestNum[next[1]] + 1);
				bestNum[next[1]] = Math.max(bestNum[next[1]], prevbestNum[next[0]] + 1);
			}
			/*
			 * for(int y = 0; y < n+1; y++){ System.out.print(bestNum[y] + ":" +
			 * bestDist[y] + " "); } System.out.println();
			 */
		}
		System.out.println(bestNum[0] + 1);
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());

		return st.nextToken();
	}

	static int nextInt () throws IOException {
		return Integer.parseInt(next());
	}

	static String nextLine () throws IOException {
		return br.readLine().trim();
	}
}
