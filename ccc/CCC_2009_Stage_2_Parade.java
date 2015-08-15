package ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2009_Stage_2_Parade {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static Node[] tree = new Node[300005];
	static Command[] commands = new Command[100005];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int q = readInt();
		for (int x = 1; x <= n; x++)
			commands[x] = new Command(readInt() - 1, readInt() - 1, readInt());
		build(1, n, 1);
		for (int x = 0; x < q; x++) {
			int i = readInt();
			Command c = new Command(readInt() - 1, readInt() - 1, readInt());
			update(i, 1, c);
			byte[][] p = tree[1].p;
			for (byte[] a : p) {
				for (byte b : a) {
					System.out.print(b + 1 + " ");
				}
				System.out.println();
			}
		}
	}

	private static void update (int x, int n, Command c) {
		if (x == tree[n].left && x == tree[n].right) {
			tree[n].p = getCommand(c);
			return;
		}
		int mid = m(tree[n].left, tree[n].right);
		if (x <= mid)
			update(x, l(n), c);
		else
			update(x, r(n), c);
		tree[n].p = product(tree[l(n)].p, tree[r(n)].p);
	}

	@SuppressWarnings ("unused")
	private static byte[][] query (int l, int r, int n) {
		// System.out.println("HERE " + l + " " + r + " " + n);
		if (tree[n].left == l && tree[n].right == r)
			return tree[n].p;
		int mid = m(tree[n].left, tree[n].right);
		if (r <= mid)
			return query(l, r, l(n));
		else if (l > mid && mid != 0)
			return query(l, r, r(n));
		else if (mid != 0)
			return product(query(l, mid, l(n)), query(mid + 1, r, r(n)));
		return null;
	}

	private static void build (int l, int r, int n) {
		tree[n] = new Node(l, r);
		if (l == r) {
			tree[n].p = getCommand(commands[l]);
			return;
		}
		int mid = m(l, r);
		build(l, mid, l(n));
		build(mid + 1, r, r(n));
		tree[n].p = product(tree[l(n)].p, tree[r(n)].p);
	}

	private static byte[][] getCommand (Command c) {
		byte[][] p = new byte[4][4];
		byte[][] newP = new byte[4][4];
		byte cnt = 0;
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++, cnt++) {
				p[x][y] = cnt;
				newP[x][y] = cnt;
			}
		for (int x = c.c + 1; x < c.c + c.k; x++)
			swap(p, newP, c.r, x - 1, c.r, x);
		for (int x = c.r + 1; x < c.r + c.k; x++)
			swap(p, newP, x - 1, c.c + c.k - 1, x, c.c + c.k - 1);
		for (int x = c.c + c.k - 2; x >= c.c; x--) {
			swap(p, newP, c.r + c.k - 1, x + 1, c.r + c.k - 1, x);
		}
		for (int x = c.r + c.k - 2; x >= c.r; x--)
			swap(p, newP, x + 1, c.c, x, c.c);
		return newP;
	}

	private static byte[][] product (byte[][] a, byte[][] b) {
		byte[][] n = new byte[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				byte curr = b[i][j];
				n[i][j] = a[curr / 4][curr % 4];
			}
		}
		return n;
	}

	private static void swap (byte[][] p, byte[][] np, int r, int c, int r2,
			int c2) {
		// byte temp = p[r][c];
		// p[r][c] = p[r2][c2];
		// p[r2][c2] = temp;
		// System.out.println(p[r][c]);
		np[r2][c2] = p[r][c];
	}

	static int l (int n) {
		return 1 << n;
	}

	static int r (int n) {
		return 1 << n | 1;
	}

	static int m (int x, int y) {
		return (x + y) / 2;
	}

	static class Command {
		int r, c, k;

		Command (int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static class Node {
		int left, right;
		byte[][] p;

		Node (int left, int right) {
			this.left = left;
			this.right = right;
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
