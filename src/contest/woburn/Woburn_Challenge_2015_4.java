package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++)
			p[i] = new Point(readInt(), readInt());
		int ans = 0;
		int currX = 1;
		int maxY = 1;
		int[] max = new int[40001], min = new int[40001];
		for (int i = 0; i <= 40000; i++) {
			max[i] = - 1 << 30;
			min[i] = 1 << 30;
		}
		min[1] = 1;
		max[1] = 1;
		for (int k = 0; k < n; k++) {
			toAdd = 0;
			System.out.println(k);
			for (int i = k; i <= k; i++) {
				maxY = Math.max(maxY, p[i].x);
				// even is min
				// odd is max
				if (p[i].x % 2 == 0) {
					max[p[i].x - 1] = max(max[p[i].x - 1], p[i].y, true);
					min[p[i].x] = min(min[p[i].x], p[i].y, true);
					max[p[i].x] = max(max[p[i].x], p[i].y, true);
					min[p[i].x + 1] = min(min[p[i].x + 1], p[i].y, true);
				} else {
					min[p[i].x - 1] = min(min[p[i].x - 1], p[i].y, true);
					min[p[i].x] = min(min[p[i].x], p[i].y, false);
					max[p[i].x] = max(max[p[i].x], p[i].y, true);
					max[p[i].x + 1] = max(max[p[i].x + 1], p[i].y, false);
				}
			}
			if (k == 0) {
			for (int i = 1; i <= 6; i++) {
				if (i % 2 == 0) {
					if (min[i] == 1 << 30)
						;
					else {
						if (min[i] < currX) {
							ans += (currX - min[i]);
							currX = min[i];
						}
					}
						min[i] = Math.min(min[i], Math.min(min[i+1], min[i]));
						max[i] = Math.max(max[i], Math.max(max[i-1], max[i]));
				} else {
					if (max[i] == - 1 << 30)
						;
					else {
						if (max[i] > currX) {
							ans += (max[i] - currX);
							currX = max[i];
						}
					}
						min[i] = Math.min(min[i], Math.min(min[i-1], min[i]));
						max[i] = Math.max(max[i], Math.max(max[i+1], max[i]));
				}
			}
				for (int i = 6; i >= 1; i--) {
					if (i % 2 == 0) {
							min[i] = Math.min(min[i], Math.min(min[i+1], min[i]));
							max[i] = Math.max(max[i], Math.max(max[i-1], max[i]));
					} else {
							min[i] = Math.min(min[i], Math.min(min[i-1], min[i]));
							max[i] = Math.max(max[i], Math.max(max[i+1], max[i]));
					}
				}
				for (int i =1; i <= 6; i++) {

					System.out.println(i + " PRINTING " + min[i] + " " + max[i]);
				}
			}
			if (k == 0)
				out.println(ans + maxY - 1);
			else
				out.println((ans += toAdd) + maxY - 1);
		}
		out.close();
	}
	static int toAdd = 0;
	static int min (int a, int b, boolean add) {
		System.out.println("min " + a + " " + b + " " + add);
		if (a == 1 << 30)
			return b;
		if (add)
		toAdd += Math.max(a - b, 0);
		return Math.min(a, b);
	}
	static int max (int a, int b, boolean add) {
		System.out.println("max " + a + " " + b + " " + add);
		if (a == -1 << 30)
			return b;
		if (add)
		toAdd += Math.max(b - a, 0);
		return Math.max(a, b);
	}
	
	static int add (int val) {
		if (val > 1 << 29)
			return 0;
		return val;
	}
	
	static class Point{
		int x, y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
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

