package contest.codeforces;

import java.util.*;
import java.io.*;

public class CROC_2016_Elimination_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] a, b;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static ArrayList<HashSet<Integer>> need = new ArrayList<HashSet<Integer>>();
	static int[] in;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();

		a = new int[m];
		b = new int[m];

		in = new int[n];

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			need.add(new HashSet<Integer>());
		}
		for (int i = 0; i < m; i++) {
			a[i] = readInt() - 1;
			b[i] = readInt() - 1;
			adj.get(a[i]).add(b[i]);

			in[b[i]]++;
		}

		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < n; i++) {
			if (in[i] == 0)
				q.offer(i);
		}

		while (!q.isEmpty()) {
			if (q.size() > 1) {
				out.println(-1);
				out.close();
				return;
			}
			int curr = q.poll();
			for (int next : adj.get(curr)) {
				in[next]--;
				if (in[next] == 0) {
					need.get(curr).add(next);
					q.offer(next);
				}
			}
		}

		for (int i = m - 1; i >= 0; i--) {
			if (need.get(a[i]).contains(b[i])) {
				out.println(i + 1);
				out.close();
				return;
			}
		}

		out.println(0);
		out.close();
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
