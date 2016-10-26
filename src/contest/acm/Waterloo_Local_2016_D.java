package contest.acm;

import java.util.*;
import java.io.*;

public class Waterloo_Local_2016_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int R = readInt();
		int C = readInt();
		
		char[][] g = new char[R][C];
		boolean[][] vis = new boolean[R][C];
		for (int i = 0; i < R; i++)
			g[i] = readLine().toCharArray();
		
		int r = 0;
		int c = 0;
		int moves = 0;
		while (g[r][c] != 'T') {
			vis[r][c] = true;
			switch (g[r][c]) {
				case 'N':
					r--;
					break;
				case 'S':
					r++;
					break;
				case 'E':
					c++;
					break;
				case 'W':
					c--;
					break;
			}
			if (r < 0 || r >= R || c < 0 || c >= C) {
				out.println("Out");
				out.close();
				return;
			}
			
			if (vis[r][c]) {
				out.println("Lost");
				out.close();
				return;
			}
			
			moves++;
		}
		out.println(moves);
		out.close();
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
