package contest.dmoj;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Spacetime_Generator {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		// pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		pr = new PrintWriter(new FileWriter("in.txt"));
		int n = 5000;
		boolean[][] done = new boolean[10000][10000];
		pr.println(n);
		pr.println(10000);
		for (int i = 0; i < n; i++) {
			int x = (int)(Math.random() * 9999);
			int y = (int)(Math.random() * 9999 + 1);
			if (done[x][y]) {
				i--;
				continue;
			}
			done[x][y] = true;
			pr.println(x + " " + y);
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
