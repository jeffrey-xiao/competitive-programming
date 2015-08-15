package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class APOI_2008_Roads {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(System.out);
	static StringTokenizer st;

	static int[] id;
	static int[] size;
	static int n, e, k;

	// concrete is white and cobble is black
	public static void main (String[] args) throws IOException {
		n = readInt();
		e = readInt();
		k = readInt();

		ArrayList<Edge> white = new ArrayList<Edge>();
		ArrayList<Edge> black = new ArrayList<Edge>();
		for (int x = 0; x < e; x++) {
			int src = readInt() - 1;
			int dest = readInt() - 1;
			if (readInt() == 0)
				black.add(new Edge(src, dest));
			else
				white.add(new Edge(src, dest));
		}
		init();

		for (int x = 0; x < white.size(); x++)
			if (find(white.get(x).src) != find(white.get(x).dest))
				merge(white.get(x).src, white.get(x).dest);
		ArrayList<Integer> mark = new ArrayList<Integer>();
		for (int x = 0; x < black.size(); x++) {
			if (find(black.get(x).src) != find(black.get(x).dest)) {
				merge(black.get(x).src, black.get(x).dest);
				mark.add(x);
			}
		}
		if (mark.size() > k) {
			ps.println("no solution");
		} else {
			init();
			for (Integer i : mark) {
				merge(black.get(i).src, black.get(i).dest);
				ps.println(black.get(i).src + 1 + " " + (black.get(i).dest + 1)
						+ " 0");
			}
			int count = 0;
			for (int x = 0; x < black.size(); x++) {
				if (find(black.get(x).src) != find(black.get(x).dest)) {
					merge(black.get(x).src, black.get(x).dest);
					ps.println(black.get(x).src + 1 + " "
							+ (black.get(x).dest + 1) + " 0");
					count++;
					if (count + mark.size() == k)
						break;
				}
			}
			count = 0;
			for (int x = 0; x < white.size(); x++)
				if (find(white.get(x).src) != find(white.get(x).dest)) {
					count++;
					merge(white.get(x).src, white.get(x).dest);
					ps.println(white.get(x).src + 1 + " "
							+ (white.get(x).dest + 1) + " 1");
					if (count == n - k - 1)
						break;
				}
		}
		ps.flush();
		ps.close();
	}

	private static void merge (int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (size[rootx] > size[rooty]) {
			size[rootx] += size[rooty];
			id[rooty] = id[rootx];
		} else {
			size[rooty] += size[rootx];
			id[rootx] = id[rooty];
		}
	}

	private static int find (int x) {
		while (x != id[x])
			x = id[x];
		return x;
	}

	private static void init () {
		id = new int[n];
		size = new int[n];
		for (int x = 0; x < n; x++) {
			id[x] = x;
			size[x] = 0;
		}
	}

	static class Edge {
		int src, dest;

		Edge (int s, int d) {
			src = s;
			dest = d;
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
