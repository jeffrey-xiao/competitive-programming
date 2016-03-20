package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2006_Stage_2_Paint_By_Numbers {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static ArrayList<ArrayList<Integer>> rows, cols;
	static int[] grid;
	static boolean swap = false;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		grid = new int[N];

		rows = new ArrayList<ArrayList<Integer>>();
		cols = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < N; i++) {
			rows.add(new ArrayList<Integer>());
			int val;
			while ((val = readInt()) != 0)
				rows.get(i).add(val);
		}
		for (int i = 0; i < M; i++) {
			cols.add(new ArrayList<Integer>());
			int val;
			while ((val = readInt()) != 0)
				cols.get(i).add(val);
		}

		if (M > N)
			swap();

		solve(0, new int[M], new int[M]);
		if (swap) {
			for (int j = 0; j < M; j++) {
				for (int i = 0; i < N; i++) {
					if ((grid[i] & 1 << j) > 0) {
						out.print("*");
					} else {
						out.print(".");
					}
				}
				out.println();
			}
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if ((grid[i] & 1 << j) > 0) {
						out.print("*");
					} else {
						out.print(".");
					}
				}
				out.println();
			}
		}
		out.close();
	}

	static void swap () {
		int temp1 = N;
		N = M;
		M = temp1;

		ArrayList<ArrayList<Integer>> temp2 = rows;
		rows = cols;
		cols = temp2;

		grid = new int[N];
		swap = true;
	}

	static boolean solve (int i, int[] colCnt, int[] colIndex) {
		if (i == N) {
			int[] currColCnt = Arrays.copyOf(colCnt, M);
			int[] currColIndex = Arrays.copyOf(colIndex, M);
			for (int j = 0; j < M; j++) {
				if (currColCnt[j] > 0) {
					if (currColIndex[j] == cols.get(j).size() || currColCnt[j] != cols.get(j).get(currColIndex[j])) {
						return false;
					} else {
						currColIndex[j]++;
						currColCnt[j] = 0;
					}
				}
				if (currColIndex[j] != cols.get(j).size())
					return false;
			}
			return true;
		}

		for (int j = 0; j < 1 << M; j++) {
			int index = 0;
			int size = 0;
			boolean valid = true;
			int[] currColCnt = Arrays.copyOf(colCnt, M);
			int[] currColIndex = Arrays.copyOf(colIndex, M);

			for (int k = 0; k < M; k++) {
				if ((j & 1 << k) > 0) {
					size++;
					currColCnt[k]++;
				} else {
					// end of col group
					if (currColCnt[k] != 0) {
						if (currColIndex[k] == cols.get(k).size() || currColCnt[k] != cols.get(k).get(currColIndex[k])) {
							valid = false;
						} else {
							currColIndex[k]++;
							currColCnt[k] = 0;
						}
					}

					// end of row group
					if (size != 0) {
						if (index == rows.get(i).size() || size != rows.get(i).get(index)) {
							valid = false;
						} else {
							index++;
							size = 0;
						}
					}
				}
			}
			// end of row group
			if (size != 0) {
				if (index == rows.get(i).size() || size != rows.get(i).get(index)) {
					valid = false;
				} else {
					index++;
					size = 0;
				}
			}
			if (index != rows.get(i).size())
				valid = false;

			grid[i] = j;
			if (valid && solve(i + 1, currColCnt, currColIndex)) {
				return true;
			}
		}
		return false;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
