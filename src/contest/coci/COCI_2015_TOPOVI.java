package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2015_TOPOVI {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, K, P;
	static HashMap<Integer, Integer> rowVal = new HashMap<Integer, Integer>(),
			colVal = new HashMap<Integer, Integer>();
	static HashMap<Integer, Long> rowOcc = new HashMap<Integer, Long>(),
			colOcc = new HashMap<Integer, Long>();
	static HashMap<Point, Integer> toValue = new HashMap<Point, Integer>();
	static long rowTotal = 0, colTotal = 0;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		K = readInt();
		P = readInt();

		for (int i = 0; i < K; i++) {
			int row = readInt();
			int col = readInt();
			int val = readInt();

			insert(rowVal, rowOcc, row, val, false);
			insert(colVal, colOcc, col, val, true);

			toValue.put(new Point(row, col), val);
		}

		long ans = 0;

		for (Map.Entry<Integer, Long> e : rowOcc.entrySet()) {
			Long occ = colOcc.get(e.getKey());
			if (occ == null)
				occ = 0L;
			long distinct = N - occ;
			ans += distinct * e.getValue() - (colTotal - occ) * e.getValue();
		}

		for (Map.Entry<Integer, Long> e : colOcc.entrySet()) {
			Long occ = rowOcc.get(e.getKey());
			if (occ == null)
				occ = 0L;
			long distinct = N - occ;
			ans += distinct * e.getValue();
		}

		for (int i = 0; i < P; i++) {
			int r1 = readInt();
			int c1 = readInt();
			int r2 = readInt();
			int c2 = readInt();

			int val = toValue.get(new Point(r1, c1));
			toValue.remove(new Point(r1, c1));
			toValue.put(new Point(r2, c2), val);

			int before = 0, after = 0;
			Long occ;
			long distinct;

			if (rowVal.get(r1) == null)
				occ = N - colTotal;
			else
				occ = colOcc.get(rowVal.get(r1));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			before += distinct;

			if (colVal.get(c1) == null)
				occ = N - rowTotal;
			else
				occ = rowOcc.get(colVal.get(c1));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			before += distinct;

			if (!equal(colVal.get(c1), rowVal.get(r1)))
				before--;

			insert(rowVal, rowOcc, r1, val, false);
			insert(colVal, colOcc, c1, val, true);

			if (rowVal.get(r1) == null)
				occ = N - colTotal;
			else
				occ = colOcc.get(rowVal.get(r1));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			after += distinct;

			if (colVal.get(c1) == null)
				occ = N - rowTotal;
			else
				occ = rowOcc.get(colVal.get(c1));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			after += distinct;

			if (!equal(colVal.get(c1), rowVal.get(r1)))
				after--;

			ans += (after - before);

			before = 0;
			after = 0;

			if (rowVal.get(r2) == null)
				occ = N - colTotal;
			else
				occ = colOcc.get(rowVal.get(r2));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			before += distinct;

			if (colVal.get(c2) == null)
				occ = N - rowTotal;
			else
				occ = rowOcc.get(colVal.get(c2));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			before += distinct;

			if (!equal(colVal.get(c2), rowVal.get(r2)))
				before--;

			insert(rowVal, rowOcc, r2, val, false);
			insert(colVal, colOcc, c2, val, true);

			if (rowVal.get(r2) == null)
				occ = N - colTotal;
			else
				occ = colOcc.get(rowVal.get(r2));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			after += distinct;

			if (colVal.get(c2) == null)
				occ = N - rowTotal;
			else
				occ = rowOcc.get(colVal.get(c2));
			if (occ == null)
				occ = 0L;
			distinct = N - occ;
			after += distinct;

			if (!equal(colVal.get(c2), rowVal.get(r2)))
				after--;

			ans += (after - before);
			out.println(ans);
		}

		out.close();
	}

	static boolean equal (Integer a, Integer b) {
		if (a == null)
			a = 0;
		if (b == null)
			b = 0;
		return a.equals(b);
	}

	static void insert (HashMap<Integer, Integer> values, HashMap<Integer, Long> occ, int index, int val, boolean isCol) {
		if (!values.containsKey(index))
			values.put(index, 0);
		Integer oldVal = values.get(index);
		int newVal = values.get(index) ^ val;
		values.put(index, values.get(index) ^ val);
		if (values.get(index) == 0)
			values.remove(index);

		if (newVal != 0) {
			if (!occ.containsKey(newVal))
				occ.put(newVal, 0L);
			occ.put(newVal, occ.get(newVal) + 1);

			if (isCol) colTotal++;
			else rowTotal++;
		}
		if (oldVal != 0) {
			occ.put(oldVal, occ.get(oldVal) - 1);
			if (occ.get(oldVal) == 0)
				occ.remove(oldVal);

			if (isCol) colTotal--;
			else rowTotal--;
		}
	}

	static class Point {
		int r, c;
		Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode () {
			return r * 31 + c;
		}
		@Override
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point)o;
				return r == p.r && c == p.c;
			}
			return false;
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

