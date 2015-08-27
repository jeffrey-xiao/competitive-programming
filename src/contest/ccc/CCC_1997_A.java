package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1997_A {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int v = readInt();
			int o = readInt();
			String[] nouns = new String[n];
			String[] verbs = new String[v];
			String[] objects = new String[o];
			for (int x = 0; x < n; x++)
				nouns[x] = readLine();
			for (int x = 0; x < v; x++)
				verbs[x] = readLine();
			for (int x = 0; x < o; x++)
				objects[x] = readLine();
			for (int x = 0; x < n; x++)
				for (int y = 0; y < v; y++)
					for (int z = 0; z < o; z++)
						System.out.printf("%s %s %s.\n", nouns[x], verbs[y], objects[z]);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
