package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class CCC_1998_D {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int qq = readInt(); qq > 0; qq--) {
			StringTokenizer s = new StringTokenizer(readLine());
			LinkedList<String> seq = new LinkedList<String>();
			while (s.hasMoreTokens()) {
				String t = s.nextToken();
				if (t.equals("X"))
					seq.add("(" + seq.pollLast() + " X " + s.nextToken() + ")");
				else
					seq.add(t);

			}
			LinkedList<String> res = new LinkedList<String>();
			while (!seq.isEmpty()) {
				String t = seq.pollFirst();
				if (t.equals("+") || t.equals("-"))
					res.add("(" + res.pollLast() + " " + t + " " + seq.pollFirst() + ")");
				else
					res.add(t);
			}
			String ans = "";
			for (String string : res) {
				ans += string;
			}
			System.out.println(ans.substring(1, ans.length() - 1));
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
