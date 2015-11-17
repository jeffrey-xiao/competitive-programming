package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class COCI_2014_UTRKA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[] in, next;
	static boolean[] visited;
	static int ans = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int n = readInt();
		HashMap<String, Integer> name = new HashMap<String, Integer>();
		for (int i = 0; i < 2 * n - 1; i++) {
			String s = next();
			if (name.get(s) == null)
				name.put(s, 0);
			name.put(s, name.get(s) + 1);
		}
		for (Map.Entry<String, Integer> e : name.entrySet()) {
			if (e.getValue() % 2 == 1) {
				ps.println(e.getKey());
				break;
			}
		}
		ps.close();
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