package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2000_Stage_2_The_Game_Of_31 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[][][][][][] win; // 1 is win, -1 is lose

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			win = new int[5][5][5][5][5][5];
			String s = readLine();
			if (s == null)
				s = "";
			int[] state = {4, 4, 4, 4, 4, 4};
			int sum = 0;
			for (int x = 0; x < s.length(); x++) {
				int next = s.charAt(x) - 49;
				state[next]--;
				sum += next + 1;
			}
			compute(Arrays.copyOf(state, state.length), sum);
			int isWin = win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]];
			int player = s.length() % 2;
			if (isWin == 1 && player == 0)
				System.out.println("A");
			else if (isWin == 1 && player == 1)
				System.out.println("B");
			else if (isWin == -1 && player == 0)
				System.out.println("B");
			else if (isWin == -1 && player == 1)
				System.out.println("A");
		}
	}

	private static void compute (int[] state, int sum) {
		for (int x = 0; x < state.length; x++) {
			if (sum + x + 1 <= 31 && state[x] > 0) {
				state[x]--;
				if (win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]] == 0)
					compute(state, sum + (x + 1));
				if (win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]] == -1) {
					state[x]++;
					win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]] = 1;
				} else
					state[x]++;
			}
		}
		if (win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]] == 1)
			return;
		win[state[0]][state[1]][state[2]][state[3]][state[4]][state[5]] = -1;
		return;
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
