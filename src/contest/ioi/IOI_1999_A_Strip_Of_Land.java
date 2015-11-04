package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_1999_A_Strip_Of_Land {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int r, c, h;
	static int[][][] max, min;
	static int lc;
	static ArrayDeque<State> minq = new ArrayDeque<State>(), maxq = new ArrayDeque<State>();
	
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		c = readInt();
		r = readInt();
		h = readInt();
		lc = Math.min(1 + (int)(Math.log(c) / Math.log(2)), 1 + (int)(Math.log(100)/Math.log(2)));
		max = new int[r+1][c+1][lc];
		min = new int[r+1][c+1][lc];
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++) {
				max[i][j][0] = min[i][j][0] = readInt();
			}
		for (int i = 1; i <= r; i++) 
			for (int l = 1; l < lc; l++) 
				for (int j = 1; j + (1 << l) - 1 <= c; j++)  {
					max[i][j][l] = Math.max(max[i][j][l-1], max[i][j + (1 << (l - 1))][l-1]);
					min[i][j][l] = Math.min(min[i][j][l-1], min[i][j + (1 << (l - 1))][l-1]);
				}
		int max = 0;
		int lor = 0, hir = 0, loc = 0, hic = 0;
		for (int i = 1; i <= c; i++) {
			for (int j = i; j <= Math.min(c, i + 99); j++) {
				int lo = 1, hi = 1;
				minq.clear();
				maxq.clear();
				while (hi <= r) {
					int maxV = getMax(i, j, hi);
					int minV = getMin(i, j, hi);
					while (!minq.isEmpty() && minq.getLast().val >= minV)
						minq.removeLast();
					if (!minq.isEmpty() && minq.getFirst().pos < lo)
						minq.removeFirst();
					minq.addLast(new State(minV, hi));
				
					while (!maxq.isEmpty() && maxq.getLast().val <= maxV)
						maxq.removeLast();
					if (!maxq.isEmpty() && maxq.getFirst().pos < lo)
						maxq.removeFirst();
					maxq.addLast(new State(maxV, hi));
					if (maxq.getFirst().val - minq.getFirst().val <= h) {
						if ((hi - lo + 1) * (j - i + 1) > max) {
							max = (hi - lo + 1) * (j - i + 1);
							loc = i;
							hic = j;
							lor = r - hi + 1;
							hir = r - lo + 1;
						}
						hi++;
					} else {
						hi++;
						lo++;
					}
				}
			}
		}
//		out.println(max);
		out.printf("%d %d %d %d\n", loc, lor, hic, hir);
		out.close();
	}

	static class State {
		int val, pos;
		State (int val, int pos) {
			this.val = val;
			this.pos = pos;
		}
	}
	
	static int getMax (int c1, int c2, int r) {
		int sz = (int)(Math.log(c2 - c1 + 1) / Math.log(2));
		return Math.max(max[r][c1][sz], max[r][c2 - (1 << sz) + 1][sz]);
	}
	static int getMin (int c1, int c2, int r) {
		int sz = (int)(Math.log(c2 - c1 + 1) / Math.log(2));
		return Math.min(min[r][c1][sz], min[r][c2 - (1 << sz) + 1][sz]);
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

