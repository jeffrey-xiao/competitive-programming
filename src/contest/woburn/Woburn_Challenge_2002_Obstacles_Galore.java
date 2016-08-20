package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2002_Obstacles_Galore {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;
	static double[] X, Y;
	static double cx, cy, g, endRad;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 0; t < T; t++) {
			N = readInt();
			
			X = new double[N];
			Y = new double[N];
			
			for (int i = 0; i < N; i++) {
				X[i] = readDouble();
				Y[i] = readDouble();
			}
			
			cx = readDouble();
			cy = readDouble();
			g = readDouble();
			endRad = readDouble();
			
			double minTime = 1 << 30;
			
			for (int i = 0; i < N; i++) {
				double currTime = dist(cx, cy, X[i], Y[i]) / g;
				minTime = Math.min(minTime, currTime);
			}
			
			if (minTime >= endRad / g)
				out.println("The monkeys need help!\n");
			else {
				out.printf("%.3f\n", minTime);
				for (int i = 0; i < N; i++)
					if (Math.abs(minTime - dist(cx, cy, X[i], Y[i]) / g) < 1e-8)
						out.printf("%d ", i + 1);
				out.printf("\n%.3f\n\n", minTime * g);
			}
		}
		
		out.close();
	}

	static double dist (double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
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

