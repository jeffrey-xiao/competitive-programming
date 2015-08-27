package contest.ioi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2013_Art_Class {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int t = 1;
		for (int i = 0; i < t; i++) {
			int h = readInt();
			int w = readInt();
			int[][] in = new int[h][w];
			double[][] g = new double[h][w];
			for (int j = 0; j < h; j++)
				for (int k = 0; k < w; k++) {
					in[j][k] = readInt();
					g[j][k] = 0.21 * getR(in[j][k]) + 0.72 * getG(in[j][k]) + 0.07 * getB(in[j][k]);
				}
			double d = 0;
			for (int j = 0; j < h; j++) {
				for (int k = 0; k < w; k++) {
					if (j < h - 1)
						d += Math.abs(g[j][k] - g[j + 1][k]);
					if (k < w - 1)
						d += Math.abs(g[j][k] - g[j][k + 1]);
				}
			}
			d /= h * w;
			if (d > 50)
				System.out.println(3);
			else if (d > 15)
				System.out.println(2);
			else if (d > 8)
				System.out.println(1);
			else
				System.out.println(4);
		}

		pr.close();
	}

	static int getR (int RGB) {
		return (RGB >> 16) & 0xFF;
	}

	static int getG (int RGB) {
		return (RGB >> 8) & 0xFF;
	}

	static int getB (int RGB) {
		return RGB & 0xFF;
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
