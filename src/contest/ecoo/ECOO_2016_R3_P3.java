package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R3_P3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 10;
	
	static int N;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt")); // DATA31.txt
		// out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		char[][] dict = new char[N][];
		for (int i = 0; i < N; i++)
			dict[i] = next().toCharArray();
		
		for (int t = 1; t <= TEST_CASES; t++) {
			char[] in = (next() + " ").toCharArray();
			int[] dp = new int[in.length];
			
			Arrays.fill(dp, 1 << 30);
			dp[0] = 0;
			for (int j = 0; j < dp.length; j++) {
				if (dp[j] != 1 << 30) {
					for (int k = 0; k < N; k++) {
						boolean valid = true;
						for (int l = 0; l < dict[k].length; l++) {
							if (in[j + l] != dict[k][l]) {
								valid = false;
								break;
							}
						}
						if (valid) {
							dp[j + dict[k].length] = Math.min(dp[j + dict[k].length], dp[j] + 1);
						}
					}
				}
			}
			out.println(dp[in.length - 1] - 1);
			out.flush();
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

