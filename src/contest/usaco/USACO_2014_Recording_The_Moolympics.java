package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2014_Recording_The_Moolympics {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfPrograms = readInt();
		int[][] programs = new int[numOfPrograms][2];
		for (int x = 0; x < numOfPrograms; x++) {
			programs[x][0] = readInt();
			programs[x][1] = readInt();
		}
		Arrays.sort(programs, new Comparator<int[]>() {
			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[1] - arg1[1];
			}
		});
		int end1 = 0;
		int end2 = 0;
		int counter = 0;
		for (int x = 0; x < numOfPrograms; x++) {
			if (programs[x][0] >= end1 && programs[x][0] >= end2) {
				if (end1 > end2)
					end1 = programs[x][1];
				else
					end2 = programs[x][1];
				counter++;
			} else if (programs[x][0] >= end1 && programs[x][0] < end2) {
				end1 = programs[x][1];
				counter++;
			} else if (programs[x][0] < end1 && programs[x][0] >= end2) {
				end2 = programs[x][1];
				counter++;
			}
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
