package contest.coci;

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

public class COCI_2007_AVOGADRO {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int N;
	static ArrayList<LinkedList<Integer>> col = new ArrayList<LinkedList<Integer>>(); // first
																						// is
																						// number,
																						// second
																						// are
																						// columns
	static int[][] g;
	static int[][] occ; // first is number, second is row
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		N = readInt();
		for (int i = 0; i < N; i++)
			col.add(new LinkedList<Integer>());
		g = new int[N][3];
		occ = new int[N][3];
		v = new boolean[N];

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < N; i++) {
				int a = readInt() - 1;
				g[i][j] = a;
				col.get(a).add(i);
				occ[a][j]++;
			}
		}
		int res = 0;
		Queue<Integer> process = new LinkedList<Integer>();
		for (int i = 0; i < N; i++)
			if (occ[i][0] == 0 || occ[i][1] == 0 || occ[i][2] == 0) {
				process.offer(i);
				// System.out.println(i + " " + occ[i][0] + " " + occ[i][1] +
				// " " + occ[i][2]);
			}
		while (!process.isEmpty()) {
			Integer next = process.poll();
			for (Integer c : col.get(next)) {
				if (v[c])
					continue;
				v[c] = true;
				res++;
				occ[g[c][0]][0]--;
				occ[g[c][1]][1]--;
				occ[g[c][2]][2]--;
				if (occ[g[c][0]][0] == 0)
					process.offer(g[c][0]);
				if (occ[g[c][1]][1] == 0)
					process.offer(g[c][1]);
				if (occ[g[c][2]][2] == 0)
					process.offer(g[c][2]);

			}
		}
		System.out.println(res);
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
