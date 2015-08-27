package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_2009_Stage_2_Beware_of_the_Geoducks {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] matrix;
	static int[] pathS;
	static int[] pathM;
	static int s, m;

	public static void main (String[] args) throws IOException {
		int v = readInt();
		int e = readInt();
		s = readInt();
		m = readInt();
		int g = readInt();
		int t = readInt();
		matrix = new int[v][v];
		for (int x = 0; x < e; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			matrix[a][b] = c;
			matrix[b][a] = c;
		}
		pathS = new int[s];
		pathM = new int[m];
		HashSet<Integer> geoducks = new HashSet<Integer>();
		for (int x = 0; x < s; x++)
			pathS[x] = readInt() - 1;
		for (int x = 0; x < m; x++)
			pathM[x] = readInt() - 1;

		for (int x = 0; x < g; x++)
			geoducks.add(readInt() - 1);
		if (s == 1 || m == 1) {
			if (pathS[0] == pathM[0]) {
				if (geoducks.contains(pathS[0]))
					System.out.println("NO");
				else
					System.out.println("YES");
			} else
				System.out.println("NO");
			return;
		}
		int si = 0;
		double sd = 0;
		int mi = 0;
		double md = 0;
		for (int x = 0; x < t * 2 + 1; x++) {
			if (geoducks.contains(pathS[si]) || geoducks.contains(pathM[mi])) {
				System.out.println("NO");
				return;
			}
			if (sd == 0 && md == 0 && pathS[si] == pathM[mi]) {
				System.out.println("YES");
				return;
			}

			if (checkTrue(si, mi, sd, md)) {
				System.out.println("YES");
				return;
			}
			// System.out.println(matrix[pathS[si]][pathS[si+1]]);
			if (si != pathS.length - 1)
				sd += 0.5;
			if (mi != pathM.length - 1)
				md += 0.5;
			if (si != s - 1 && sd == matrix[pathS[si]][pathS[si + 1]]) {
				sd = 0;
				si++;
			}
			if (mi != m - 1 && md == matrix[pathM[mi]][pathM[mi + 1]]) {
				md = 0;
				mi++;
			}
			// System.out.println(sd + " " + md + " " + si + " " + mi);
		}
		System.out.println("NO");
	}

	private static boolean checkTrue (int si, int mi, double sd, double md) {
		if (si == s - 1 || mi == m - 1)
			return false;
		if (pathS[si] == pathM[mi] && pathS[si + 1] == pathM[mi + 1])
			return sd == md;
		if (pathS[si] == pathM[mi + 1] && pathS[si + 1] == pathM[mi])
			return matrix[pathS[si]][pathS[si + 1]] - sd == md;
		return false;
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
