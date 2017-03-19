package contest.acm;

import java.util.*;
import java.io.*;

public class CCPC_2017_I {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int H = readInt();
		int W = readInt();
		int N = readInt();
		int M = readInt();
		
		int[][] image = new int[H][W];
		int[][] kernel = new int[N][M];
		int[][] output = new int[H - N + 1][W - M + 1];
		
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				image[i][j] = readInt();
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				kernel[i][j] = readInt();
		
		for (int i = 0; i < H - N + 1; i++)
			for (int j = 0; j < W - M + 1; j++)
				for (int n = 0; n < N; n++)
					for (int m = 0; m < M; m++)
						output[i][j] += image[i + n][j + m] * kernel[N - n - 1][M - m - 1];
				
		for (int i = 0; i < H - N + 1; i++, out.println())
			for (int j = 0; j < W - M + 1; j++)
				out.print(output[i][j] + " ");
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

