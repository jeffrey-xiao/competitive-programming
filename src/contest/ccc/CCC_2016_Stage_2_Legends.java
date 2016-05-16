package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_Stage_2_Legends {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int S, T, N, M, cnt;
	static Edge[] e;
	static int[] last;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		S = readInt();
		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			N = readInt();
			M = readInt();
			
			cnt = 0;
			last = new int[N];
			e = new Edge[2 * M];
			
			Arrays.fill(last, -1);
			
			for (int i = 0; i < M; i++) {
				int a = readInt() - 1;
				int b = readInt() - 1;
				addEdge(a, b, e, cnt);
				cnt += 2;
			}
			
			if (S == 1)
				out.println(new Solve1().solve() ? "YES" : "NO");
			else if (S == 2)
				out.println(new Solve2().solve() ? "YES" : "NO");
			else if (S == 3)
				out.println(new Solve3().solve() ? "YES" : "NO");
			else if (S == 4)
				out.println(new Solve4().solve() ? "YES" : "NO");
			else if (S == 5)
				out.println(new Solve5().solve() ? "YES" : "NO");
		}
		
		out.close();
	}

	static class Solve1 {
		int[] par, depth, occ;
		Solve1 () {
			par = new int[N];
			depth = new int[N];
			occ = new int[N];
			
			for (int i = 0; i < N; i++) {
				par[i] = -1;
				depth[i] = -1;
			}
		}
		
		
		int getDepth (int a) {
			if (depth[a] != -1)
				return depth[a];
			if (par[a] == a)
				return 0;
			return depth[a] = getDepth(par[a]) + 1;
		}
		
		boolean solve () {
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(0);
			par[0] = 0;
			
			while (!q.isEmpty()) {
				int curr = q.poll();
				for (int next = last[curr]; next != -1; next = e[next].last) {
					if (par[e[next].to] == -1) {
						q.offer(e[next].to);
						par[e[next].to] = curr;
					}
				}
			}
			
			for (int i = 0; i < N; i++)
				getDepth(i);
			
			for (int i = 0; i < M; i++) {
				int a = e[i << 1].to;
				int b = e[i << 1 | 1].to;
				if (par[a] != b && par[b] != a) {
					while (a != b) {
						if (depth[a] >= depth[b]) {
							occ[a]++;
							a = par[a];
						} else {
							occ[b]++;
							b = par[b];
						}
					}
				}
			}
			
			for (int i = 0; i < N; i++)
				if (occ[i] >= 2)
					return true;

			return false;
		}
	}
	
	static class Solve2 {
		boolean solve () {
			return M >= N;
		}
	}
	
	static class Solve3 {
		int[] par, depth, occ;
		Solve3 () {
			par = new int[N];
			depth = new int[N];
			occ = new int[N];
			
			for (int i = 0; i < N; i++) {
				par[i] = -1;
				depth[i] = -1;
			}
		}
		
		boolean solve () {
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(0);
			par[0] = 0;
			depth[0] = 0;
			
			while (!q.isEmpty()) {
				int curr = q.poll();
				for (int next = last[curr]; next != -1; next = e[next].last) {
					if (par[e[next].to] == -1) {
						q.offer(e[next].to);
						par[e[next].to] = curr;
						depth[e[next].to] = depth[curr] + 1;
					}
				}
			}
			
			for (int i = 0; i < M; i++) {
				int a = e[i << 1].to;
				int b = e[i << 1 | 1].to;
				if (par[a] != b && par[b] != a) {
					int dist = 0;
					while (a != b) {
						if (depth[a] >= depth[b]) {
							occ[a]++;
							a = par[a];
						} else {
							occ[b]++;
							b = par[b];
						}
						dist++;
					}
					if (dist >= 3)
						return true;
				}
			}
			
			for (int i = 0; i < N; i++)
				if (occ[i] >= 2)
					return true;

			return false;
		}
	}
	
	static class Solve4 {
		int[] degree;
		Solve4 () {
			degree = new int[N];
		}
		boolean solve () {
			for (int i = 0; i < 2 * M; i++)
				degree[e[i].to]++;
			
			for (int i = 0; i < N; i++)
				if (degree[i] >= 3)
					return true;
			return false;
		}
	}
	
	static class Solve5 {
		boolean[] vis;
		boolean[] visE;
		int[] degree;
		Stack<Integer> edgeStack;
		Solve5 () {
			vis = new boolean[N];
			visE = new boolean[M * 2];
			degree = new int[N];
			edgeStack = new Stack<Integer>();
		}

		boolean isValid (int u, int prevEdge) {
			vis[u] = true;
			boolean ret = false;
			for (int v = last[u]; v != -1; v = e[v].last) {
				if (v == prevEdge || v == (prevEdge ^ 1))
					continue;
				edgeStack.push(v);
				if (vis[e[v].to]) {
					HashSet<Integer> unique = new HashSet<Integer>();
					boolean inCycle = false;
					int degreeThreeCnt = 0;
					int sz = 0;
					for (int edge : edgeStack) {
						int from = e[edge ^ 1].to;
						if (from == e[v].to || inCycle) {
							inCycle = true;
							sz++;
							for (int next = last[from]; next != -1; next = e[next].last)
								unique.add(e[next].to);
							if (degree[from] >= 3)
								degreeThreeCnt++;
						}
					}
					if (unique.size() - sz >= 2 && degreeThreeCnt >= 2) {
						edgeStack.pop();
						vis[u] = false;
						return true;
					}
				} else {
					if (!visE[v]) {
						visE[v] = true;
						ret |= isValid(e[v].to, v);
					}
				}
				edgeStack.pop();
			}
			vis[u] = false;
			return ret;
		}
		
		boolean solve () {
			for (int i = 0; i < 2 * M; i++)
				degree[e[i].to]++;
			
			return isValid(0, -1);
		}
	}

	static void addEdge (int a, int b, Edge[] e, int cnt) {
		e[cnt] = new Edge(b, last[a]);
		last[a] = cnt++;
		
		e[cnt] = new Edge(a, last[b]);
		last[b] = cnt++;
	}
	
	static class Edge {
		int to, last;
		Edge (int to, int last) {
			this.to = to;
			this.last = last;
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

