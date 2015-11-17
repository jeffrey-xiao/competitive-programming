package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TSOC_Dungeon_Crawling {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static boolean[] v;
	static boolean[] isEnd;
	static boolean[] isStart;
	static int[] dist;
	static long cnt = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		v = new boolean[n];
		isEnd = new boolean[n];
		isStart = new boolean[n];
		dist = new int[n];
		for (int i = 0; i < n; i++) {
			isEnd[i] = isStart[i] = true;
			dist[i] = 1 << 30;
			adj.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			adj.get(a).add(b);
			isEnd[a] = false;
			isStart[b] = false;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			if (isStart[i]) {
				dfs(i);
				q.offer(i);
				dist[i] = 0;
			}
		}
		while (!q.isEmpty()) {
			int i = q.poll();
			for (int j : adj.get(i)) {
				if (dist[j] <= dist[i] + 1)
					continue;
				dist[j] = dist[i] + 1;
				q.offer(j);
			}
		}
		pr.println(cnt % 1000000007);
		for (int i = 0; i < n; i++)
			if (isEnd[i])
				pr.print(dist[i] + 1 + " ");
		pr.println();
		pr.close();
	}

	static void dfs (int i) {
		v[i] = true;
		for (int j : adj.get(i))
			dfs(j);
		if (isEnd[i])
			cnt++;
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
