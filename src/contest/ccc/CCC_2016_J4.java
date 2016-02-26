package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_J4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String[] time = readLine().split(":");
		int min = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
		int travelTime = 240;
		for (int i = 0; i < (travelTime + 1) / 2; i++, min++) {
			if (7 * 60 < min && min <= 10 * 60) {
				travelTime++;
			} else if (15 * 60 < min && min <= 19 * 60) {
				travelTime++;
			}
		}
		min %= 1440;
		out.printf("%02d:%02d\n", min / 60, min % 60);

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

