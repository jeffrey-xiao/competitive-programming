package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class DMOPC_2014_P5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();

		ArrayList<ArrayList<Integer>> pos = new ArrayList<ArrayList<Integer>>();
		HashSet<Integer> left = new HashSet<Integer>();
		int[] occ = new int[m];
		ArrayList<HashSet<Integer>> c = new ArrayList<HashSet<Integer>>();
		for (int x = 0; x < n; x++) {
			c.add(new HashSet<Integer>());
			String s = readLine();
			for (int y = 0; y < m; y++)
				if (s.charAt(y) == 'X')
					c.get(x).add(y);
			if (c.get(x).size() != 0)
				left.add(x);
		}
		int ans = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (!left.isEmpty()) {
			// System.out.println("HERE");
			occ = new int[m];
			for (Integer i : left) {
				// errors
				for (Integer z : c.get(i)) {
					occ[z]++;
					// System.out.println("OCC ADDED AT " + z);
				}
			}
			int max = 0;
			int index = -1;
			for (int x = 0; x < m; x++) {
				if (occ[x] > max) {
					// System.out.println("EDITED HERE");
					max = occ[x];
					index = x;
				}
			}
			ans++;
			result.add(index);
			ArrayList<Integer> remove = new ArrayList<Integer>();
			for (Integer i : left) {
				if (c.get(i).contains(index))
					remove.add(i);
			}
			for (int x = 0; x < remove.size(); x++)
				left.remove(remove.get(x));
		}
		if (ans == 0) {
			System.out.println(ans + 1 + "\n" + "1");
		} else {
			System.out.println(ans);
			for (Integer x : result)
				System.out.print(x + 1 + " ");
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
