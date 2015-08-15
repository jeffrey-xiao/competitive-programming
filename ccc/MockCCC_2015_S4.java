package ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MockCCC_2015_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static Pillar[] p;
	static int[] left;
	static int[] right;
	static int[] leftD;
	static int[] rightD;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		p = new Pillar[n];
		left = new int[n];
		right = new int[n];
		leftD = new int[n];
		rightD = new int[n];
		for (int x = 0; x < n; x++)
			p[x] = new Pillar(readInt(), readInt());
		int total = 0;
		while (!isComplete()) {
			computeSums();
			int max = 0;
			int time = Integer.MAX_VALUE;
			int index = 0;
			for (int x = 0; x < n; x++) {
				// System.out.println(x + " " + left[x] + " " + right[x] + " " +
				// p[x].d);
				if ((leftD[x] + rightD[x] > max || (leftD[x] + rightD[x] == max && p[x].d < time))
						&& p[x].d != 0) {
					max = leftD[x] + rightD[x];
					time = p[x].d;
					index = x;
				}
			}
			// System.out.println("SELECTED " + index);
			int currWeight = p[index].w;
			for (int x = index + 1; x < n; x++) {
				if (currWeight < p[x].d) {
					p[x].d -= currWeight;
					break;
				} else {
					currWeight = p[x].w;
					p[x].w = p[x].d = 0;
				}
			}
			currWeight = p[index].w;
			for (int x = index - 1; x > 0; x--) {
				if (currWeight < p[x].d) {
					p[x].d -= currWeight;
					break;
				} else {
					currWeight = p[x].w;
					p[x].w = p[x].d = 0;
				}
			}
			total += p[index].d;
			p[index].d = p[index].w = 0;
			// for(int x = 0; x < n; x++){
			// System.out.printf("%d %d\n", p[x].d, p[x].w);
			// }
			// System.out.println();
		}
		System.out.println(total);
	}

	private static void computeSums () {
		for (int x = 0; x < n; x++) {
			leftD[x] = 0;
			rightD[x] = 0;
		}
		for (int x = 0; x < n - 1; x++) {
			if (p[x].d <= p[x + 1].w && p[x].d != 0) {
				left[x + 1] = left[x] + 1;
				leftD[x + 1] = leftD[x] + p[x].d;
			} else {
				leftD[x + 1] = p[x + 1].w;
			}
		}
		for (int x = n - 1; x > 0; x--) {
			if (p[x].d <= p[x - 1].w && p[x].d != 0) {
				right[x - 1] = right[x] + 1;
				rightD[x - 1] = rightD[x] + p[x].d;
			} else {
				rightD[x - 1] = p[x - 1].w;
			}
		}
	}

	private static boolean isComplete () {
		for (int x = 0; x < n; x++)
			if (p[x].d > 0)
				return false;
		return true;
	}

	static class Pillar {
		int d, w;

		Pillar (int d, int w) {
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
