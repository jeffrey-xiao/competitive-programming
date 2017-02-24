package contest.hackerearth;

import java.util.*;
import java.io.*;

public class Clash_May_2016_P5 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] val;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		val = new int[N];

		for (int i = 0; i < N; i++)
			val[i] = next().length() - next().length();

		int zeroIndex = -1;
		int positiveIndex = -1;
		int negativeIndex = -1;
		for (int i = 0; i < N; i++) {
			if (val[i] == 0)
				zeroIndex = i;
			else if (val[i] > 0)
				positiveIndex = i;
			else
				negativeIndex = i;
		}
		if (zeroIndex != -1) {
			out.printf("YES\n%d\n%d\n", 1, zeroIndex + 1);
		} else if (positiveIndex == -1 || negativeIndex == -1) {
			out.printf("NO\n");
		} else {
			out.printf("YES\n%d\n", Math.abs(val[negativeIndex]) + Math.abs(val[positiveIndex]));
			for (int i = 0; i < Math.abs(val[negativeIndex]); i++)
				out.printf("%d ", positiveIndex + 1);
			for (int i = 0; i < Math.abs(val[positiveIndex]); i++)
				out.printf("%d ", negativeIndex + 1);
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
