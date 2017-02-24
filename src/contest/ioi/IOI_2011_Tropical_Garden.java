package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2011_Tropical_Garden {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, P, Q;
	static ArrayList<ArrayList<Integer>> adj;
	static HashMap<Long, State> vis;
	static HashMap<Long, Integer> curr;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		P = readInt();

		adj = new ArrayList<ArrayList<Integer>>(N);
		vis = new HashMap<Long, State>();
		curr = new HashMap<Long, Integer>();

		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			int u = readInt();
			int v = readInt();
			if (adj.get(u).size() < 2)
				adj.get(u).add(v);
			if (adj.get(v).size() < 2)
				adj.get(v).add(u);
		}

		for (int i = 0; i < N; i++) {
			curr.clear();
			if (!vis.containsKey(1l * adj.get(i).get(adj.get(i).size() - 1) * N + i))
				dfs(i, adj.get(i).get(adj.get(i).size() - 1), 0, -1, -1);
		}

		Q = readInt();

		for (int i = 0; i < Q; i++) {
			int K = readInt();
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				State next = vis.get(1l * adj.get(j).get(adj.get(j).size() - 1) * N + j);

				// never can reach restaurant
				if (next.firstOcc == -1) {
					continue;
				}

				// restaurant can only be reached once
				if (next.firstOcc < next.len) {
					if (next.firstOcc == K) {
						cnt++;
						continue;
					}
				}

				// restaurant is on cycle
				else if (K >= next.firstOcc && (K - next.firstOcc) % next.cycle == 0) {
					cnt++;
					continue;
				}

				// never can reach restaurant
				if (next.secondOcc == -1)
					continue;

				if (next.secondOcc < next.len) {
					if (next.secondOcc == K) {
						cnt++;
						continue;
					}
				}

				// restaurant is on cycle
				else if (K >= next.secondOcc && (K - next.secondOcc) % next.cycle == 0) {
					cnt++;
					continue;
				}
			}
			out.printf("%d ", cnt);
		}
		out.println();
		out.close();
	}

	static int dfs (int u, int prev, int depth, int firstOcc, int secondOcc) {

		if (u == P) {
			if (firstOcc == -1)
				firstOcc = depth;
			else
				secondOcc = depth;
		}

		int nextNode;
		if (adj.get(u).size() == 1)
			nextNode = adj.get(u).get(0);
		else if (adj.get(u).get(0) == prev)
			nextNode = adj.get(u).get(1);
		else
			nextNode = adj.get(u).get(0);

		long currState = getStateIndex(prev, u);
		long nextState = getStateIndex(u, nextNode);

		curr.put(currState, depth);

		// start of cycle has been found
		if (curr.containsKey(nextState)) {
			int cycleLength = depth - curr.get(nextState) + 1;

			PriorityQueue<Integer> val = new PriorityQueue<Integer>();

			if (firstOcc >= curr.get(nextState))
				val.add((firstOcc - curr.get(nextState) + 1) % cycleLength);

			if (secondOcc >= curr.get(nextState))
				val.add((secondOcc - curr.get(nextState) + 1) % cycleLength);

			val = makeUnique(val);
			vis.put(currState, new State(0, cycleLength, val.isEmpty() ? -1 : val.poll(), val.isEmpty() ? -1 : val.poll()));
			return curr.get(nextState);
		}

		// node is not in cycle
		else if (vis.containsKey(nextState)) {
			State next = vis.get(nextState);

			PriorityQueue<Integer> val = new PriorityQueue<Integer>();

			val.add(Math.max(-1, firstOcc - depth));
			val.add(Math.max(-1, secondOcc - depth));

			if (next.firstOcc != -1)
				val.add(next.firstOcc + 1);

			if (next.secondOcc != -1)
				val.add(next.secondOcc + 1);

			while (!val.isEmpty() && val.peek() == -1)
				val.poll();
			val = makeUnique(val);
			vis.put(currState, new State(next.len + 1, next.cycle, val.isEmpty() ? -1 : val.poll(), val.isEmpty() ? -1 : val.poll()));
			return -1;
		}

		// node might be in cycle
		else {
			int res = dfs(nextNode, u, depth + 1, firstOcc, secondOcc);
			State next = vis.get(nextState);
			if (res == -1 || depth < res) {
				PriorityQueue<Integer> val = new PriorityQueue<Integer>();

				val.add(Math.max(-1, firstOcc - depth));
				val.add(Math.max(-1, secondOcc - depth));

				if (next.firstOcc != -1)
					val.add(next.firstOcc + 1);

				if (next.secondOcc != -1)
					val.add(next.secondOcc + 1);

				while (!val.isEmpty() && val.peek() == -1)
					val.poll();
				val = makeUnique(val);
				vis.put(currState, new State(next.len + 1, next.cycle, val.isEmpty() ? -1 : val.poll(), val.isEmpty() ? -1 : val.poll()));
			} else {
				PriorityQueue<Integer> val = new PriorityQueue<Integer>();

				val.add(Math.max(-1, firstOcc - depth));
				val.add(Math.max(-1, secondOcc - depth));

				if (next.firstOcc != -1)
					val.add((next.firstOcc + 1) % next.cycle);

				if (next.secondOcc != -1)
					val.add((next.secondOcc + 1) % next.cycle);

				while (!val.isEmpty() && val.peek() == -1)
					val.poll();

				val = makeUnique(val);
				vis.put(currState, new State(0, next.cycle, val.isEmpty() ? -1 : val.poll(), val.isEmpty() ? -1 : val.poll()));
			}
			return res;
		}
	}

	static PriorityQueue<Integer> makeUnique (PriorityQueue<Integer> pq) {
		PriorityQueue<Integer> ret = new PriorityQueue<Integer>();
		if (pq.isEmpty())
			return ret;
		ret.offer(pq.poll());
		int last = ret.peek();
		while (!pq.isEmpty()) {
			if (last != pq.peek())
				ret.offer(last = pq.peek());
			pq.poll();
		}
		return ret;
	}

	static long getStateIndex (int prev, int u) {
		if (adj.get(u).contains(prev))
			return 1l * prev * N + u;
		return 1l * adj.get(u).get(adj.get(u).size() - 1) * N + u;
	}

	static class State {
		int len, cycle, firstOcc, secondOcc;

		State (int len, int cycle, int firstOcc, int secondOcc) {
			this.len = len;
			this.cycle = cycle;
			this.firstOcc = firstOcc;
			this.secondOcc = secondOcc;
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
