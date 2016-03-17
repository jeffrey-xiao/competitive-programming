package contest.ccc;

import java.io.*;
import java.util.*;

public class CCOQR_2016_P1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;

	static int[] X, Y;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		X = new int[N];
		Y = new int[N];

		ArrayList<ArrayList<Integer>> xs = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> ys = new ArrayList<ArrayList<Integer>>();
		
		HashMap<Integer, Integer> xindex = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> yindex = new HashMap<Integer, Integer>();
		
		int xcnt = 0;
		int ycnt = 0;
		
		for (int i = 0; i < 100000; i++) {
			xs.add(new ArrayList<Integer>());
			ys.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			X[i] = readInt();
			Y[i] = readInt();
			if (xindex.get(X[i]) == null)
				xindex.put(X[i], xcnt++);
			if (yindex.get(Y[i]) == null)
				yindex.put(Y[i], ycnt++);
			
			xs.get(xindex.get(X[i])).add(Y[i]);
			ys.get(yindex.get(Y[i])).add(X[i]);
		}

		for (int i = 0; i < 100000; i++) {
			Collections.sort(xs.get(i));
			Collections.sort(ys.get(i));
		}
		
		long ans = 0;
		for (int i = 0; i < N; i++) {
			int indexincol = bsearch(xs.get(xindex.get(X[i])), Y[i]);
			int indexinrow = bsearch(ys.get(yindex.get(Y[i])), X[i]);
			long left = indexinrow;
			long right = ys.get(yindex.get(Y[i])).size() - indexinrow - 1;
			long down = indexincol;
			long up = xs.get(xindex.get(X[i])).size() - indexincol - 1;
			ans += (left * down) * (up * right) + (left * up) * (right * down);
		}
		out.println(ans);
		out.close();
	}

	static class Point {
		int x, y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int bsearch (ArrayList<Integer> a, int val) {
		int lo = 0;
		int hi = a.size() - 1;
		while (lo <= hi) {
			int mid = (hi + lo) / 2;
			if (a.get(mid) < val) {
				lo = mid + 1;
			} else if (a.get(mid) > val) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
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
