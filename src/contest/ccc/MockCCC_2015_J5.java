package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MockCCC_2015_J5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Map<Integer, ArrayList<Integer>> xC = new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, ArrayList<Integer>> yC = new HashMap<Integer, ArrayList<Integer>>();
		for (int z = 0; z < n; z++) {
			int x = readInt();
			int y = readInt();
			if (xC.get(x) == null)
				xC.put(x, new ArrayList<Integer>());
			if (yC.get(y) == null)
				yC.put(y, new ArrayList<Integer>());
			xC.get(x).add(y);
			yC.get(y).add(x);
		}
		for (Map.Entry<Integer, ArrayList<Integer>> e : xC.entrySet()) {
			Collections.sort(e.getValue());
		}
		for (Map.Entry<Integer, ArrayList<Integer>> e : yC.entrySet()) {
			Collections.sort(e.getValue());
		}
		int m = readInt();

		int x = readInt();
		int y = readInt();
		long res = 0;
		for (int z = 0; z < m - 1; z++) {
			int nx = readInt();
			int ny = readInt();

			if (x == nx) {
				if (xC.get(x) != null) {
					Integer c1 = bs(xC.get(x), Math.max(ny, y), true);
					Integer c2 = bs(xC.get(x), Math.min(ny, y), false);
					// System.out.println("y " + c1 + " " + c2);
					res += c1 - Math.max(0, c2) + 1;
				}

			} else {
				if (yC.get(y) != null) {
					Integer c1 = bs(yC.get(y), Math.max(nx, x), true);
					Integer c2 = bs(yC.get(y), Math.min(nx, x), false);
					// System.out.println("x " + c1 + " " + c2);
					res += c1 - Math.max(0, c2) + 1;
				}

			}
			x = nx;
			y = ny;
		}
		System.out.println(res);
	}

	private static int bs (ArrayList<Integer> a, int i, boolean lower) {
		int lo = 0;
		int hi = a.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (lower) {
				if (a.get(mid) <= i)
					lo = mid + 1;
				else
					hi = mid - 1;
			} else {
				if (a.get(mid) < i)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
		}
		return lower ? hi : lo;
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
