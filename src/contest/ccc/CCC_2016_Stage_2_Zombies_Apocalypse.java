package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_Stage_2_Zombies_Apocalypse {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, K;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		out.close();
	}

	static class Line implements Comparable<Line> {
		int c, lor, hir, index, type;
		
		Line (int c, int lor, int hir, int index, int type) {
			this.c = c;
			this.lor = lor;
			this.hir = hir;
			this.index = index;
			this.type = type;
		}

		@Override
		public int compareTo (Line l) {
			return c - l.c;
		}
	}
	
	static class Point implements Comparable<Point> {
		int r, index, type;
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

