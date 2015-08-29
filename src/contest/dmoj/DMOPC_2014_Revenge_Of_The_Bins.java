package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Revenge_Of_The_Bins {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	static int n;
	static int[] a;
	static Node[] tree = new Node[100005*3];
	public static void main (String[] args) throws IOException {
		n = readInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		int ans = 0;
//		int[] count = new int[n];
		build(1, n, 1);
		for (int i = 0; i < n/2; i++) {
			update(1, a[i], 1, 2);
			update(1, a[i*2], 1, -1);
			update(1, a[i*2+1], 1, -1);
			
//			for (int k = 0; k < a[i]; k++)
//				count[k] += 2;
//			for (int k = 0; k < a[i*2]; k++)
//				count[k]--;
//			for (int k = 0; k < a[i*2+1]; k++)
//				count[k]--;
			int minimum = tree[1].min;
//			int minimum = Integer.MAX_VALUE;
//			for (int j = 0; j < n; j++) {
//				minimum = Math.min(minimum, count[j]);
//			}
			if (minimum < 0)
				continue;
			ans = i+1;
		}
		System.out.println(ans);
	}
	static void update (int l, int r, int n, int v) {
		if (tree[n].l == l && tree[n].r == r) {
			tree[n].p += v;
			tree[n].min += v;
			return;
		}
		if (tree[n].p != 0) {
			tree[2*n].p += tree[n].p;
			tree[2*n+1].p += tree[n].p;
			tree[2*n].min += tree[n].p;
			tree[2*n+1].min += tree[n].p;
			tree[n].p = 0;
		}
		int mid = (tree[n].l + tree[n].r)/2;
		if (r <= mid)
			update(l, r, 2*n, v);
		else if (l > mid)
			update(l, r, 2*n+1, v);
		else {
			update(l, mid, 2*n, v);
			update(mid+1, r, 2*n+1, v);
		}
		tree[n].min = Math.min(tree[2*n].min, tree[2*n+1].min);
	}
	
	static void build (int l, int r, int n) {
		tree[n] = new Node(l, r);
		if (l == r)
			return;
		int mid = (l+r)/2;
		build(l, mid, 2*n);
		build(mid+1, r, 2*n+1);
	}
	
	static class Node {
		int l, r, min, p;
		Node (int l, int r) {
			this.l = l;
			this.r = r;
		}
	}
	
	private static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	private static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
}