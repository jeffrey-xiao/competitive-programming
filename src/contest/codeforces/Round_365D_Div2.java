package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_365D_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int[] val, prefix, bit, ans;
	static Query[] queries;
	static HashMap<Integer, Integer> last;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		last = new HashMap<Integer, Integer>();
		val = new int[N + 1];
		prefix = new int[N + 1];
		bit = new int[N + 1];
		
		for (int i = 1; i <= N; i++)
			prefix[i] = prefix[i - 1] ^ (val[i] = readInt());
		
		M = readInt();
		
		queries = new Query[M];
		ans = new int[M];
		
		for (int i = 0; i < M; i++)
			queries[i] = new Query(readInt(), readInt(), i);
		
		Arrays.sort(queries);
		
		int index = 1;
		
		for (int i = 0; i < M; i++) {
			while (index <= queries[i].r) {
				update(index, val[index]);
				if (last.get(val[index]) != null)
					update(last.get(val[index]), val[index]);
				last.put(val[index], index);
				index++;
			}
			
			ans[queries[i].index] = prefix[queries[i].r] ^ prefix[queries[i].l - 1] ^
									query(queries[i].r) ^ query(queries[i].l - 1);
		}

		for (int i = 0; i < M; i++)
			out.println(ans[i]);
		
		out.close();
		
	}

	static void update (int x, int val) {
		for (int i = x; i <= N; i += (i & -i))
			bit[i] ^= val;
	}
	
	static int query (int x) {
		int ret = 0;
		for (int i = x; i > 0; i -= (i & -i))
			ret ^= bit[i];
		return ret;
	}
	
	static class Query implements Comparable<Query> {
		int l, r, index;
		
		Query (int l, int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}

		@Override
		public int compareTo (Query q) {
			return r - q.r;
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

