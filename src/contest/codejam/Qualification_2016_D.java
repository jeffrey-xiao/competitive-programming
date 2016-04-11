package contest.codejam;

import java.util.*;
import java.io.*;

public class Qualification_2016_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, K, C, S;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			K = readInt();
			C = readInt();
			S = readInt();
			
			int needed = (K + C - 1) / C;
			if (needed > S)
				out.printf("Case #%d: IMPOSSIBLE\n", t);
			else {
				int[][] pos = new int[needed][C];
				int cnt = 0;
				for (int i = 0; i < needed; i++) {
					for (int j = 0; j < C; j++) {
						if (cnt >= K)
							pos[i][j] = K - 1;
						else
							pos[i][j] = cnt;
						cnt++;
					}
				}
				out.printf("Case #%d: ", t);
				for (int i = 0; i < needed; i++) {
					long ans = 0;
					long pow = pow(K, C - 1);
					for (int j = 0; j < C; j++) {
						ans += pos[i][j] * pow;
						pow /= K;
					}
					out.printf("%d ", ans + 1);
				}
				out.println();
			}
		}	

		out.close();
	}

	static long pow (long base, long pow) {
		if (pow == 0)
			return 1;
		if (pow == 1)
			return base;
		if (pow % 2 == 0)
			return pow(base * base, pow / 2);
		return base * pow(base * base, pow / 2);
	}
	
	static void compute (int sz, int com) {
		
		for (int i = 0; i < 1 << sz; i++) {
			String res = String.format("%03d", Integer.parseInt(Integer.toString(i, 2)));
			String start = res;
			for (int j = 1; j < com; j++) {
				String next = "";
				for (int k = 0; k < res.length(); k++)
					if (res.charAt(k) == '0')
						for (int l = 0; l < res.length(); l++)
							next += '0';
					else
						next += res;
				res = next;
			}
			System.out.println(res + " " + start);
		}
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

