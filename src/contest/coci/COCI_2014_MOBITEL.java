package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_MOBITEL {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[] in, next;
	static boolean[] visited;
	static int ans = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int[] map = new int[10];
		for (int i = 1; i <= 9; i++) {
			map[readInt()] = i;
		}
		int[] num = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
		int[] times = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4};
		char[] in = next().toCharArray();
		int prevNum = 0;
		for (int i = 0; i < in.length; i++) {
			int j = times[in[i] - 'a'];
			int nn = map[num[in[i] - 'a']];
			if (prevNum == nn) {
				ps.print("#");
			}
			prevNum = nn;
			for (int k = 0; k < j; k++)
				ps.print(nn);
		}
		ps.close();
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