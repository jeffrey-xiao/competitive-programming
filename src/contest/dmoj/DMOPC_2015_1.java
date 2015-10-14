package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		String[] name = new String[n];
		double[] val = new double[n];
		for (int i = 0; i < n; i++) {
			name[i] = next();
			val[i] = readDouble();
		}
		int index = 0;
		for (int i = 1; i < n; i++) {
			if (val[index] < val[i])
				index = i;
		}
		out.println(name[index]);
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

