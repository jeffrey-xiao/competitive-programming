package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Most_Illogical {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		Stack<String> s = new Stack<String>();
		s.push(next());
		for (int i = 0; i < N / 2; i++) {
			String op = next();
			String next = next();
			if (op.equals("and")) {
				if (next.equals("false") || s.peek().equals("false")) {
					s.pop();
					s.push("false");
				} else if (next.equals("unknown") || s.peek().equals("unknown")) {
					s.pop();
					s.push("unknown");
				} else {
					s.pop();
					s.push("true");
				}
			} else {
				s.push(next);
			}
		}

		while (s.size() > 1) {
			String last = s.pop();
			if (last.equals("true") || s.peek().equals("true")) {
				s.pop();
				s.push("true");
			} else if (last.equals("unknown") || s.peek().equals("unknown")) {
				s.pop();
				s.push("unknown");
			} else {
				s.pop();
				s.push("false");
			}
		}
		out.println(s.pop());
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
