package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DMOPC_2014_Sawmill_Scheme {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[] sawmill = new boolean[1000000];
	static double[] prob = new double[1000000];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			adj.get(a).add(readInt() - 1);
			sawmill[a] = true;
		}
		prob[0] = 1.0;
		for (int x = 0; x < n; x++) {
			double count = adj.get(x).size();
			for (Integer i : adj.get(x))
				prob[i] += prob[x] / count;
		}
		for (int x = 0; x < n; x++)
			if (!sawmill[x])
				ps.println(prob[x]);
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
