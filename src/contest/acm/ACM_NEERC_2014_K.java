package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NEERC_2014_K {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;
	static int[][] distTo, minDist;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 0; t < T; t++) {
			N = readInt();
			distTo = new int[N][N];
			minDist = new int[N][N];
			for	(int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					int val = readInt() - 1;
					distTo[i][val] = j;
					minDist[i][j] = val;
				}
			ArrayList<Integer> p = new ArrayList<Integer>();
			for (int i = 0; i < N; i++)
				p.add(i);
			compute(p);
			out.println();
		}
		
		out.close();
	}

	static void compute (ArrayList<Integer> p) {
		if (p.size() == 2) {
			out.println(p.get(0) + 1 + " " + (p.get(1) + 1));
			return;
		}
		if (p.size() <= 1)
			return;
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		HashSet<Integer> curr = new HashSet<Integer>();
		for (int x : p)
			curr.add(x);
		for (int i = 1; i < N; i++)
			if (curr.contains(minDist[p.get(0)][i])) {
				out.printf("%d %d\n", p.get(0) + 1, minDist[p.get(0)][i] + 1);
				a.add(p.get(0));
				b.add(minDist[p.get(0)][i]);
				for (int x : p) {
					if (x == p.get(0) || x == minDist[p.get(0)][i])
						continue;
					if (distTo[x][p.get(0)] < distTo[x][minDist[p.get(0)][i]]) {
						a.add(x);
					} else {
						b.add(x);
					}
				}
				compute(a);
				compute(b);
				break;
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

