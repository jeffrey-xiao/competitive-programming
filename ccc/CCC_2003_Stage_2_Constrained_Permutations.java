package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Constrained_Permutations {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static HashMap<Integer, HashSet<Integer>> cons;
	static int count = 0;

	public static void main (String[] args) throws IOException {
		cons = new HashMap<Integer, HashSet<Integer>>();
		int n = readInt();
		int m = readInt();
		for (int i = 0; i < m; i++)
			add(readInt(), readInt());
		permute(n, n, new HashSet<Integer>());
		System.out.println(count);
	}

	private static void permute (int n, int curr, HashSet<Integer> v) {
		if (curr == 0) {
			count++;
			return;
		}
		main : for (int x = 1; x <= n; x++) {
			if (!v.contains(x)) {
				HashSet<Integer> c = cons.get(x);
				if (c != null)
					for (Integer i : c)
						if (!v.contains(i))
							continue main;
				v.add(x);
				permute(n, curr - 1, v);
				v.remove(x);
			}
		}
	}

	private static void add (int x, int y) {
		if (cons.get(y) == null)
			cons.put(y, new HashSet<Integer>());
		cons.get(y).add(x);
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
