package smac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SMAC_2008_Befungulator {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		boolean[][][] v = new boolean[r][c][4];
		char[][] g = new char[r][];
		for (int x = 0; x < r; x++)
			g[x] = readLine().toCharArray();
		int direction = 1;
		int[] state = new int[2];
		char operator = '\u0000';
		int result = 0;
		while (g[state[0]][state[1]] != 'X') {
			int x = state[0];
			int y = state[1];

			if (v[x][y][direction]) {
				System.out.println("Infinite Loop");
				return;
			}
			v[x][y][direction] = true;
			if (g[x][y] == '>')
				direction = 1;
			else if (g[x][y] == 'v')
				direction = 2;
			else if (g[x][y] == '<')
				direction = 3;
			else if (g[x][y] == '^')
				direction = 0;
			else if (g[x][y] == '+')
				operator = '+';
			else if (g[x][y] == '-')
				operator = '-';
			else if (g[x][y] == '*')
				operator = '*';
			else if (g[x][y] == '/')
				operator = '/';
			else if (g[x][y] == '%')
				operator = '%';
			else if (g[x][y] - '0' >= 0 && g[x][y] - '0' <= 9) {
				int n = g[x][y] - '0';
				if (operator == '\u0000')
					result = n;
				else if (operator == '/' && n == 0) {
					System.out.println("Zero Division Error");
					return;
				} else if (operator == '%' && n == 0) {
					System.out.println("Invalid Modulo");
					return;
				} else if (operator == '+') {
					result += n;
				} else if (operator == '-') {
					result -= n;
				} else if (operator == '/') {
					result /= n;
				} else if (operator == '*') {
					result *= n;
				} else if (operator == '%') {
					result %= n;
				}
			}
			nextState(state, direction);
			if (state[0] < 0 || state[0] >= r || state[1] < 0 || state[1] >= c) {
				System.out.println("Invalid Direction");
				return;
			}
		}
		System.out.println(result);
	}

	private static void nextState (int[] state, int d) {
		switch (d) {
			case 0:
				state[0]--;
				break;
			case 1:
				state[1]++;
				break;
			case 2:
				state[0]++;
				break;
			case 3:
				state[1]--;
				break;
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
