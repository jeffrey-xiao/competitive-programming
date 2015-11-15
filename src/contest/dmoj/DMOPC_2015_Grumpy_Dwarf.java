package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Grumpy_Dwarf {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		int ans = 0;
		int swords = 0;
		int bars = readInt();
		int k = readInt();
		while (bars > 0) {
			ans += bars;
			swords += bars;
			bars = 0;
			bars = swords / k;
			swords %= k;
		}
		out.println(ans);
		
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

