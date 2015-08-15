package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Connected_Components {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		WeightedQuickUnion q = new WeightedQuickUnion(n);
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (readInt() == 1)
					if (!q.connected(x, y)) {
						q.union(x, y);
					}
			}
		}
		Arrays.sort(q.id, new Comparator<Point>() {

			@Override
			public int compare (Point arg0, Point arg1) {
				if (arg0.id == arg1.id)
					return arg0.index - arg1.index;
				return arg0.id - arg1.id;
			}

		});
		int curr = q.id[0].id;
		for (int x = 0; x < q.id.length; x++) {
			if (q.id[x].id != curr) {
				curr = q.id[x].id;
				System.out.println();
			}
			System.out.print(q.id[x].index + 1 + " ");
		}
	}

	static class Point {
		int index;
		int id;

		Point (int index, int id) {
			this.index = index;
			this.id = id;
		}
	}

	static class WeightedQuickUnion {
		private Point[] id;
		private int[] size;
		private int count;

		public WeightedQuickUnion (int n) {
			id = new Point[n];
			size = new int[n];
			count = n;
			for (int x = 0; x < n; x++) {
				id[x] = new Point(x, x);
				size[x] = 1;
			}
		}

		public int find (int i) {
			while (i != id[i].id) {
				id[i].id = id[id[i].id].id;
				i = id[i].id;
			}
			return i;
		}

		public boolean connected (int x, int y) {
			return find(x) == find(y);
		}

		public int count () {
			return count;
		}

		public void union (int x, int y) {
			int rootx = find(x);
			int rooty = find(y);
			if (rootx == rooty)
				return;
			count--;
			if (size[rootx] < size[rooty]) {
				id[rootx].id = id[rooty].id;
				size[rooty] += size[rootx];
			} else {
				id[rooty].id = id[rootx].id;
				size[rootx] += size[rooty];
			}
		}

		public void print () {
			for (Point i : id)
				System.out.print(i.id + " ");
			System.out.println();
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

	public Connected_Components () {
	}
}
