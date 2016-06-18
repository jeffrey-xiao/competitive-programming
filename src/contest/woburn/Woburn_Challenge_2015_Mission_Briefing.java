package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_Mission_Briefing {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static char[] in;
	static boolean[] vis = new boolean[10];
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		in = readLine().toCharArray();
		
		for (int i = 0; i < in.length - 2; i++) {
			if (in[i] == '0' && in[i + 1] == '0' && in[i + 2] - '0' <= 9 && in[i + 2] - '0' >= 1)
				vis[in[i + 2] - '0'] = true;
		}
		
		int ans = 0;
		for (int i = 0; i < 10; i++)
			if (vis[i])
				ans++;
		
		out.println(ans);
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

