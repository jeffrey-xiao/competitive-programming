package contest.codejam;

import java.util.*;
import java.io.*;

public class Round_2_C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, R, C;
	static int[] lover;
	static char[][] g;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		main : for (int t = 1; t <= T; t++) {
//			System.out.println("NEW CASE");
			out.printf("Case #%d: \n", t);
			R = readInt();
			C = readInt();

			lover = new int[R * 2 + C * 2];

			for (int i = 0; i < R * 2 + C * 2; i += 2) {
				int a = readInt() - 1;
				int b = readInt() - 1;
				
				lover[a] = b;
				lover[b] = a;
			}

			inner : for (int i = 0; i < 1 << (R * C); i++) {
//				System.out.println("NEW GRID");
				g = new char[R][C];
				for (int j = 0; j < R * C; j++) {
					if ((i & 1 << j) > 0) {
						g[j / C][j % C] = '/';
					} else {
						g[j / C][j % C] = '\\';
					}
				}
//				for (int j = 0; j < R; j++)
//					System.out.println(Arrays.toString(g[j]));
				// 0 : north, 1 : east, 2 : south, 3 : west
				// down
				for (int j = 0; j < C; j++) {
					int currR = 0;
					int currC = j;
					int dir = 2;
					int a = getLover(currR, currC, dir) - 1;
//					System.out.printf("%d %d\n", currR - 1, currC);
					int b = getId(currR - 1, currC) - 1;
//					System.out.printf("%d-%d\n", a, b);
					if (lover[a] != b) {
						continue inner;
					}
					
					currR = R - 1;
					currC = j;
					dir = 0;
					a = getLover(currR, currC, dir) - 1;
					b = getId(currR + 1, currC) - 1;
//					System.out.printf("%d-%d\n", a, b);
					if (lover[a] != b)
						continue inner;
				}
				
				for (int j = 0; j < R; j++) {
					int currR = j;
					int currC = 0;
					int dir = 1;
					int a = getLover(currR, currC, dir) - 1;
					int b = getId(currR, currC - 1) - 1;
//					System.out.printf("%d-%d\n", a, b);
					if (lover[a] != b) {
						continue inner;
					}
					
					currR = j;
					currC = C - 1;
					dir = 3;
					a = getLover(currR, currC, dir) - 1;
					b = getId(currR, currC + 1) - 1;
//					System.out.printf("%d-%d\n", a, b);
					if (lover[a] != b)
						continue inner;
				}
				// this is valid;
				for (int j = 0; j < R; j++) {
					for (int k = 0; k < C; k++) {
						out.printf("%c", g[j][k]);
					}
					out.println();
				}
				continue main;
			}
			out.println("IMPOSSIBLE");
		}

		out.close();
	}

	static int getLover (int currR, int currC, int dir) {
//		System.out.printf("%d %d %d\n", currR, currC, dir);
		while (currR >= 0 && currR < R && currC >= 0 && currC < C) {
			if (g[currR][currC] == '/') {
				if (dir == 2) {
					dir = 3;
					currC--;
				} else if (dir == 3) {
					dir = 2;
					currR++;
				} else if (dir == 0) {
					dir = 1;
					currC++;
				} else if (dir == 1) {
					dir = 0;
					currR--;
				}
			} else {
				if (dir == 2) {
					dir = 1;
					currC++;
				} else if (dir == 3) {
					dir = 0;
					currR--;
				} else if (dir == 0) {
					dir = 3;
					currC--;
				} else if (dir == 1) {
					dir = 2;
					currR++;
				}
			}
//			System.out.printf("%d %d %d\n", currR, currC, dir);
		}
		return getId(currR, currC);
	}
	
	static int getId (int currR, int currC) {
//		System.out.println("HERE " + currR + " " + currC);
		if (currR < 0) {
			return currC + 1;
		} else if (currR >= R) {
			return C + R + (C - currC);
		} else if (currC < 0) {
//			System.out.println("RET " + (C + R + C + (R - currR)));
			return C + R + C + (R - currR);
		} else if (currC >= C) {
			return C + currR + 1;
		} else {
			System.out.println("FUCK");
			return -1;
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

