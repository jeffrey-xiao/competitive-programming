package contest.dmoj;

import java.util.*;
import java.math.BigDecimal;
import java.io.*;

public class DMOPC_2015_Tilt {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		int n = readInt();
		BigDecimal bd = new BigDecimal("0").setScale(15, BigDecimal.ROUND_HALF_UP);
		for (int i = 0; i < n; i++)
			bd = bd.add(new BigDecimal(next()));
		out.println(bd.remainder(new BigDecimal("360")).toString());
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

