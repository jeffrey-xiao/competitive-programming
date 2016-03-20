package contest.codeforces;

import java.util.*;
import java.io.*;

public class CROC_2016_Qualification_A {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int a = readInt();
		int b = readInt();

		int even = n / 2;
		int odd = (n + 1) / 2;

		int cnt1 = (a % 2 == 0 ? (a / 2) * b : (a / 2) * b + (b + 1) / 2);
		int cnt2 = a * b - cnt1;

		if (cnt1 < odd || cnt2 < even)
			out.println(-1);
		else {
			int x = 1;
			int y = 2;
			for (int i = 0; i < a; i++) {
				for (int j = 0; j < b; j++) {
					if ((i + j) % 2 == 0 && x <= n) {
						out.print(x + " ");
						x += 2;
					} else if ((i + j) % 2 == 1 && y <= n) {
						out.print(y + " ");
						y += 2;
					} else {
						out.print("0 ");
					}
				}
				out.println();
			}
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
