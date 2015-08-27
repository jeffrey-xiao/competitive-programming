package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2009_POI {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int con = readInt();
		int tasks = readInt();
		int pID = readInt() - 1;
		int[] points = new int[tasks];
		boolean[][] cons = new boolean[con][tasks];
		int[][] conScore = new int[con][tasks];// 0 is score, 1 is numOfProblems
												// solved
		for (int x = 0; x < con; x++) {
			for (int y = 0; y < tasks; y++) {
				cons[x][y] = readInt() == 1 ? true : false;
				conScore[x][1] += cons[x][y] ? 1 : 0;
				points[y] += cons[x][y] ? 0 : 1;
			}
		}
		int place = 1;
		for (int x = 0; x < con; x++) {
			for (int y = 0; y < tasks; y++) {
				if (cons[x][y])
					conScore[x][0] += points[y];
			}
		}
		for (int x = 0; x < con; x++) {
			if (x == pID)
				continue;
			if (conScore[x][0] > conScore[pID][0])
				place++;
			else if (conScore[x][0] == conScore[pID][0]) {
				if (conScore[x][1] > conScore[pID][1])
					place++;
				else if (conScore[x][1] == conScore[pID][1] && x < pID)
					place++;
			}
		}
		System.out.println(conScore[pID][0] + " " + place);
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
}
