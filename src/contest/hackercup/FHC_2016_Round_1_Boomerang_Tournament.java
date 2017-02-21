package contest.hackercup;
import java.util.*;
import java.io.*;

public class FHC_2016_Round_1_Boomerang_Tournament {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[][] adj;
	static int[] loseFirst;
	static int[] best;
	
	static int[] place = {0, 0, 1, 2, 2, 4, 4, 4, 4, 8, 8, 8, 8, 8, 8, 8, 8};
	static int[] places = {1, 2, 3, 5, 9};
	static HashSet<Integer> hs1 = new HashSet<Integer>();
	static HashSet<Integer> hs2 = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));
	
		int T = readInt();
		for (int t = 1; t <= T; t++) {
			dp.clear();
			N = readInt();
			adj = new int[N][N];
			loseFirst = new int[N];
			best = new int[N];

			for (int i = 0; i < N; i++) {
				best[i] = N / 2 + 1;
				loseFirst[i] = 1;
				for (int j = 0; j < N; j++) {
					adj[i][j] = readInt();
					if (j != i)
						loseFirst[i] &= adj[i][j];
				}
			}
			if (N != 1) {
				int[] val = new int[N];
				for (int i = 0; i < N; i++)
					val[i] = i;
				solve(val, N, 0);
			}
			out.printf("Case #%d: \n", t);
			for (int i = 0; i < N; i++)
				out.printf("%d %d\n", best[i], loseFirst[i] == 0 ? N / 2 + 1 : 1);
		}
		
		out.close();
	}
	
	static HashMap<Integer, HashSet<Integer>> dp = new HashMap<Integer, HashSet<Integer>>();
	
	static HashSet<Integer> solve (int[] val, int n, int index) {
		int state = 0;
		for (int i = 0; i < n; i++)
			state |= 1 << val[i];
		if (dp.containsKey(state))
			return dp.get(state);
		if (n == 1) {
			best[val[0]] = Math.min(best[val[0]], places[index]);
			HashSet<Integer> res = new HashSet<Integer>();
			res.add(val[0]);
			return res;
		}
		HashSet<Integer> res = new HashSet<Integer>();
		for (int i = 0; i < 1 << n; i++) {
			int cnt = 0;
			for (int j = 0; j < n; j++)
				if ((i & 1 << j) > 0)
					cnt++;
			if (cnt != n / 2)
				continue;
			int[] a = new int[n / 2];
			int[] b = new int[n / 2];
			cnt = 0;
			for (int j = 0; j < n; j++)
				if ((i & 1 << j) > 0)
					a[cnt++] = val[j];

			cnt = 0;
			for (int j = 0; j < n; j++)
				if ((i & 1 << j) == 0)
					b[cnt++] = val[j];
			
			HashSet<Integer> res1 = solve(a, n / 2, index + 1);
			HashSet<Integer> res2 = solve(b, n / 2, index + 1);
			for (int j : res1)
				for (int k : res2) {
					if (adj[j][k] == 1) {
						best[j] = Math.min(best[j], places[index]);
						res.add(j);
					} else {
						best[k] = Math.min(best[k], places[index]);
						res.add(k);
					}
				}
		}
		dp.put(state, res);
		return res;
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}

