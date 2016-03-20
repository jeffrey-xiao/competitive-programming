package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		int x1 = readInt();
		int y1 = readInt();
		int x2 = readInt();
		int y2 = readInt();
		Ship[] s = new Ship[n];
		for (int i = 0; i < n; i++)
			s[i] = new Ship(readInt(), readInt(), readInt(), readInt());
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			if (a == 1) {
				for (int j = 0; j < n; j++) {
					s[j].x += s[j].dx * b;
					s[j].y += s[j].dy * b;
					if (x1 <= s[j].x && s[j].x <= x2 && y1 <= s[j].y && s[j].y <= y2)
						s[j].damage += c;
				}
			} else {
				int ans = 0;
				for (int j = b - 1; j < c; j++)
					ans += s[j].damage;
				out.println(ans);
			}
		}
		out.close();
	}

	static class Ship {
		int x, y, dx, dy, damage;

		Ship (int x, int y, int dx, int dy) {
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
			this.damage = 0;
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
