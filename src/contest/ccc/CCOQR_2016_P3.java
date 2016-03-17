package contest.ccc;

import java.io.*;
import java.util.*;

public class CCOQR_2016_P3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static Point[] p;
	
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));
		
		N = readInt();
		M = readInt();
		
		p = new Point[M];
		
		for (int i = 0; i < M; i++)
			p[i] = new Point(readInt(), readInt());
		Arrays.sort(p);
		
		long ans = 0, prev = 0, height = 0;
		
		for (int i = 0; i < M; i++) {
			if (p[i].c != prev) {
				long dist = p[i].c - prev;
				long side = Math.min(height, dist);
				ans += (side) * (side + 1) / 2 + Math.max(0, (height - side) * (side));
				height = Math.max(height - side, N - p[i].r + 1);
				prev = p[i].c;
			} else {
				height = Math.max(height, N - p[i].r + 1);
			}
		}
		long dist = N + 1 - prev;
		long side = Math.min(height, dist);
		ans += (side) * (side + 1) / 2 + Math.max(0, (height - side) * side);
		out.println(ans);
		out.close();
	}

	static class Point implements Comparable<Point> {
		int r, c;
		Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo (Point p) {
			return c - p.c;
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
