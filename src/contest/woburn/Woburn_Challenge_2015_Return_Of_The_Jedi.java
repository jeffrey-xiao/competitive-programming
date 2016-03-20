package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_Return_Of_The_Jedi {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int S = readInt();
		int E = readInt();
		int R = readInt();

		Point[] t = new Point[S];
		for (int i = 0; i < S; i++)
			t[i] = new Point(readInt(), readInt(), readInt());

		int ans = 0;

		for (int i = 0; i < E; i++) {
			int x = readInt();
			int y = readInt();
			HashSet<Integer> types = new HashSet<Integer>();
			for (int j = 0; j < S; j++)
				if ((x - t[j].x) * (x - t[j].x) + (y - t[j].y) * (y - t[j].y) <= R * R)
					types.add(t[j].type);

			if (types.size() > 1)
				ans++;
		}
		out.println(ans);

		out.close();
	}

	static class Point {
		int type, x, y;

		Point (int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
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
