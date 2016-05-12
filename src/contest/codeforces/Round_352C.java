package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_352C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static ArrayList<ArrayDeque<Integer>> divisors = new ArrayList<ArrayDeque<Integer>>();

	static int[] val, occ;
	static int[] sum, max, lazy;

	// keep track of first two occ and last two
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		sum = new int[4*N];
		lazy = new int[4*N];
		max = new int[4*N];

		val = new int[N + 1];
		occ = new int[200001];

		for (int i = 0; i <= 200000; i++)
			divisors.add(new ArrayDeque<Integer>());

		for (int i = 1; i <= N; i++) {
			val[i] = readInt();
			int x = val[i];
			for (int j = 1; j * j <= x; j++) {
				if (x % j == 0) {
					divisors.get(j).add(i);
					if (divisors.get(j).size() == 3)
						divisors.get(j).pollFirst();
					if (x/ j != j) {
						divisors.get(x / j).add(i);
						if (divisors.get(x / j).size() == 3)
							divisors.get(x / j).pollFirst();
					}
				}
			}
		}

		for (int i = 1; i <= 200000; i++) {
			if (divisors.get(i).size() == 2) {
				int pos = divisors.get(i).peekFirst() - 1;
				if (pos >= 1) {
					update(1, 1, N, 1, pos, i);
					System.out.printf("update %d %d with %d\n", 1, pos, i);
				}
			}
		}

		int ans = 0;
		
		for (int i = 1; i <= N; i++) {
			if (i != 1) {
				occ[val[i - 1]]++;
				System.out.println(val[i - 1] + " " + occ[val[i - 1]]);
				if (occ[val[i - 1]] == 1) {
					int pos = divisors.get(val[i - 1]).peekLast() - 1;
					if (pos >= i) {
						int lo = i;
						int hi = pos;
						
						while (lo <= hi) {
							int mid = (lo + hi) >> 1;
							if (query(1, 1, N, mid, mid) > val[i - 1])
								lo = mid + 1;
							else
								hi = mid - 1;
						}
						System.out.printf("UPDATING %d to %d with %d\n", i, pos, val[i - 1]);
						if (lo <= pos)
							update(1, 1, N, lo, pos, val[i - 1]);
					}
				} else {
					int lo = i;
					int hi = N;
					
					while (lo <= hi) {
						int mid = (lo + hi) >> 1;
						if (query(1, 1, N, mid, mid) > val[i - 1])
							lo = mid + 1;
						else
							hi = mid - 1;
					}
					if (lo <= N)
						update(1, 1, N, lo, N, val[i - 1]);
				}
			}
			
			ans += query(1, 1, N, i, N);
			for (int j = 1; j <= N; j++)
				System.out.printf("%d ", query(1, 1, N, j, j));
			System.out.println();
		}
		out.println(ans);
		out.close();
	}

	static int query (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return sum[n];
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			max[n << 1] = max[n << 1 | 1] = lazy[n];
			sum[n << 1] = lazy[n] * (mid - l + 1);
			sum[n << 1 | 1] = lazy[n] * (r - (mid + 1) + 1);
			lazy[n] = 0;
		}
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr);
		else
			return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid + 1, r, mid + 1, qr);
	}
	
	static void update (int n, int l, int r, int ql, int qr, int val) {
		if (l == ql && r == qr) {
			max[n] = val;
			lazy[n] = val;
			sum[n] = val * (r - l + 1);
			return;
		}
		
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			max[n << 1] = max[n << 1 | 1] = lazy[n];
			sum[n << 1] = lazy[n] * (mid - l + 1);
			sum[n << 1 | 1] = lazy[n] * (r - (mid + 1) + 1);
			lazy[n] = 0;
		}
		
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			update(n << 1, l, mid, ql, mid, val);
			update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		
		sum[n] = sum[n << 1] + sum[n << 1 | 1];
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

