package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MockCCC_2015_S4_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static Pillar[] p;
	static long[] left;
	static long[] leftB;
	static long[] right;
	static long[] rightB;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		p = new Pillar[n + 2];
		left = new long[n + 2]; // use curr to break prev
		leftB = new long[n + 2]; // use prev to break curr
		right = new long[n + 2]; // use curr to break prev
		rightB = new long[n + 2]; // use prev to break curr
		for (int x = 1; x <= n; x++)
			p[x] = new Pillar(readLong(), readLong());
		p[0] = new Pillar(0, 0);
		p[n + 1] = new Pillar(0, 0);
		left[1] = p[1].d;
		leftB[1] = p[1].d;
		right[n] = p[n].d;
		rightB[n] = p[n].d;
		for (int x = 2; x <= n; x++) {
			left[x] = leftB[x - 1] - Math.min(Math.max(0, p[x - 1].d - p[x - 2].w), p[x].w) + p[x].d;
			left[x] = Math.min(left[x], left[x - 1] + p[x].d);
			leftB[x] = Math.min(left[x - 1], leftB[x - 1]) + Math.max(0, p[x].d - p[x - 1].w);
		}
		for (int x = n - 1; x >= 1; x--) {
			right[x] = rightB[x + 1] - Math.min(Math.max(0, p[x + 1].d - p[x + 2].w), p[x].w) + p[x].d;
			right[x] = Math.min(right[x], right[x + 1] + p[x].d);
			rightB[x] = Math.min(right[x + 1], rightB[x + 1]) + Math.max(0, p[x].d - p[x + 1].w);
		}
		long min = Long.MAX_VALUE;
		for (int x = 1; x <= n; x++) {
			min = Math.min(min, leftB[x] + right[x + 1] - Math.min(Math.max(0, p[x].d - p[x - 1].w), p[x + 1].w));
			min = Math.min(min, left[x - 1] + rightB[x] - Math.min(Math.max(0, p[x].d - p[x + 1].w), p[x - 1].w));
		}
		System.out.println(min);
	}

	static class Pillar {
		long d, w;

		Pillar (long d, long w) {
			this.d = d;
			this.w = w;
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
