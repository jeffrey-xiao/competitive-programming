package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CCC_2007_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfBoxes = readInt();
		int[][] boxes = new int[numOfBoxes][3];
		for (int x = 0; x < numOfBoxes; x++) {
			boxes[x][0] = readInt();
			boxes[x][1] = readInt();
			boxes[x][2] = readInt();
			Arrays.sort(boxes[x]);
		}
		int numOfItems = readInt();
		int[][] items = new int[numOfItems][3];
		for (int x = 0; x < numOfItems; x++) {
			items[x][0] = readInt();
			items[x][1] = readInt();
			items[x][2] = readInt();
			Arrays.sort(items[x]);
		}
		Arrays.sort(boxes, new Comparator<int[]>() {
			@Override
			public int compare (int[] o1, int[] o2) {
				return o1[0] * o1[1] * o1[2] - o2[0] * o2[1] * o2[2];
			}
		});
		main : for (int x = 0; x < numOfItems; x++) {
			for (int y = 0; y < numOfBoxes; y++) {
				if (items[x][0] <= boxes[y][0] && items[x][1] <= boxes[y][1]
						&& items[x][2] <= boxes[y][2]) {
					System.out.println(boxes[y][0] * boxes[y][1] * boxes[y][2]);
					continue main;
				}
			}
			System.out.println("Item does not fit.");
		}
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
