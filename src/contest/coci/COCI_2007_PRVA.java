package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2007_PRVA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {0, 1};
	static int[] movey = {1, 0};
	static int r;
	static int c;
	static boolean[][] visited;
	static char[][] g;
	static ArrayList<String> words = new ArrayList<String>();

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		g = new char[r + 2][c + 2];
		for (int x = 0; x < r + 2; x++)
			for (int y = 0; y < c + 2; y++)
				g[x][y] = '#';
		for (int x = 1; x <= r; x++) {
			char[] ch = (" " + next()).toCharArray();
			for (int y = 1; y <= c; y++) {
				g[x][y] = ch[y];
			}
		}
		visited = new boolean[r + 2][c + 2];

		for (int x = 0; x < r + 2; x++) {
			for (int y = 0; y < c + 2; y++) {
				if (!visited[x][y] && g[x][y] == '#') {
					visited[x][y] = true;
					dfs(x, y, -1, "");
					visited[x][y] = false;
				}
			}
		}
		Collections.sort(words, new Comparator<String>() {
			@Override
			public int compare (String o1, String o2) {
				for (int x = 0; x < Math.min(o1.length(), o2.length()); x++)
					if (o1.charAt(x) != o2.charAt(x))
						return o1.charAt(x) - o2.charAt(x);
				return o1.length() - o2.length();
			}
		});
		System.out.println(words.get(0));
	}

	private static void dfs (int x, int y, int dir, String s) {
		for (int z = 0; z < 2; z++) {
			int nx = x + movex[z];
			int ny = y + movey[z];
			if (nx < 0 || ny < 0 || nx >= r + 2 || ny >= c + 2
					|| g[nx][ny] == '#') {
				if (dir == z && s.length() > 1)
					words.add(s);
				continue;
			}
			if (visited[nx][ny] || (dir != z && dir != -1))
				continue;
			visited[nx][ny] = true;
			dfs(nx, ny, z, s + g[nx][ny]);
			visited[nx][ny] = false;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
