package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Glenforest_Equation_Solver {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String[] in = readLine().split(" ");
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < in.length - 1;) {
			if (!in[i].equals("P") && !in[i].equals("M")) {
				s.push(Integer.parseInt(in[i]));
				i++;
			} else if (in[i].equals("P")) {
				i++;
				s.push(s.pop() + Integer.parseInt(in[i++]));
			} else if (in[i].equals("M")) {
				i++;
				s.push(s.pop() - Integer.parseInt(in[i++]));
			}
		}
		System.out.println(s.pop());
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
