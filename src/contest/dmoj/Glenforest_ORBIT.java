package contest.dmoj;

import java.util.*;
import java.io.*;

public class Glenforest_ORBIT {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, Q;
	
	static Node[] tree;
	static int[] a;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		Q = readInt();

		a = new int[N+1];
		tree = new Node[4*N];
		
		for (int i = 1; i <= N; i++)
			a[i] = readInt();

		build(1, 1, N);
		
		for (int i = 0; i < Q; i++) {
			int x = readInt();
			a[x] = readInt();
			update(1, 1, N, x);
			out.println(tree[1].max);
		}
		
		
		out.close();
	}

	static void update (int n, int l, int r, int x) {
		if (l == x && x == r) {
			tree[n] = new Node(a[x], a[x], a[x], a[x]);
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(n << 1, l, mid, x);
		else
			update(n << 1 | 1, mid+1, r, x);
		tree[n] = new Node();
		tree[n].total = tree[n << 1].total | tree[n << 1 | 1].total;
		tree[n].left = Math.max(tree[n].total, Math.max(tree[n << 1].left, tree[n << 1].total | tree[n << 1 | 1].left));
		tree[n].right = Math.max(tree[n].total, Math.max(tree[n << 1 | 1].right, tree[n << 1 | 1].total | tree[n << 1].right));
		tree[n].max = Math.max(tree[n << 1 | 1].left | tree[n << 1].right, Math.max(tree[n << 1].max, tree[n << 1 | 1].max));
	}
	
	static void build (int n, int l, int r) {
		if (l == r) {
			tree[n] = new Node(a[l], a[l], a[l], a[l]);
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid+1, r);
		tree[n] = new Node();
		tree[n].total = tree[n << 1].total | tree[n << 1 | 1].total;
		tree[n].left = Math.max(tree[n].total, Math.max(tree[n << 1].left, tree[n << 1].total | tree[n << 1 | 1].left));
		tree[n].right = Math.max(tree[n].total, Math.max(tree[n << 1 | 1].right, tree[n << 1 | 1].total | tree[n << 1].right));
		tree[n].max = Math.max(tree[n << 1 | 1].left | tree[n << 1].right, Math.max(tree[n << 1].max, tree[n << 1 | 1].max));
	}
	
	static int[] getAdd (int n) {
		int[] bit = new int[32];
		for (int i = 0; i < 32; i++)
			if ((n & (1 << i)) > 0)
				bit[i] = 1;
		if (n < 0) {
			for (int i = 0; i < 32; i++)
				bit[i] = bit[i] ^ 1;
			bit[0]++;
			for (int i = 0; i < 31; i++)
				if (bit[i] == 2) {
					bit[i] = 0;
					bit[i + 1]++;
				}
		}
		return bit;
	}
	
	static class Node {
		
		int left, right, max, total;
		Node () {}
		Node (int left, int right, int max, int total) {
			this.left = left;
			this.right = right;
			this.max = max;
			this.total = total;
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

