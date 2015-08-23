package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2009_Stage_2_Dinner {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		int n = readInt();
		int k = readInt();
		char[] in = next().toCharArray();
		int ans = 0;
		char prev = ' ';
		for (int i = 0; i < in.length; i++)
			if (in[i] != prev) {
				ans++;
				prev = in[i];
			}
		pr.println(ans / 2 + 1);
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
