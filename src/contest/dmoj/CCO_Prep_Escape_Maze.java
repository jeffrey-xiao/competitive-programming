package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCO_Prep_Escape_Maze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static Stack<Edge> s = new Stack<Edge>();
	static ArrayList<ArrayList<Integer>> adj;
	static int[] disc;
	static int[] low;
	static int count;
	static boolean[] vi = new boolean[400];
	static ArrayList<Integer> c;
	static HashSet<Integer> art;
	static long ansNum;
	static long ansCount;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		while (n != 0) {
			s.clear();
			ansNum = 0;
			ansCount = 1;
			art = new HashSet<Integer>();
			c = new ArrayList<Integer>();
			adj = new ArrayList<ArrayList<Integer>>();
			disc = new int[400];
			low = new int[400];
			for (int i = 0; i < 400; i++) {
				adj.add(new ArrayList<Integer>());
				disc[i] = low[i] = -1;
				vi[i] = true;
			}
			HashSet<Integer> p = new HashSet<Integer>();
			for (int i = 0; i < n; i++) {
				int a = readInt() - 1;
				int b = readInt() - 1;
				p.add(a);
				p.add(b);
				adj.get(a).add(b);
				adj.get(b).add(a);
				vi[a] = false;
				vi[b] = false;
			}
			int size = 0;
			boolean[] v = new boolean[400];
			for (Integer i : p) {
				if (v[i])
					continue;
				v[i] = true;
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(i);
				while (!q.isEmpty()) {
					for (Integer j : adj.get(q.poll())) {
						if (!v[j]) {
							v[j] = true;
							q.offer(j);
						}
					}
				}
				size++;
			}
			for (Integer i : p) {
				v = new boolean[400];
				int count = 0;
				v[i] = true;
				for (Integer j : p) {
					if (v[j])
						continue;
					count++;
					v[j] = true;
					Queue<Integer> q = new LinkedList<Integer>();
					q.offer(j);
					while (!q.isEmpty()) {
						int curr = q.poll();
						for (int k : adj.get(curr)) {
							if (!v[k]) {
								v[k] = true;
								q.offer(k);
							}
						}
					}
				}
				if (count > size) {
					art.add(i);
				}
			}
			for (int i = 0; i < 400; i++)
				if (!vi[i])
					dfs(i, -1);

			HashSet<Integer> currCom = new HashSet<Integer>();
			while (!s.isEmpty()) {
				currCom.add(s.peek().a);
				currCom.add(s.peek().b);
				s.pop();
			}
			int cnt = 0;
			int artCnt = 0;
			for (int k : currCom) {
				if (art.contains(k))
					artCnt++;
				else
					cnt++;
			}
			if (artCnt == 0 && cnt != 0) {
				ansNum += 2;
				ansCount *= (cnt * (cnt - 1) / 2);
			} else if (artCnt == 1 && cnt != 0) {
				ansNum += 1;
				ansCount *= cnt;
			}

			System.out.println(ansNum + " " + ansCount);
			n = readInt();
		}

	}

	private static void dfs (int i, int prev) {
		disc[i] = low[i] = count++;
		vi[i] = true;
		int children = 0;
		for (Integer j : adj.get(i)) {
			if (!vi[j]) {
				children++;
				s.push(new Edge(i, j));
				dfs(j, i);
				low[i] = Math.min(low[i], low[j]);
				if ((disc[i] == 0 && children > 1) || (disc[i] > 0 && low[j] >= disc[i])) {

					HashSet<Integer> currCom = new HashSet<Integer>();
					while (s.peek().a != i || s.peek().b != j) {
						currCom.add(s.peek().a);
						currCom.add(s.peek().b);
						s.pop();
					}
					currCom.add(s.peek().a);
					currCom.add(s.peek().b);
					s.pop();
					int cnt = 0;
					int artCnt = 0;
					for (int k : currCom) {
						if (art.contains(k))
							artCnt++;
						else
							cnt++;
					}
					if (artCnt == 0) {
						ansNum += 2;
						ansCount *= (cnt * (cnt - 1) / 2);
					} else if (artCnt == 1 && cnt != 0) {
						ansNum += 1;
						ansCount *= cnt;
					}
				}
			} else if (j != prev && disc[j] < low[i]) {
				low[i] = disc[j];
				s.push(new Edge(i, j));
			}
		}
	}

	static class Edge {
		int a, b;

		Edge (int a, int b) {
			this.a = a;
			this.b = b;
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