package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class June_2015_D {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int sz = 0;
	static Integer[] sa;
	static int[] order;
	static int[] newOrder;
	static String in;
	static String s;
	static SuffixComparator sc = new SuffixComparator();
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		in = next();
		int q = readInt();
		ArrayList<Query> qs = new ArrayList<Query>();
		for (int i = 0; i < q; i++)
			qs.add(new Query(readInt() - 1, readInt() - 1, i));

		int[] end1 = getEnd(in);
		int[] end2 = getEnd(new StringBuilder(in).reverse().toString());

		long[] ans = new long[q];
		int sz = (int) Math.sqrt(in.length());
		for (int i = 0; i < (in.length() - 1) / sz + 1; i++) {
			int start = i * sz;
			int end = Math.min((i + 1) * sz - 1, in.length() - 1);

			ArrayList<Query> curr = new ArrayList<Query>();
			for (Query query : qs)
				if (start <= query.l && query.l <= end)
					curr.add(query);

			Collections.sort(curr);
			int l = -1, r = -1;
			long cnt = 0;
			for (Query query : curr) {
				if (l == -1 && r == -1) {
					l = query.l;
					r = query.r;
					for (int j = l; j <= r; j++) 
						cnt += Math.max(0, r - j + 1 - (end1[j]));
				}
				while (r < query.r) {
					r++;
					cnt += Math.max(0, r - l + 1 - end2[in.length() - r - 1]);
				}
				while (l < query.l) {
					cnt -= Math.max(0, r - l + 1 - end1[l]);
					l++;
				}
				while (l > query.l) {
					l--;
					cnt += Math.max(0, r - l + 1 - end1[l]);
				}
				ans[query.i] = cnt;
			}
		}
		for (int i = 0; i < q; i++)
			pr.println(ans[i]);

		pr.close();
	}
	static int[] getEnd (String s1) {
		s = s1;
		sa = new Integer[s.length()];
		order = new int[s.length()];
		newOrder = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			sa[i] = i;
			order[i] = (int)(s.charAt(i));
			newOrder[i] = 0;
		}

		for (sz = 1; ; sz <<= 1) {
			Arrays.sort(sa, sc);
			for (int i = 0; i < s.length() - 1; i++) {
				newOrder[i+1] = newOrder[i] + (sc.compare(sa[i], sa[i+1]) < 0 ? 1 : 0);
			}
			for (int i = 0; i < s.length(); i++) {
				order[sa[i]] = newOrder[i];
			}
			if (newOrder[s.length()-1] == s.length() - 1)
				break;
		}
		int[] lcp = new int[s.length()];
		int k = 0;
		for (int i = 0; i < s.length(); i++, k = k > 0 ? k - 1 : k) {
			if (order[i] == s.length() - 1) {
				lcp[i] = 0;
				continue;
			}
			int j = sa[order[i] + 1];
			while (j + k < s.length() && i + k < s.length() && s.charAt(j+k) == s.charAt(i+k))
				k++;
			lcp[i] = k;
		}
		int[] end = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (order[i] == 0)
				end[i] = lcp[i];
			else
				end[i] = Math.max(lcp[sa[order[i]-1]], lcp[i]);
		}
		return end;
	}
	static class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer o1, Integer o2) {
			if (order[o1] != order[o2])
				return order[o1] - order[o2];
			if ((o1 += sz) < s.length() & (o2 += sz) < s.length())
				return order[o1] - order[o2];
			return o2 - o1;
		}
	}
	static class Query implements Comparable<Query> {
		int l, r, i;

		Query (int l, int r, int i) {
			this.l = l;
			this.r = r;
			this.i = i;
		}

		@Override
		public int compareTo (Query o) {
			return r - o.r;
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
