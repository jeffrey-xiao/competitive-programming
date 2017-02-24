package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_CCO {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String[] design = {readLine(), readLine(), readLine()};

		String V = next();
		int H = readInt();
		int W = readInt();

		int height = 2 * H + 5;
		int width = 2 * W + 5;
		int index = 0;
		for (int i = 1; i <= height; i++) {
			if (i == 1 || i == height)
				out.println(rep('=', width));
			else if (i == 2)
				out.printf("|%s%s|\n", V, rep(' ', width - 2 - V.length()));
			else if (i == height - 1)
				out.printf("|%s%s|\n", rep(' ', width - 2 - V.length()), V);
			else if (i <= 1 + H || i >= height - H)
				out.printf("|%s|\n", rep(' ', width - 2));
			else
				out.printf("|%s%s%s|\n", rep(' ', (width - 5) / 2), design[index++], rep(' ', (width - 5) / 2));
		}

		out.close();
	}

	static String rep (char c, int times) {
		StringBuilder ret = new StringBuilder("");
		for (int i = 0; i < times; i++)
			ret.append(c);
		return ret.toString();
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
