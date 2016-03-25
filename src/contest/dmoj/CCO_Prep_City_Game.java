package contest.dmoj;

import java.util.*;
import java.io.*;

public class CCO_Prep_City_Game {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, K;
	static int[][] a;
	
	public static void main (String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int K = readInt();

		for (int t = 1; t <= K; t++) {
			N = readInt();
			M = readInt();
			
			a = new int[N][M];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++) {
				    char c = readCharacter();
					a[i][j] = c == 'R' ? 1 : 0;
				}
			out.println(getMaxZeroSubmatrix() * 3);
		}
		out.close();
	}

	static int getMaxZeroSubmatrix () {
		int rows = a.length;
		int cols = a[0].length;
		int[][] height = new int[rows][cols];
		Stack<Integer> s = new Stack<Integer>();
		int ret = 0;
		for (int j = 0; j < cols; j++) {
			for (int i = rows - 1; i >= 0; i--) {
				if (a[i][j] == 1)
					height[i][j] = 0;
				else
					height[i][j] = 1 + (i == rows - 1 ? 0 : height[i + 1][j]);
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int minIndex = j;
				while (!s.isEmpty() && height[i][s.peek()] >= height[i][j]) {
					ret = Math.max(ret, (j - s.peek()) * (height[i][s.peek()]));
					minIndex = s.peek();
					height[i][minIndex] = height[i][j];
					s.pop();
				}
				s.push(minIndex);
			}
			while (!s.isEmpty()) {
				ret = Math.max(ret, (cols - s.peek()) * height[i][s.peek()]);
				s.pop();
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
