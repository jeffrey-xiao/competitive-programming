package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2013_Game {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		pr.close();
	}
	static void add1 (int x, int y, int val) {
		root = add1(root, x, y, val);
	}
	static OuterNode add1 (OuterNode n, int x, int y, int val) {
		if (n == null) {
			return new OuterNode(x);
		}
		if (x < n.x)
			n.left = add1(n.left, x, y, val);
		else if (x > n.x)
			n.right = add1(n.right, x, y, val);
		else {
			
		}
		int dh1 = getHeight(n.left) - getHeight(n.right);
		if (dh1 >= 2) {
			int dh2 = getHeight(n.left.left) - getHeight(n.left.right);
			if (dh2 <= -1)
				n.left = rotateLeft(n.left);
			n = rotateRight(n);
		} else if (dh1 <= -2) {
			int dh2 = getHeight(n.right.left) - getHeight(n.right.right);
			if (dh2 >= 1)
				n.right = rotateRight(n.right);
			n = rotateLeft(n);
		}
	}
	
	static OuterNode root;
	static class OuterNode {
		int x, height;
		InnerNode root;
		OuterNode left, right;
		OuterNode (int x) {
			this.x = x;
			this.height = 1;
		}
	}
	static class InnerNode {
		int y, gcf, height;
		InnerNode left, right;
		InnerNode (int y) {
			this.y = y;
			this.height = 0;
		}
	}
	static InnerNode rotateRight (InnerNode n) {
		InnerNode x = n.left;
		n.left = x.right;
		x.right = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}
	static InnerNode rotateLeft (InnerNode n) {
		InnerNode x = n.right;
		n.right = x.left;
		x.left = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}
	static void resetHeight (InnerNode n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right))+1;
	}
	static int getHeight (InnerNode n) {
		return n == null ? -1 : n.height;
	}
	
	static OuterNode rotateRight (OuterNode n) {
		OuterNode x = n.left;
		n.left = x.right;
		x.right = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}
	static OuterNode rotateLeft (OuterNode n) {
		OuterNode x = n.right;
		n.right = x.left;
		x.left = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}
	static void resetHeight (OuterNode n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right))+1;
	}
	static int getHeight (OuterNode n) {
		return n == null ? -1 : n.height;
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

