package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NAQ_2016_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	static double[] prefix;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		String in = readLine();
		prefix = new double[1000001];
		for (int i = 1; i <= 1000000; i++)
			prefix[i] = prefix[i - 1] + Math.log10(i);
		if (in.length() < 9) {
			int n = 1;
			int target = Integer.parseInt(in);
			for (int i = 1;; i++) {
				n *= i;
				if (n == target) {
					out.println(i);
					out.close();
					return;
				}
			}
		} else {
			int lo = 1;
			int hi = 1000000;
			
			while (lo <= hi) {
				int mid = (hi + lo) >> 1;
				if (digits(mid) < in.length())
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			out.println(lo);
		}
		out.close();
	}

	static int digits (int n) {
		return (int)Math.ceil(prefix[n]);
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
