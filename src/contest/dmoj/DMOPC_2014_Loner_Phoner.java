package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Loner_Phoner {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		
		for (int i = 0; i < N; i++) {
			String in = readLine();
			
			if (in.length() != 10 || !isNumber(in) || (!in.substring(0, 3).equals("416") && !in.substring(0, 3).equals("647"))) 
				out.println("not a phone number");
			else
				out.printf("(%s)-%s-%s\n", in.substring(0, 3), in.substring(3, 6), in.substring(6, 10));
		}
		
		out.close();
	}
	
	static boolean isNumber (String s) {
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		return true;
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

