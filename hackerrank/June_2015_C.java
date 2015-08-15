package hackerrank;

import java.util.*;
import java.io.*;

public class June_2015_C {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			String n = next();
			String m = next();
			while (n.length() < m.length())
				n = "0"+n;
			while (m.length() < n.length())
				m = "0"+m;
			boolean done = false;
			for (int i = 0; i < n.length(); i++) {
				if (done)
					System.out.print("9");
				else {
					if (n.charAt(i) - '0' + m.charAt(i) - '0' > 9) {
						System.out.print("9");
						done = true;
					} else {
						System.out.print(n.charAt(i) - '0' + m.charAt(i) - '0');
					}
				}
			}
			System.out.println();
		}
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

