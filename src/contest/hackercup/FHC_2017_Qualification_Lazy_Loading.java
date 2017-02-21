package contest.hackercup;

import java.util.*;
import java.io.*;

public class FHC_2017_Qualification_Lazy_Loading {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;
	static int[] W;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			N = readInt();
			W = new int[N];
			
			for (int i = 0; i < N; i++)
				W[i] = readInt();
			
			Arrays.sort(W);
			
			int l = 0;
			int r = N - 1;
			int ans = 0;
			while (r >= l) {
				int itemsNeeded = 49 / W[r];
				if (l + itemsNeeded > r)
					break;
				l += itemsNeeded;
				r--;
				ans++;
			}
			out.printf("Case #%d: %d\n", t, ans);
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

