package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_182C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// terminate add 1
		for (int i = 0; i < 9; i++)
			out.printf("%d??<>%d\n", i, i + 1);

		// carry
		out.printf("9??>>??0\n");

		// terminate carry
		out.printf("??<>1\n");

		// push ? right
		for (int i = 0; i < 10; i++)
			out.printf("?%d>>%d?\n", i, i);

		// finished pushing
		out.printf("?>>??\n");

		// begin
		out.printf(">>?\n");

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
