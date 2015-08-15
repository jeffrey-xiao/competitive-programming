package dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCO_Escape_Maze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	static Stack<Integer> s = new Stack<Integer>();
	static ArrayList<ArrayList<Integer>> adj;
	static int[] disc;
	static int[] low;
	static int count;
	static boolean[] vi = new boolean[400];
	static ArrayList<Integer> c;
	static HashSet<Integer> art;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		while (n != 0) {
			s.clear();
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
			for (int i = 0; i < 400; i++) {
				if (!vi[i]) {
					count = 0;
					dfs(i, -1);
				}
			}
			long l = 1;
			System.out.print(c.size() + " ");
			for (Integer i : c) {
				l *= i;
			}
			System.out.println(l);
			n = readInt();
		}

	}

	private static void dfs (int i, int prev) {
		disc[i] = low[i] = count++;
		vi[i] = true;
		s.push(i);
		for (Integer j : adj.get(i)) {
			if (prev == j)
				continue;
			if (disc[j] == -1) {
				dfs(j, i);
				low[i] = Math.min(low[i], low[j]);
			} else if (s.contains(j)) {
				low[i] = Math.min(low[i], disc[j]);
			}
		}
		if (disc[i] == low[i]) {
			System.out.println("HERE");
			int count = 0;
			boolean hasArt = false;
			while (s.peek() != i) {
				if (!art.contains(s.pop()))
					count++;
				else
					hasArt = true;
			}

			if (!art.contains(s.pop()))
				count++;
			else
				hasArt = true;
			if (count != 0) {
				if (!hasArt && count != 1) {
					c.add(count * (count - 1) / 2);
					c.add(1);
				} else {
					c.add(count);
				}
			}
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
