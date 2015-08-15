package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2014_Mooo_Moo {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfFields = readInt();
		int numOfCows = readInt();

		int[] cows = new int[numOfCows + 1];
		for (int x = 1; x <= numOfCows; x++)
			cows[x] = readInt();

		int counter = 0;
		int fieldVolume = 0;
		int prevVolume = 0;
		ArrayList<Integer> volumes = new ArrayList<Integer>();
		int largest = 0;
		for (int x = 0; x < numOfFields; x++) {
			int curr = readInt();
			fieldVolume = curr - ((prevVolume - 1) < 0 ? 0 : (prevVolume - 1));
			prevVolume = curr;
			volumes.add(fieldVolume);
			largest = Math.max(largest, fieldVolume);
			// System.out.println(fieldVolume);
		}
		int[] fieldSolutions = solve(largest, cows);
		for (Integer i : volumes)
			counter += fieldSolutions[i];
		System.out.println(counter);
	}

	private static int[] solve (int fieldVolume, int[] cows) {

		// System.out.println("ENTERED " + fieldVolume);
		int[] field = new int[fieldVolume + 1];
		for (int x = 1; x < field.length; x++)
			field[x] = Integer.MAX_VALUE - 50000;
		for (int x = 1; x < cows.length; x++) {
			for (int y = 0; y + cows[x] <= fieldVolume; y++) {
				field[y + cows[x]] = Math.min(field[y + cows[x]], field[y] + 1);
			}
			/*
			 * for(int a: field) System.out.print(a + " ");
			 * System.out.println();
			 */

		}
		return field;
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
