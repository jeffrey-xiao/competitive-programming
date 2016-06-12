package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2013_SALASTICAR {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;

	static int sz;
	static Integer[] res;
	static int[] order, newOrder;
	static char[] input;

	static int[] ans, min, toIndex, sum;
	static State[] states;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		input = next().toCharArray();
		res = new Integer[N];
		order = new int[N];
		newOrder = new int[N];
		toIndex = new int[N];
		min = new int[4 * N];
		sum = new int[4 * N];
		
		computeSuffixArray();
		minBuild(1, 0, N - 1);

		for (int i = 0; i < N; i++)
			toIndex[res[i]] = i;
		
		M = readInt();

		ans = new int[M];
		states = new State[M];

		
		
		for (int i = 0; i < M; i++) {
			String code = next();
			states[i] = new State(code, getFirstSuffix(code), i);

			assert(getFirstSuffix(code) == bruteforceFirstSuffix(code));
		}
		
		Arrays.sort(states);
		
		int curr = 0;
		
		for (State s : states) {
			while (curr <= Math.min(s.firstMatch, N - 1)) {
				sumUpdate(1, 0, N - 1, toIndex[curr], 1);
				curr++;
			}
			ans[s.index] = getAnswer(s.code) + s.firstMatch;
		}

		for (int i = 0; i < M; i++)
			out.println(ans[i]);
		
		out.close();
	}

	static int bruteforceFirstSuffix (String s) {
		String in = new String(input);
		for (int i = 0; i < in.length() - s.length(); i++)
			if (in.substring(i, i + s.length()).equals(s))
				return i;
		return N;
	}
	
	static int getAnswer (String code) {
		int ret = 0;
		int lo = 0;
		int hi = N - 1;
		for (int i = 0; i < code.length(); i++) {
			int nlo = bsearchCeil(lo, hi, code.charAt(i), i);
			int nhi = bsearchFloor(lo, hi, code.charAt(i), i);

			if (nlo > nhi)
				return ret;
			lo = nlo;
			hi = nhi;
			ret += sumQuery(1, 0, N - 1, nlo, nhi);
		}
		return ret;
	}
	
	static int getFirstSuffix (String code) {
		int lo = 0;
		int hi = N - 1;
		for (int i = 0; i < code.length(); i++) {
			int nlo = bsearchCeil(lo, hi, code.charAt(i), i);
			int nhi = bsearchFloor(lo, hi, code.charAt(i), i);

			if (nlo > nhi)
				return N;
			lo = nlo;
			hi = nhi;
		}
		return minQuery(1, 0, N - 1, lo, hi);
	}

	public static int bsearchCeil (int lo, int hi, char val, int pos) {
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (res[mid] + pos >= N || input[res[mid] + pos] < val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}
	
	public static int bsearchFloor (int lo, int hi, char val, int pos) {
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (res[mid] + pos >= N || input[res[mid] + pos] <= val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return hi;
	}

	static void sumUpdate (int n, int l, int r, int x, int val) {
		if (l == x && x == r) {
			sum[n] += val;
			return;
		}
		
		int mid = (l + r) >> 1;
			
		if (x <= mid)
			sumUpdate(n << 1, l, mid, x, val);
		else
			sumUpdate(n << 1 | 1, mid + 1, r, x, val);
		
		sum[n] = sum[n << 1] + sum[n << 1 | 1];
	}
	
	static int sumQuery (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return sum[n];
		
		int mid = (l + r) >> 1;

		if (qr <= mid)
			return sumQuery(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return sumQuery(n << 1 | 1, mid + 1, r, ql, qr);
		else
			return sumQuery(n << 1, l, mid, ql, mid) + sumQuery(n << 1 | 1, mid + 1, r, mid + 1, qr);
	}
	
	static int minQuery (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return min[n];
		
		int mid = (l + r) >> 1;

		if (qr <= mid)
			return minQuery(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return minQuery(n << 1 | 1, mid + 1, r, ql, qr);
		else
			return Math.min(minQuery(n << 1, l, mid, ql, mid), minQuery(n << 1 | 1, mid + 1, r, mid + 1, qr));
	}

	static void minBuild (int n, int l, int r) {
		if (l == r) {
			min[n] = res[l];
			return;
		}
		int mid = (l + r) >> 1;
		minBuild(n << 1, l, mid);
		minBuild(n << 1 | 1, mid + 1, r);
		min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
	}

	static class State implements Comparable<State> {
		String code;
		int firstMatch, index;

		State (String code, int firstMatch, int index) {
			this.code = code;
			this.firstMatch = firstMatch;
			this.index = index;
		}

		@Override
		public int compareTo (State s) {
			return firstMatch - s.firstMatch;
		}
	}

	static Integer[] computeSuffixArray () {
		for (int i = 0; i < N; i++) {
			res[i] = i;
			order[i] = (int) (input[i] - '0');
			newOrder[i] = 0;
		}

		final SuffixComparator C = new SuffixComparator(); 

		for (sz = 1;; sz <<= 1) {
			Arrays.sort(res, C);
			for (int i = 0; i < N - 1; i++)
				newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i + 1]) < 0 ? 1 : 0);
			for (int i = 0; i < N; i++)
				order[res[i]] = newOrder[i];
			if (newOrder[N - 1] == N - 1)
				break;
		}
		return res;
	}

	static class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer o1, Integer o2) {
			if (order[o1] != order[o2])
				return order[o1] - order[o2];
			if ((o1 += sz) < N & (o2 += sz) < N)
				return order[o1] - order[o2];
			return o2 - o1;
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

