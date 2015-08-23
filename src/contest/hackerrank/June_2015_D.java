package contest.hackerrank;

import java.util.*;
import java.io.*;

public class June_2015_D {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		String in = next();
		int q = readInt();
		ArrayList<Query> qs = new ArrayList<Query>();
		for (int i = 0; i < q; i++) {
			qs.add(new Query(readInt()-1, readInt()-1, i));
		}
		HashSet<String> unique = new HashSet<String>();
		for (int j = 0; j < in.length(); j++) {
			for (int k = j + 1; k <= in.length(); k++) {
				String ss = in.substring(j, k);
				if (search(ss, in) == 1)
					unique.add(in.substring(j, k));
			}
		}
		int[] ans = new int[q];
		int sz = (int)Math.sqrt(in.length());
		for (int i = 0; i < (in.length()-1)/sz + 1; i++) {
			int start = i*sz;
			int end = Math.min((i+1)*sz-1, in.length()-1);
//			System.out.println(start + " " + end);
			ArrayList<Query> curr = new ArrayList<Query>();
			for (Query query : qs) {
				if (start <= query.l && query.l <= end) {
					curr.add(query);
				}
			}
			Collections.sort(curr);
			int l = -1, r = -1, cnt = 0;
			for (Query query : curr) {
				if (l == -1 && r == -1) {
					l = query.l;
					r = query.r;
					for (int j = l; j <= r; j++) {
						for (int k = j + 1; k <= r+1; k++) {
							String ss = in.substring(j, k);
							if (unique.contains(ss))
								cnt++;
						}
					}
				}
				while (r < query.r) {
					r++;
					for (int j = l; j <= r; j++)
						if (unique.contains(in.substring(j, r+1)))
							cnt++;
				}
				while (l < query.l) {
					for (int j = l; j <= r; j++)
						if (unique.contains(in.substring(l, j+1)))
							cnt--;
					l++;
				}
				while (l > query.l) {
					l--;
					for (int j = l; j <= r; j++)
						if (search(in.substring(l, j+1), in) == 1)
							cnt++;
				}
				ans[query.i] = cnt;
			}
		}
		for (int i = 0; i < q; i++)
			pr.println(ans[i]);
		
		pr.close();
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
	private static int[] computeLSPTable(String s){
		int[] LSP = new int[s.length()];
		LSP[0] = 0;
		for(int x = 1; x < s.length(); x++){
			// First assume that we are extending the previous LSP
			int y = LSP[x - 1];
			// If there is a mismatch, go to previous LSP
			while(y > 0 && s.charAt(y) != s.charAt(x))
				y = LSP[y - 1];
			if(s.charAt(y) == s.charAt(x))
				y++;
			LSP[x] = y;
		}
		return LSP;
	}
	private static int search(String pattern, String text){
		int[] LSP = computeLSPTable(pattern);
		int y = 0;
		int cnt = 0;
		for(int x = 0; x < text.length(); x++){
			while(y > 0 && text.charAt(x) != pattern.charAt(y))
				y = LSP[y - 1];
			if(text.charAt(x) == pattern.charAt(y)){
				y++;
				if(y == pattern.length()) {
					cnt++;
					y = LSP[y-1];
				}
			}
		}
		return cnt;
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

