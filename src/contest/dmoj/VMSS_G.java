package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		char[][] g = { {'G', 'G', 'G', 'G', 'G'}, {'G', '.', '.', '.', '.'}, {'G', '.', '.', 'G', 'G'}, {'G', '.', '.', '.', 'G'}, {'G', 'G', 'G', 'G', 'G'}};
		int n = readInt();
		for (int i = 0; i < 5 * n; i++, out.println())
			for (int j = 0; j < 5 * n; j++)
				out.print(g[i / n][j / n]);
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
