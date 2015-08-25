package contest.yandex_warm_up_2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static int[] movex = {0, 0, 0, 0, 1, -1};
	static int[] movey = {0, 0, -1, 1, 0, 0};
	static int[] movez = {-1, 1, 0, 0, 0, 0};
	static boolean[][][] v;
	static char[][][] cube;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int tt = readInt();
		for (int qq = 1; qq <= tt; qq++) {
			int n = readInt();
			cube = new char[n][n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cube[i][j] = next().toCharArray();
				}
			}
			v = new boolean[n][n][n];
			int count = 0;
			HashSet<Character> hs = new HashSet<Character>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						hs.add(cube[i][j][k]);
						if (!v[i][j][k]) {
							dfs(i, j, k, n);
							count++;
						}
						// System.out.print(cube[i][j][k]);
					}
					// System.out.println();
				}
				// System.out.println();
			}
			pr.println(count == hs.size() ? "Yes" : "No");
		}

		pr.close();
	}

	private static void dfs (int i, int j, int k, int n) {
		v[i][j][k] = true;
		for (int z = 0; z < 6; z++) {
			int ni = movex[z] + i;
			int nj = movey[z] + j;
			int nk = movez[z] + k;
			if (ni < 0 || nj < 0 || nk < 0 || ni >= n || nj >= n || nk >= n
					|| v[ni][nj][nk] || cube[i][j][k] != cube[ni][nj][nk])
				continue;
			dfs(ni, nj, nk, n);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
