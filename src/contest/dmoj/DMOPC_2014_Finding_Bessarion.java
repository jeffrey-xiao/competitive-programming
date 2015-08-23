package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Finding_Bessarion {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int lesliei = -100;
		int bayviewi = -100;
		int bessi = -100;
		for (int i = 0; i < n; i++) {
			String s = readLine();
			if (s.equals("Bayview"))
				bayviewi = i;
			else if (s.equals("Leslie"))
				lesliei = i;
			else if (s.equals("Bessarion"))
				bessi = i;
		}
		if (Math.abs(bayviewi - bessi) == 1 && Math.abs(lesliei - bessi) == 1)
			System.out.println("Y");
		else
			System.out.println("N");
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
