package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Double_XP_Weekend {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int ei = readInt();
		int ef = readInt();
		int eb = readInt();
		
		int b = readInt();
		int c = readInt();
		
		int fish = 0;
		long cost = 0;
		while (ei < ef) {
			if (eb >= b) {
				ei += b;
				eb -= b;
			} else {
				ei += eb;
				eb = 0;
			}
			ei += 2 * b;
			fish += 1;
			cost += c;
		}
		out.println(fish + "\n" + cost);
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

