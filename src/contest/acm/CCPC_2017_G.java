package contest.acm;

import java.util.*;
import java.io.*;

public class CCPC_2017_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;
	static double xs, ys, s, t;
	static double[] X, Y;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int i = 0; i < T; i++) {
			xs = readDouble();
			ys = readDouble();
			s = readDouble();
			t = readDouble();
			s -= xs;
			t -= ys;
			
			N = readInt();
			X = new double[N];
			Y = new double[N];
			
			for (int j = 0; j < N; j++) {
				X[j] = readDouble() - xs;
				Y[j] = readDouble() - ys;
			}
			
			double lo = Math.max(1e-6, t);
			double hi = lo;
			
			while (!success(hi))
				hi *= 2;
			
			for (int k = 0; k < 5000; k++) {
				double mid = (lo + hi) / 2;
				if (success(mid)) {
					hi = mid;
				} else {
					lo = mid;
				}
			}
			
			out.println(lo + ys);
		}
		
		out.close();
	}

	static boolean success (double alt) {
	    double a, b;
	    
	    if (t != 0.0) {
	        b = 2.0 * alt / t * (1.0 - Math.sqrt(1.0 - t / alt)) * s;
	        a = t / (s * (s - b));
	    } else {
	        b = s;
	        a = -4.0 * alt / (b*b);
	    }
	    
	    for (int j = 0; j < N; j++) {
	        double x = X[j];
	        double fx = a * x * (x - b);
	        if (fx < Y[j])
	            return false;
	    }
	    return true;
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

