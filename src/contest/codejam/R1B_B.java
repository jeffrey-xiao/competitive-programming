package contest.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class R1B_B {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		// pr = new PrintWriter(new BufferedWriter(new
		// OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("in.txt"));
		pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 1; qq <= t; qq++) {
			int r = readInt();
			int c = readInt();
			int n = readInt();
			int min = 1 << 30;
			for (int i = 0; i < 1 << (r * c); i++) {
				int cnt = 0;
				for (int j = 0; j < r * c; j++) {
					if (((1 << j) & i) != 0) {
						cnt++;
					}
				}

				if (cnt == n) {
					// System.out.println(Integer.toString(i, 2));
					int ans = 0;
					boolean[][] g = new boolean[r][c];
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							g[x][y] = ((1 << x * c + y) & i) != 0 ? true : false;
						}
					}
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							for (int z = 0; z < 4; z++) {
								int nx = x + movex[z];
								int ny = y + movey[z];
								if (nx < 0 || nx >= r || ny < 0 || ny >= c || !g[x][y])
									continue;

								// System.out.println(x + " " + y + " " + nx +
								// " " + ny);
								if (g[nx][ny])
									ans++;
							}
						}
					}
					min = Math.min(min, ans / 2);
				}
			}
			pr.printf("Case #%d: %d\n", qq, min);
		}

		pr.close();
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
