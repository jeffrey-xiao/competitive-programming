package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1998_C {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};
	static int[] curr;
	static byte dir;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			dir = 0;
			int x = 0;
			int y = 0;
			int command = readInt();
			while (command != 0) {
				if (command == 1) {
					int moves = readInt();
					x += movex[dir] * moves;
					y += movey[dir] * moves;
				} else if (command == 2)
					dir = (byte) ((dir + 5) % 4);
				else
					dir = (byte) ((dir + 3) % 4);
				command = readInt();
			}
			System.out.println("Distance is " + (Math.abs(x) + Math.abs(y)));
			curr = new int[] {x, y};
			while (curr[0] != 0 || curr[1] != 0)
				move();
		}
	}

	// right is 2, left is 3
	private static void move () {
		int x = curr[0];
		int y = curr[1];
		// System.out.println(x + " " + y);
		if (x < 0 && y < 0) {
			if (dir == 0) {
				System.out.println("2\n1\n" + Math.abs(y));
				dir = 1;
				y = 0;
			} else if (dir == 1) {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 2) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else {
				System.out.println("3\n1\n" + Math.abs(x));
				dir = 2;
				x = 0;
			}
		} else if (x > 0 && y < 0) {
			if (dir == 0) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 1) {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 2) {
				System.out.println("3\n1\n" + Math.abs(y));
				dir = 1;
				y = 0;
			} else {
				System.out.println("2\n1\n" + Math.abs(x));
				dir = 0;
				x = 0;
			}
		} else if (x > 0 && y > 0) {
			if (dir == 0) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 1) {
				System.out.println("3\n1\n" + Math.abs(x));
				dir = 0;
				x = 0;
			} else if (dir == 2) {
				System.out.println("2\n1\n" + Math.abs(y));
				dir = 3;
				y = 0;
			} else {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			}
		} else if (x < 0 && y > 0) {
			if (dir == 0) {
				System.out.println("3\n1\n" + Math.abs(y));
				dir = 3;
				y = 0;
			} else if (dir == 1) {
				System.out.println("2\n1\n" + Math.abs(x));
				dir = 2;
				x = 0;
			} else if (dir == 2) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			}
		} else if (x == 0 && y > 0) {
			if (dir == 0) {
				System.out.println("3\n1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 1) {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 2) {
				System.out.println("2\n1\n" + Math.abs(y));
				y = 0;
			} else {
				System.out.println("2\n2\n1\n" + Math.abs(y));
				y = 0;
			}
		} else if (x == 0 && y < 0) {
			if (dir == 0) {
				System.out.println("2\n1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 1) {
				System.out.println("2\n2\n1\n" + Math.abs(y));
				y = 0;
			} else if (dir == 2) {
				System.out.println("3\n1\n" + Math.abs(y));
				y = 0;
			} else {
				System.out.println("1\n" + Math.abs(y));
				y = 0;
			}
		} else if (x < 0 && y == 0) {
			if (dir == 0) {
				System.out.println("2\n2\n1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 1) {
				System.out.println("2\n1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 2) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else {
				System.out.println("3\n1\n" + Math.abs(x));
				x = 0;
			}
		} else if (x > 0 && y == 0) {
			if (dir == 0) {
				System.out.println("1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 1) {
				System.out.println("3\n1\n" + Math.abs(x));
				x = 0;
			} else if (dir == 2) {
				System.out.println("2\n2\n1\n" + Math.abs(x));
				x = 0;
			} else {
				System.out.println("2\n1\n" + Math.abs(x));
				x = 0;
			}
		}
		curr[0] = x;
		curr[1] = y;
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
