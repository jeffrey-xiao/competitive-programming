package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2007_BARICA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(readInt(), readInt(), readInt(), i);
		}
		int[] maxX = new int[300001];
		int[] maxY = new int[300001];
		int[] indexX = new int[300001];
		int[] indexY = new int[300001];
		for (int i = 0; i <= 300000; i++)
			maxX[i] = maxY[i] = indexX[i] = indexY[i] = -1 << 20;
		Arrays.sort(p);
		int[] prev = new int[n];
		for (int i = 0; i < n; i++) {
			prev[i] = -1;
			if (p[i].i == 0) {
				maxX[p[i].x] = p[i].e;
				maxY[p[i].y] = p[i].e;
				indexX[p[i].x] = i;
				indexY[p[i].y] = i;
			} else {
				int max = -1;
				if (maxX[p[i].x] >= k && maxX[p[i].x] + p[i].e - k > max) {
					max = maxX[p[i].x] + p[i].e - k;
					prev[i] = indexX[p[i].x];
				}
				if (maxY[p[i].y] >= k && maxY[p[i].y] + p[i].e - k > max) {
					max = maxY[p[i].y] + p[i].e - k;
					prev[i] = indexY[p[i].y];
				}
				if (maxX[p[i].x] < max) {
					maxX[p[i].x] = max;
					indexX[p[i].x] = i;
				}
				if (maxY[p[i].y] < max) {
					maxY[p[i].y] = max;
					indexY[p[i].y] = i;
				}
				if (p[i].i == n - 1) {
					System.out.println(max);
					Stack<Point> out = new Stack<Point>();
					int curr = i;
					while (curr != -1) {
						out.push(p[curr]);
						curr = prev[curr];
					}
					System.out.println(out.size());
					while (!out.isEmpty()) {
						Point c = out.pop();
						System.out.println(c.x + " " + c.y);
					}
				}
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x, y, e, i;

		Point (int x, int y, int e, int i) {
			this.x = x;
			this.y = y;
			this.e = e;
			this.i = i;
		}

		@Override
		public int compareTo (Point o) {
			if (x == o.x)
				return y - o.y;
			return x - o.x;
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
