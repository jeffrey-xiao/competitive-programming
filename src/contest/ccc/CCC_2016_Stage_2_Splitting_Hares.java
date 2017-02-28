package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_Stage_2_Splitting_Hares {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] x, y, w;
	static final double EPS = 1e-8;
	static final Comparator<Integer> pointComparator = new Comparator<Integer>() {
		@Override
		// small positive < big positive < big negative < small negative
		public int compare (Integer i, Integer j) {
			Double slope1 = 0.0;
			Double slope2 = 0.0;

			if (x[i] != 0)
				slope1 = (double)(y[i]) / x[i];
			if (x[j] != 0)
				slope2 = (double)(y[j]) / x[j];

			if (slope1 * slope2 > 0)
				return slope1.compareTo(slope2);

			if (slope1 < 0)
				return 1;
			if (slope1 > 0)
				return -1;

			if (slope2 < 0)
				return -1;
			if (slope2 > 0)
				return 1;
			return 0;
		}
	};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		x = new int[N];
		y = new int[N];
		w = new int[N];

		for (int i = 0; i < N; i++) {
			x[i] = readInt();
			y[i] = readInt();
			w[i] = readInt();
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			int currX = x[i];
			int currY = y[i];

			for (int j = 0; j < N; j++) {
				x[j] -= currX;
				y[j] -= currY;
			}

			ArrayList<Integer> plane1 = new ArrayList<Integer>();
			ArrayList<Integer> plane2 = new ArrayList<Integer>();

			int w1 = 0, w2 = 0, prefix = 0, suffix = 0;

			/// don't include the current point in weight calculations until the end
			for (int j = 0; j < N; j++) {
				if (y[j] < 0) {
					plane1.add(j);
					w1 += w[j];
				} else if (y[j] > 0) {
					plane2.add(j);
					w2 += w[j];
				} else if (x[j] < 0) {
					suffix += w[j];
				} else if (x[j] > 0) {
					prefix += w[j];
				}
			}

			Collections.sort(plane1, pointComparator);
			Collections.sort(plane2, pointComparator);

			ans = Math.max(ans, Math.max(w1 + prefix + suffix + w[i], w2 + prefix + suffix + w[i]));

			ans = Math.max(ans, Math.max(w1 + suffix + w[i], w2 + prefix + w[i]));
			ans = Math.max(ans, Math.max(w1 + suffix, w2 + prefix + w[i]));
			ans = Math.max(ans, Math.max(w1 + suffix + w[i], w2 + prefix));

			ans = Math.max(ans, Math.max(w1 + prefix + w[i], w2 + suffix + w[i]));
			ans = Math.max(ans, Math.max(w1 + prefix, w2 + suffix + w[i]));
			ans = Math.max(ans, Math.max(w1 + prefix + w[i], w2 + suffix));

			ans = Math.max(ans, calc(plane1, plane2, w1, w2, prefix, suffix, i));

			for (int j = 0; j < N; j++) {
				x[j] += currX;
				y[j] += currY;
			}
		}

		out.println(ans);
		out.close();
	}

	static int calc (ArrayList<Integer> plane1, ArrayList<Integer> plane2, int w1, int w2, int prefix, int suffix, int i) {
		int index1 = 0;
		int index2 = 0;
		int ret = 0;

		while (index2 < plane2.size()) {
			int curr = plane2.get(index2);
			w1 += prefix;
			w2 += suffix;
			prefix = 0;
			suffix = 0;

			while (index2 < plane2.size() && pointComparator.compare(curr, plane2.get(index2)) == 0) {
				prefix += w[plane2.get(index2)];
				w2 -= w[plane2.get(index2)];
				index2++;
			}
			while (index1 < plane1.size() && ccw(curr, i, plane1.get(index1)) < 0) {
				w2 += w[plane1.get(index1)];
				w1 -= w[plane1.get(index1)];
				index1++;
			}

			while (index1 < plane1.size() && pointComparator.compare(curr, plane1.get(index1)) == 0) {
				suffix += w[plane1.get(index1)];
				w1 -= w[plane1.get(index1)];
				index1++;
			}

			ret = Math.max(ret, Math.max(w1 + prefix + suffix + w[i], w2 + prefix + suffix + w[i]));

			ret = Math.max(ret, Math.max(w1 + suffix + w[i], w2 + prefix + w[i]));
			ret = Math.max(ret, Math.max(w1 + suffix, w2 + prefix + w[i]));
			ret = Math.max(ret, Math.max(w1 + suffix + w[i], w2 + prefix));

			ret = Math.max(ret, Math.max(w1 + prefix + w[i], w2 + suffix + w[i]));
			ret = Math.max(ret, Math.max(w1 + prefix, w2 + suffix + w[i]));
			ret = Math.max(ret, Math.max(w1 + prefix + w[i], w2 + suffix));
		}

		return ret;
	}

	static long cross (long x0, long y0, long x1, long y1) {
		return x0 * y1 - x1 * y0;
	}

	static long ccw (int p1, int p2, int p3) {
		return cross(x[p2] - x[p1], y[p2] - y[p1], x[p3] - x[p1], y[p3] - y[p1]);
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
