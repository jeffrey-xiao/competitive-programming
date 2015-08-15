package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2014_SABOR {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int n;
	static boolean[] mp;
	static ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		n = readInt();
		mp = new boolean[n];
		for (int i = 0; i < n; i++) {
			// mp[i] = (int)(Math.random()*2) == 1;
			adj.add(new HashSet<Integer>());
		}
		for (int i = 0; i < 5; i++) {
			int j = readInt();
			for (; j > 0; j--) {
				int a = readInt() - 1;
				int b = readInt() - 1;
				adj.get(a).add(b);
				adj.get(b).add(a);
			}
		}
		boolean change = true;
		while (change) {
			change = false;
			for (int i = n - 1; i >= 0; i--) {
				int cnt = 0;
				for (int j : adj.get(i))
					if (mp[j] == mp[i])
						cnt++;
				if (cnt > 2) {
					change = true;
					mp[i] = !mp[i];
				}
			}
		}
		for (int i = 0; i < n; i++)
			ps.print(mp[i] ? 'A' : 'B');
		ps.println();
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