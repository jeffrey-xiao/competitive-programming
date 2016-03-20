package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_S1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int wildcard = 0;
		int[] cnt = new int[26];
		char[] in1 = readLine().toCharArray();
		char[] in2 = readLine().toCharArray();
		for (int i = 0; i < in1.length; i++) {
			cnt[in1[i] - 'a']++;

			if (in2[i] == '*')
				wildcard++;
			else
				cnt[in2[i] - 'a']--;
		}

		int sum = 0;
		for (int i = 0; i < 26; i++)
			sum += Math.abs(cnt[i]);
		out.println(sum <= wildcard ? "A" : "N");
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
