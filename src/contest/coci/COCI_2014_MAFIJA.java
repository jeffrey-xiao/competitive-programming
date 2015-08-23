package contest.coci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_MAFIJA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[] in, next;
	static boolean[] visited;
	static int ans = 0;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		// ps = new PrintWriter(new BufferedWriter(new
		// OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		ps = new PrintWriter("output.txt");

		int n = readInt();
		in = new int[n + 1];
		next = new int[n + 1];
		visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			next[i] = readInt();
			in[next[i]]++;
		}
		for (int i = 1; i <= n; i++) {
			if (in[i] == 0) {
				dfs(i, false);
			}
		}
		for (int i = 1; i <= n; i++)
			dfs(i, true);
		ps.println(ans);
		ps.close();
	}

	public static void dfs (int i, boolean inno) {
		if (visited[i])
			return;
		visited[i] = true;
		in[next[i]]--;
		if (!inno)
			ans++;
		if (in[next[i]] == 0 || !inno) {
			dfs(next[i], !inno);
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