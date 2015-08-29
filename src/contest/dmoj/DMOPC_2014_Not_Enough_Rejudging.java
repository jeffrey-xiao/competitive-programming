package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DMOPC_2014_Not_Enough_Rejudging {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<String> status = new ArrayList<String>();
		int WA = 0;
		for (int x = 0; x < n; x++) {
			String s = next();
			if (s.equals("WA"))
				WA++;
			status.add(s);
		}
		WA = WA * 3 / 10;
		int count = 0;
		for (int x = 0; x < n; x++) {
			if (status.get(x).equals("AC"))
				System.out.println("AC");
			else if (status.get(x).equals("WA")) {
				if (WA-- > 0)
					System.out.println("AC");
				else
					System.out.println("WA");
			} else if (status.get(x).equals("IR")) {
				count++;
				if (count <= 10) {
					System.out.println("AC");
				} else if (count <= 20) {
					System.out.println("WA");
				} else {
					System.out.println("IR");
				}
			} else {
				System.out.println("WA");
			}
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
