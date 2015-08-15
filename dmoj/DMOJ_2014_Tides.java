package dmoj;

import java.util.*;
import java.io.*;

public class DMOJ_2014_Tides {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] in = new int[n];
		int min = 1 << 30;
		int max = -1 << 30;
		boolean increase = false;
		for (int i = 0; i < n; i++) {
			in[i] = readInt();
			min = Math.min(min, in[i]);
			max = Math.max(max, in[i]);
		}
		for (int i = 0; i < n; i++) {
			if (increase && i > 0 && in[i - 1] >= in[i]) {
				System.out.println("unknown");
				return;
			}
			if (in[i] == min) {
				if (increase) {
					System.out.println("unknown");
					return;
				}
				increase = true;
			} else if (in[i] == max) {
				if (!increase) {
					System.out.println("unknown");
					return;
				}
				increase = false;
			}
		}
		System.out.println(max - min);
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
