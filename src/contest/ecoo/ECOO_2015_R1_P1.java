package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2015_R1_P1 {

	static BufferedReader br;
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("DATA11.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int qq = 0; qq < 10; qq++) {
			int[] sum = new int[8];
			String next = readLine();
			while (!next.equals("end of box")) {
				if (next.equals("red"))
					sum[0]++;
				else if (next.equals("blue"))
					sum[1]++;
				else if (next.equals("green"))
					sum[2]++;
				else if (next.equals("yellow"))
					sum[3]++;
				else if (next.equals("pink"))
					sum[4]++;
				else if (next.equals("violet"))
					sum[5]++;
				else if (next.equals("brown"))
					sum[6]++;
				else if (next.equals("orange"))
					sum[7]++;
				next = readLine();
			}
			System.out.println(sum[0] * 16 + get(sum[1]) + get(sum[2]) + get(sum[3]) + get(sum[4]) + get(sum[5]) + get(sum[6]) + get(sum[7]));
		}
	}

	private static int get (int i) {
		return (i + 6) / 7 * 13;
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
