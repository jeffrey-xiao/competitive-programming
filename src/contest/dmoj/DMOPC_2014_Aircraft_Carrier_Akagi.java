package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Aircraft_Carrier_Akagi {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[][] tree;
	static int[] maxD;
	static int[] minD;
	static int n, start;
	static int[] in;
	
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		start = readInt();
		in = new int[n+1];
		for (int i = 1; i <= n; i++)
			in[i] = readInt();
		
		tree = new int[n*4][n];
		maxD = new int[n*4];
		minD = new int[n*4];
		
		build(1, 1, n);
		
		for (int i = 1; i <= n; i++) {
			out.printf("Updating %d to %d\n", i-+(i-1)/2, i);
			update(1, 1, n, i - (i-1)/2, i, -1);
			if (i < start)
				continue;
			
		}
		out.close();
	}
	static void update (int n, int l, int r, int ql, int qr, int val) {
		if (l == ql && r == qr && maxD[n] == minD[n]) {
			maxD[n] += val;
			minD[n] += val;
			return;
		}
		if (maxD[n] == minD[n]) {
			maxD[n << 1] = maxD[n];
			maxD[n << 1 | 1] = maxD[n];
			minD[n << 1] = minD[n];
			minD[n << 1 | 1] = minD[n];
		}
		
		int mid = (r + l) >> 1;
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			update(n << 1, l, mid, ql, mid, val);
			update(n << 1 | 1, mid+1, r, mid+1, qr, val);
		}
		maxD[n] = Math.max(maxD[n << 1], maxD[n << 1 | 1]);
		minD[n] = Math.min(minD[n << 1], minD[n << 1 | 1]);
		out.printf("%d TO %d has minD : %d and maxD : %d\n", l, r, minD[n], maxD[n]);
	}
	static void build (int n, int l, int r) {
		tree[n] = new int[r - l + 2];
		if (l == r) {
			tree[n][1] = in[l];
			return;
		}
		int mid = (r + l) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid+1, r);

		int si = mid - l + 1;
		int sj = r - (mid + 1) + 1;
		int i = 1;
		int j = 1;
		for (int k = 1; k <= r - l + 1; k++) {
			if (i > si)
				tree[n][k] = tree[n << 1 | 1][j++];
			else if (j > sj)
				tree[n][k] = tree[n << 1][i++];
			else if (tree[n << 1][i] < tree[n << 1 | 1][j])
				tree[n][k] = tree[n << 1][i++];
			else
				tree[n][k] = tree[n << 1 | 1][j++];
		}
		out.println(l + " to " + r);
		for (i = 1; i <= r - l + 1; i++)
			out.print(tree[n][i] + " ");
		out.println();
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

