package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R2_P3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 10;
	
	static int[] mx = {-1,-1,-1,0,0,1,1,1};
	static int[] my = {-1,0,1,-1,1,-1,0,1};
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));

		// br = new BufferedReader(new FileReader("in.txt"));
		// br = new BufferedReader(new FileReader("DATA31.txt"));
		
		for (int t = 1; t <= TEST_CASES; t++) {
			int N = readInt();
			int sz = readInt();
			char[][] g = new char[N][N];
			for (int i = 0; i < N; i++)
				g[i] = readLine().toCharArray();
			
			int cnt = 0;
			
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					// rows
					if (j + sz - 1 < N) {
						boolean valid = true;
						for (int k = j; k < j + sz; k++) {
							if (g[i][k] == 'm')
								valid = false;
							for (int l = 0; l < 8; l++) {
								int nx = i + mx[l];
								int ny = k + my[l];
								if (nx < 0 || ny < 0 || nx >= N || ny >= N)
									continue;
								if (nx == i && (j <= ny && ny <= j + sz - 1))
									continue;
								if (g[nx][ny] == 'h')
									valid = false;
							}
						}
						if (valid) {
							cnt++;
						}
					}
					// cols
					if (i + sz - 1 < N) {
						boolean valid = true;
						for (int k = i; k < i + sz; k++) {
							if (g[k][j] == 'm') {
								valid = false;
							}
							for (int l = 0; l < 8; l++) {
								int nx = k + mx[l];
								int ny = j + my[l];
								if (nx < 0 || ny < 0 || nx >= N || ny >= N)
									continue;
								if (i <= nx && nx <= i + sz - 1 && (j == ny))
									continue;
								if (g[nx][ny] == 'h') {
									valid = false;
								}
							}
						}
						if (valid) {
							cnt++;
						}
					}
				}
			out.println(cnt);
		}
		
		out.close();
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

