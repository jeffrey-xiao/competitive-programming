package contest.bloomberg;

import java.util.*;
import java.io.*;

public class Bloomberg_Codecon_2017_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int[][] places;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		places = new int[N][];
		
		for (int i = 0; i < N; i++) {
			int K = readInt();
			places[i] = new int[K];
			for (int j = 0; j < K; j++)
				places[i][j] = readInt();
		}
		
		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			out.println(compute(a, b));
		}
		out.close();
	}

	static int compute (int a, int b) {
		int len1 = places[a].length;
		int len2 = places[b].length;
		int currA = places[a][0];
		int currB = places[b][0];
		int i = 0;
		int j = 0;
		int ans = 0;
		while (i < len1 - 1 && j < len2 - 1) {
			int timeDiff1 = Math.abs(currA - places[a][i + 1]);
			int timeDiff2 = Math.abs(currB - places[b][j + 1]);
			if (timeDiff1 < timeDiff2) {
				i++;
				boolean before = currA < currB;
				currA = places[a][i];
				currB = currB < places[b][j + 1] ? currB + timeDiff1 : currB - timeDiff1;
				boolean after = currA < currB;
				if (before != after) ans++;
			} else if (timeDiff1 > timeDiff2) {
				j++;
				boolean before = currA < currB;
				currA = currA < places[a][i + 1] ? currA + timeDiff2 : currA - timeDiff2;
				currB = places[b][j];
				boolean after = currA < currB;
				if (before != after) ans++;
			} else {
				i++; j++;
				boolean before = currA < currB;
				currA = places[a][i];
				currB = places[b][j];
				boolean after = currA < currB;
				if (before != after) ans++;
			}
		}
		return ans;
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

