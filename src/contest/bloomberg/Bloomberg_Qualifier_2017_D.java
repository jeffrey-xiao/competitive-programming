package contest.bloomberg;

import java.util.*;
import java.io.*;

public class Bloomberg_Qualifier_2017_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String in = readLine();
		
		int[] occ = new int[10];
		
		for (int i = 0; i < in.length(); i++)
			occ[in.charAt(i) - '0']++;
		StringBuilder ret = new StringBuilder();
		boolean mid = false;
		String m = "";
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < occ[i] / 2; j++)
				ret.append(i);
			if (occ[i] % 2 == 1 && !mid) {
				mid = true;
				m = ""+i;
			}
		}
		out.println(ret.toString() + m + ret.reverse().toString());
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

