package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_365C_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static double w, v, u;
	static int n;
	static double[] x, y;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		w = readDouble();
		v = readDouble();
		u = readDouble();
		
		x = new double[n];
		y = new double[n];
		
		double slope = u / v;
		boolean intersect = false;
		double max = 0;
		for (int i = 0; i < n; i++) {
			x[i] = readDouble();
			y[i] = readDouble();
			double pos = y[i] / slope;

			if (pos > x[i])
				intersect = true;
			else
				max = Math.max(max, x[i] - pos);
		}

		if (intersect) {
			out.println(max / v + w / u);
		} else {
			out.println(w / u);
		}
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

