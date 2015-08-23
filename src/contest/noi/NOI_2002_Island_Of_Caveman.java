package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NOI_2002_Island_Of_Caveman {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	// (S1 + Ax) mod m = (S2 + By) mod m
	// ((S1 + Ax) - (S2 + By)) mod m = 0
	// (S1 - S2 + Ax - By) mod m = 0;
	// Ax - By = m*n - (S1 - S2)

	//

	static int n;
	static int[] start;
	static int[] move;
	static int[] life;

	@SuppressWarnings ("unused")
	public static void main (String[] args) throws IOException {
		n = readInt();
		int max = 0;
		start = new int[n];
		move = new int[n];
		life = new int[n];
		for (int x = 0; x < n; x++) {
			start[x] = readInt() - 1;
			max = Math.max(max, start[x]);
			move[x] = readInt();
			life[x] = readInt();
		}
		max++;
		for (int x = 0; x < n; x++) {
			for (int y = x + 1; y < n; y++) {
				for (int m = max; m <= 1000000; m++) {
					extended(move[x], -move[y]);
					State s1 = new State(x1, y1, d);
					System.out.println(x1 + " " + y1 + " " + d + " " + move[x]
							+ " " + move[y]);
					// form in Z(n1) - m*n2 = k
					int k = -(move[x] - move[y]);

					extended(d, -m);
					System.out.println(k + " " + x1 + " " + y1 + " " + s1.d
							+ " " + m + " " + d);
					int mult = Math.abs(k * d) / gcf(k, d);
					System.out.println(mult);
					int moveX = mult * x1;
					int moveY = mult * y1;
					System.out.println(moveX + " " + moveY);
					if (moveY == 0 || moveX == 0) {
						int lcm = lcm(d, -m);
						moveX += lcm / d;
						moveY += lcm / m;
					}
					System.out.println(moveX + " " + moveY);
					int finalX = moveX * s1.x;
					int finalY = moveX * s1.y;
					System.out.println(finalX + " " + finalY + " " + max);
					return;
				}
			}
		}
	}

	static class State {
		int x, y, d;

		State (int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int x1 = 0, y1 = 0, d = 0;

	public static int gcf (int a, int b) {
		return b == 0 ? a : gcf(b, a % b);
	}

	public static int lcm (int a, int b) {
		return a * b / gcf(a, b);
	}

	public static void extended (int a, int b) {
		if (b == 0) {
			x1 = 1;
			y1 = 0;
			d = a;
			return;
		}
		extended(b, a % b);
		int x = y1;
		int y = x1 - (a / b) * y1;
		x1 = x;
		y1 = y;

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
