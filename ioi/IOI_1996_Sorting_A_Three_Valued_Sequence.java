package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_1996_Sorting_A_Three_Valued_Sequence {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int num = readInt();
		int[] seq = new int[num];
		int numOfOne = 0;
		int numOfTwo = 0;
		for (int x = 0; x < num; x++) {
			seq[x] = readInt();
			if (seq[x] == 1)
				numOfOne++;
			else if (seq[x] == 2)
				numOfTwo++;
		}
		// System.out.println(numOfOne + " " + numOfTwo);
		int indexOne = 0;
		int count = 0;
		ArrayList<String> output = new ArrayList<String>();
		main : for (int x = num - 1; numOfOne > 0 && x > indexOne; x--) {
			if (seq[x] == 1) {
				for (int y = 0; y < numOfOne; y++) {
					if (seq[y] == 3) {
						seq[x] = seq[x] ^ seq[y];
						seq[y] = seq[y] ^ seq[x];
						seq[x] = seq[x] ^ seq[y];
						indexOne = y + 1;
						count++;
						output.add((y + 1) + " " + (x + 1));
						continue main;
					}
				}
				for (int y = 0; y < numOfOne; y++) {
					if (seq[y] == 2) {
						seq[x] = seq[x] ^ seq[y];
						seq[y] = seq[y] ^ seq[x];
						seq[x] = seq[x] ^ seq[y];
						indexOne = y + 1;
						count++;
						output.add((y + 1) + " " + (x + 1));
						continue main;
					}
				}
			}
		}
		/*
		 * System.out.println(); for(int x: seq) System.out.print(x + " ");
		 * System.out.println();
		 */
		int indexTwo = indexOne;
		for (int x = num - 1; numOfTwo > 0 && x > indexTwo; x--) {
			if (seq[x] == 2) {
				for (int y = indexTwo; y < x; y++) {
					if (seq[y] == 3) {
						seq[x] = seq[x] ^ seq[y];
						seq[y] = seq[y] ^ seq[x];
						seq[x] = seq[x] ^ seq[y];
						indexTwo = y + 1;
						count++;
						output.add((y + 1) + " " + (x + 1));
						numOfTwo--;
						break;
					}
				}
			}
		}
		System.out.println(count);
		for (String s : output)
			System.out.println(s);
		/*
		 * System.out.println(); for(int x: seq) System.out.print(x + " ");
		 */
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
