package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_J3_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int x = readInt();
		int plays = 0;
		int a = readInt();
		int b = readInt();
		int c = readInt();
		byte state = 0;
		while (x != 0) {
			switch (state) {
				case 0:
					a++;
					if (a == 35) {
						a = 0;
						x += 30;
					}
					break;
				case 1:
					b++;
					if (b == 100) {
						b = 0;
						x += 60;
					}
					break;
				case 2:
					c++;
					if (c == 10) {
						c = 0;
						x += 9;
					}
					break;
			}
			plays++;
			x--;
			state = (byte)((state + 1) % 3);
		}
		System.out.printf("Martha plays %d times before going broke.", plays);
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
