package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DMOPC_2014_Streetcars {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] num = new int[n];
		int[] percent = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = readInt();
			percent[i] = readInt();
		}
		int[] curr = Arrays.copyOf(num, num.length);
		ArrayList<Integer> c = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < c.size(); j++) {
				c.set(j, c.get(j) - c.get(j) * percent[i] / 100);
			}
			for (int j = 0; j < c.size(); j++) {
				if (c.get(j) + curr[i] <= 132) {
					c.set(j, c.get(j) + curr[i]);
					curr[i] = 0;
				} else {
					curr[i] -= 132 - c.get(j);
					c.set(j, 132);
				}
			}
			while (curr[i] > 0) {
				int minus = 132;
				if (curr[i] < 132)
					minus = curr[i];
				c.add(minus);
				curr[i] -= minus;
			}
		}
		int size1 = c.size();
		curr = Arrays.copyOf(num, num.length);
		c = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < c.size(); j++) {
				c.set(j, c.get(j) - c.get(j) * percent[i] / 100);
			}
			for (int j = 0; j < c.size(); j++) {
				if (c.get(j) + curr[i] <= 251) {
					c.set(j, c.get(j) + curr[i]);
					curr[i] = 0;
				} else {
					curr[i] -= 251 - c.get(j);
					c.set(j, 251);
				}
			}
			while (curr[i] > 0) {
				int minus = 251;
				if (curr[i] < 251)
					minus = curr[i];
				c.add(minus);
				curr[i] -= minus;
			}
		}
		int size2 = c.size();
		System.out.println(size1 - size2);
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
