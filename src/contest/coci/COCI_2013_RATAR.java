package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2013_RATAR {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[][] val;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		val = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				val[i][j] = readInt() + val[i - 1][j] + val[i][j - 1] - val[i - 1][j - 1];
		
		int ans = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// top left and bottom right
				HashMap<Integer, Integer> occ = new HashMap<Integer, Integer>();
				for (int k = 1; k <= i; k++) {
					for (int l = 1; l <= j; l++) {
						int currSum = getSum(k, l, i, j);
						if (!occ.containsKey(currSum))
							occ.put(currSum, 0);
						occ.put(currSum, occ.get(currSum) + 1);
					}
				}
				
				for (int k = i + 1; k <= N; k++) {
					for (int l = j + 1; l <= N; l++) {
						if (occ.containsKey(getSum(i + 1, j + 1, k, l)))
							ans += occ.get(getSum(i + 1, j + 1, k, l));
					}
				}
				// top right and bottom left
				occ.clear();
				
				for (int k = i; k <= N; k++) {
					for (int l = 1; l <= j; l++) {
						int currSum = getSum(i, l, k, j);
						if (!occ.containsKey(currSum))
							occ.put(currSum, 0);
						occ.put(currSum, occ.get(currSum) + 1);
					}
				}
				
				for (int k = 1; k < i; k++) {
					for (int l = j + 1; l <= N; l++) {
						if (occ.containsKey(getSum(k, j + 1, i - 1, l)))
							ans += occ.get(getSum(k, j + 1, i - 1, l));
					}
				}
			}
		}
		out.println(ans);
		out.close();
	}

	static int getSum (int x1, int y1, int x2, int y2) {
		assert(x1 <= x2 && y1 <= y2);
		x1--;
		y1--;
		return val[x2][y2] - val[x1][y2] - val[x2][y1] + val[x1][y1];
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

