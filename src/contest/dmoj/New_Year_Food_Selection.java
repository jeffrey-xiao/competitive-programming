package contest.dmoj;

import java.util.*;
import java.io.*;

public class New_Year_Food_Selection {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int C = readInt();
		int F = readInt();
		int S = readInt();
		
		int n = readInt();
		for (int i = 0; i < n; i++) {
			int x = readInt();
			int c = readInt();
			int f = readInt();
			int s = readInt();
			String str = endOfLine();
			if (c <= C * x && f <= F * x && s <= S * x)
				System.out.println(str);
		}
		
		out.close();
	}

	static String endOfLine () throws IOException {
		String res = "";
		while (st != null && st.hasMoreTokens())
			res += st.nextToken() + " ";
		return res;
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

