package contest.dmoj;

import java.util.*;
import java.io.*;

public class Mackenzie_Abdoof {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String[] in = readLine().toLowerCase().split(" ");
		if (in.length <= 1) {
			out.println("invalid");
		} else {
			boolean valid = true;
			for (int i = 0; i < in.length; i++)
				valid &= isValid(in[i], -1, 0);
			out.println(valid ? "valid" : "invalid");
		}

		out.close();
	}

	static boolean isValid (String s, int prev, int cnt) {
		if (s.equals(""))
			return (prev == 2 || prev == 0) && cnt >= 2;
		if (prev == 2)
			return false;
		boolean valid = false;
		if (prev != 0) {
			if (s.length() >= 1)
				if (s.charAt(0) == 't' || s.charAt(0) == 'm' || s.charAt(0) == 'd' || s.charAt(0) == 'b' || s.charAt(0) == 'r')
					valid |= isValid(s.substring(1), 0, cnt + 1);
			if (s.length() >= 2)
				if (s.substring(0, 2).equals("ch") || s.substring(0, 2).equals("bd"))
					valid |= isValid(s.substring(2), 0, cnt + 1);
		}
		if (prev != 1) {
			if (s.length() >= 1)
				if (s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i')
					valid |= isValid(s.substring(1), 1, cnt + 1);
			if (s.length() >= 2)
				if (s.substring(0, 2).equals("oo"))
					valid |= isValid(s.substring(2), 1, cnt + 1);
		}
		if (prev != 2) {
			if (s.length() >= 1)
				if (s.charAt(0) == 'n' || s.charAt(0) == 'v' || s.charAt(0) == 'f')
					valid |= isValid(s.substring(1), 2, cnt + 1);
		}
		return valid;
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
