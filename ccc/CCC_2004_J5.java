package ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_2004_J5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int level = readInt();
		int width = readInt();
		int targetx = readInt();
		ArrayList<HLine> up = new ArrayList<HLine>();
		ArrayList<HLine> down = new ArrayList<HLine>();
		ArrayList<VLine> left = new ArrayList<VLine>();
		ArrayList<VLine> right = new ArrayList<VLine>();
		ArrayList<HLine> nup;
		ArrayList<HLine> ndown;
		ArrayList<VLine> nleft;
		ArrayList<VLine> nright;
		up.add(new HLine(0, width, 1));
		for (int l = 0; l < level; l++) {
			nup = new ArrayList<HLine>();
			ndown = new ArrayList<HLine>();
			nleft = new ArrayList<VLine>();
			nright = new ArrayList<VLine>();
			for (HLine line : up) {
				int len = (line.x2 - line.x1) / 3;
				int x1 = line.x1;
				int x2 = line.x1 + len;
				int x3 = line.x2 - len;
				int x4 = line.x2;
				int y = line.y;
				nup.add(new HLine(x1, x2, y));
				nup.add(new HLine(x2, x3, y + len));
				nup.add(new HLine(x3, x4, y));
				nleft.add(new VLine(y, y + len, x2));
				nright.add(new VLine(y, y + len, x3));
			}
			for (HLine line : down) {
				int len = (line.x2 - line.x1) / 3;
				int x1 = line.x1;
				int x2 = line.x1 + len;
				int x3 = line.x2 - len;
				int x4 = line.x2;
				int y = line.y;
				ndown.add(new HLine(x1, x2, y));
				ndown.add(new HLine(x2, x3, y - len));
				ndown.add(new HLine(x3, x4, y));
				nleft.add(new VLine(y - len, y, x2));
				nright.add(new VLine(y - len, y, x3));
			}
			for (VLine line : left) {
				int len = (line.y2 - line.y1) / 3;
				int y1 = line.y1;
				int y2 = line.y1 + len;
				int y3 = line.y2 - len;
				int y4 = line.y2;
				int x = line.x;
				nleft.add(new VLine(y1, y2, x));
				nleft.add(new VLine(y2, y3, x - len));
				nleft.add(new VLine(y3, y4, x));
				nup.add(new HLine(x - len, x, y3));
				ndown.add(new HLine(x - len, x, y2));
			}
			for (VLine line : right) {
				int len = (line.y2 - line.y1) / 3;
				int y1 = line.y1;
				int y2 = line.y1 + len;
				int y3 = line.y2 - len;
				int y4 = line.y2;
				int x = line.x;
				nright.add(new VLine(y1, y2, x));
				nright.add(new VLine(y2, y3, x + len));
				nright.add(new VLine(y3, y4, x));
				nup.add(new HLine(x, x + len, y3));
				ndown.add(new HLine(x, x + len, y2));
			}
			up = new ArrayList<HLine>(nup);
			down = new ArrayList<HLine>(ndown);
			left = new ArrayList<VLine>(nleft);
			right = new ArrayList<VLine>(nright);
		}

		TreeSet<Integer> res = new TreeSet<Integer>();
		for (HLine line : up) {
			if (line.x1 <= targetx && line.x2 >= targetx)
				res.add(line.y);
		}
		for (HLine line : down) {
			if (line.x1 <= targetx && line.x2 >= targetx)
				res.add(line.y);
		}
		for (VLine line : right) {
			if (targetx == line.x)
				for (int y = line.y1; y <= line.y2; y++)
					res.add(y);
		}
		for (VLine line : left) {
			if (targetx == line.x)
				for (int y = line.y1; y <= line.y2; y++)
					res.add(y);
		}
		for (Integer y : res) {
			System.out.print(y + " ");
		}
	}

	static class HLine {
		int x1, x2, y;

		HLine (int x1, int x2, int y) {
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}
	}

	static class VLine {
		int y1, y2, x;

		VLine (int y1, int y2, int x) {
			this.y1 = y1;
			this.y2 = y2;
			this.x = x;
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
