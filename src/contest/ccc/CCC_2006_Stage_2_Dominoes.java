package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2006_Stage_2_Dominoes {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	/*
	 * Solution sketch: For each case (odd-even, odd-odd, even-even): Create the
	 * edges (2, 4, 1) respectively Use MST logic
	 */

	static int[] sz;
	static int[] id;
	static final int SIZE = 50001;
	static final int INF = 1 << 30;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int q = 1; q <= t; q++) {
			int n = readInt();
			int[] degree = new int[SIZE];
			sz = new int[SIZE];
			id = new int[SIZE];
			for (int i = 0; i < SIZE; i++) {
				sz[i] = 1;
				id[i] = i;
			}
			for (int i = 0; i < n; i++) {
				int a = readInt();
				int b = readInt();
				degree[a]++;
				degree[b]++;
				System.out.println(find(a) + " " + find(b));
				merge(a, b);
			}
			// i represents the id looking for
			ArrayList<State> s = new ArrayList<State>();
			for (int i = 0; i < SIZE; i++) {
				int cnt = 0;
				int se = INF;
				int so = INF;
				for (int j = 0; j < SIZE; j++) {
					if (degree[j] != 0 && find(j) == i) {
						cnt++;
						if (degree[j] % 2 == 0)
							se = Math.min(se, j);
						else
							so = Math.min(so, j);
					}
				}
				if (cnt > 0)
					s.add(new State(i, se, so));
			}
			System.out.println(s.size());
			int oddcount = 0;
			for (int i = 0; i < SIZE; i++)
				if (degree[i] > 0 && degree[i] % 2 == 1)
					oddcount++;
			ArrayList<Edge> e = new ArrayList<Edge>();
			PriorityQueue<Edge> odddestroy = new PriorityQueue<Edge>();
			for (int i = 0; i < s.size(); i++) {
				for (int j = i + 1; j < s.size(); j++) {
					State s1 = s.get(i);
					State s2 = s.get(j);
					if (s1.so != INF && s2.so != INF) {
						e.add(new Edge(s1.id, s2.id, Math.min(s1.so, s2.so), Math.max(s1.so, s2.so), -1, -1));
						odddestroy.add(new Edge(s1.id, s2.id, Math.min(s1.so, s2.so), Math.max(s1.so, s2.so), -1, -1));
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.so), Math.max(s1.se, s2.so), 1, -1));
						e.add(new Edge(s1.id, s2.id, Math.min(s1.so, s2.se), Math.max(s1.so, s2.se), -1, 1));
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.se), Math.max(s1.se, s2.se), 1, 1));
					} else if (s1.so == INF && s2.so == INF) {
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.se), Math.max(s1.se, s2.se), 1, 1));
					} else if (s1.so != INF && s2.so == INF) {
						e.add(new Edge(s1.id, s2.id, Math.min(s1.so, s2.se), Math.max(s1.so, s2.se), -1, 1));
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.se), Math.max(s1.se, s2.se), 1, 1));
					} else {
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.so), Math.max(s1.se, s2.so), 1, -1));
						e.add(new Edge(s1.id, s2.id, Math.min(s1.se, s2.se), Math.max(s1.se, s2.se), 1, 1));
					}
				}
			}
			Collections.sort(e);
			ArrayList<Pair> ps = new ArrayList<Pair>();
			for (Edge edge : e) {
				int add = edge.addo1 + edge.addo2;
				if (find(edge.id1) != find(edge.id2)) {
					if (add == 2 && oddcount == 2) {
						while (!odddestroy.isEmpty()) {
							while (!merge(odddestroy.peek().id1, odddestroy.peek().id2))
								odddestroy.poll();
						}
						if (odddestroy.size() == 0) {
							continue;
						}
						Edge odd = odddestroy.poll();
						merge(odd.id1, odd.id2);
						ps.add(new Pair(edge.a, edge.b));
						oddcount -= 2;
						degree[edge.a]--;
						degree[edge.b]--;
					}
					merge(edge.id1, edge.id2);
					ps.add(new Pair(edge.a, edge.b));
					oddcount += add;
					degree[edge.a] += edge.addo1;
					degree[edge.b] += edge.addo2;
				}
			}
			ArrayList<Integer> odd = new ArrayList<Integer>();
			for (int i = 0; i < SIZE; i++) {
				if (degree[i] > 0 && degree[i] % 2 == 1)
					odd.add(i);
			}
			Collections.sort(odd);
			for (int i = 0; i < odd.size() / 2 - 1; i++) {
				ps.add(new Pair(odd.get(i * 2), odd.get(i * 2 + 1)));
			}
			System.out.println(ps.size());
			for (Pair p : ps) {
				System.out.println(p.a + " " + p.b);
			}
		}

		ps.close();
	}

	static class Pair implements Comparable<Pair> {
		int a, b;

		Pair (int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo (Pair e) {
			if (a == e.a)
				return b - e.b;
			return a - e.a;
		}
	}

	static class Edge implements Comparable<Edge> {
		int id1, id2;
		int a, b;
		int addo1, addo2;

		Edge (int id1_, int id2_, int a_, int b_, int addo1_, int addo2_) {
			id1 = id1_;
			id2 = id2_;
			a = a_;
			b = b_;
			addo1 = addo1_;
			addo2 = addo2_;
		}

		@Override
		public int compareTo (Edge e) {
			if (a == e.a)
				return b - e.b;
			return a - e.a;
		}
	}

	static class State {
		int id, se, so;

		State (int id, int se, int so) {
			this.id = id;
			this.se = se;
			this.so = so;
		}
	}

	static int find (int x) {
		return id[x] == x ? x : (id[x] = find(id[x]));
	}

	static boolean merge (int x, int y) {
		int rx = find(x);
		int ry = find(y);
		if (rx == ry)
			return false;
		System.out.println("MERGED SUBROUTINE CALLED");
		if (sz[rx] > sz[ry]) {
			sz[rx] += sz[ry];
			id[ry] = rx;
		} else {
			sz[ry] += sz[rx];
			id[rx] = ry;
		}
		return true;
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