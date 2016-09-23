package contest.bloomberg;

import java.util.*;
import java.io.*;

public class P6 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int R, C;
	static boolean[][] g;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		R = readInt();
		C = readInt();
		
		g = new boolean[R][C];
		
		int M = readInt();
		
		for (int i = 0; i < M; i++)
			g[readInt()][readInt()] = true;
		
		LinkedList<Point> l = new LinkedList<Point>();
		l.add(new Point(0, 0));
		int N = readInt();
		Point curr = new Point(0, 0);
		for (int i = 0; i < N; i++) {
			char c = readCharacter();
			switch (c) {
				case 'R':
					curr.c++;
					break;
				case 'L':
					curr.c--;
					break;
				case 'U':
					curr.r--;
					break;
				case 'D':
					curr.r++;
					break;
			}
			if (!g[curr.r][curr.c]) {
				l.removeFirst();
			}
			g[curr.r][curr.c] = false;
			for (Point p : l) {
				if (p.r == curr.r && p.c == curr.c) {
					out.println(i + 1);
					out.close();
					return;
				}
			}
			l.addLast(new Point(curr.r, curr.c));
		}
		out.println(-1);
		out.close();
	}

	static class Point {
		int r, c;
		Point (int r, int c) {
			this.r = r;
			this.c = c;
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

