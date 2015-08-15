package dmoj;

import java.util.*;
import java.io.*;

public class DMOJ_2014_Globally_Unique_Sails {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int a = readInt();
		int b = readInt();
		boolean[] A = new boolean[1000000];
		for (int i = 0; i < a; i++) {
			A[readInt()] = true;
		}
		int cnt = 0;
		for (int i = 0; i < b; i++) {
			if (A[readInt()])
				cnt++;
		}
		System.out.println(n - cnt);
		pr.close();
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
