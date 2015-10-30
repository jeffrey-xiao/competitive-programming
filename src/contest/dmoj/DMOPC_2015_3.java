package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		char[] str = next().toCharArray();
		String s = next();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < str.length; j++)
				str[j] = (char) ((str[j] - 'a' - 1 + 26) % 26 + 'a');
			main : for (int j = 0; j < str.length - s.length() + 1; j++) {
				for (int k = 0; k < s.length(); k++) {
					if (s.charAt(k) != str[j + k])
						continue main;
				}
				out.println((i + 1) % 26);
				out.println(new String(str));
				out.close();
				return;
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
