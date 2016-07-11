package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2008_ROT {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int R, C, rot;
	static char[][] grid;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		R = readInt();
		C = readInt();
		
		grid = new char[R][C];
		
		for (int i = 0; i < R; i++)
			grid[i] = readLine().toCharArray();
		
		rot = readInt();
		
		while (rot >= 90) {
			rot -= 90;
			grid = rot90(grid);
		}
		
		if (rot >= 45)
			grid = rot45(grid);
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				out.print(grid[i][j] == ' ' ? " " : grid[i][j]);
			}
			out.println();
		}
		
		out.close();
	}

	static char[][] rot45 (char[][] g) {
		R = grid.length;
		C = grid[0].length;
		
		char[][] ret = new char[R + C][R + C - 1];
		
		for (int i = 0; i < R + C; i++)
			for (int j = 0; j < R + C - 1; j++)
				ret[i][j] = ' ';			
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ret[i + j][R - 1 - i + j] = g[i][j];
			}
		}
		return ret;
	}
	
	static char[][] rot90 (char[][] g) {
		R = grid.length;
		C = grid[0].length;
		
		char[][] ret = new char[C][R];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ret[j][R - i - 1] = g[i][j];
			}
		}
		
		return ret;
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

