package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Election_Night_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int states;
	static int candidates;
	static ArrayList<ArrayList<Edge>> curr = new ArrayList<ArrayList<Edge>>();

	public static void main (String[] args) throws IOException {
		states = readInt();
		candidates = readInt();
		while (states != 0) {
			// SOURCE NODE IS 0
			// STATES ARE FROM 1-STATES
			// CANDIDATES ARE FROM STATES+1 - STATES+CANDIDATES;
			// SINK IS STATES+CANDIDATES+1
			ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
			for (int x = 0; x < states + candidates + 2; x++)
				adj.add(new ArrayList<Edge>());
			for (int x = states + 1; x < states + candidates + 1; x++) {
				adj.get(x).add(new Edge(states + candidates + 1, 0));
				adj.get(states + candidates + 1).add(new Edge(x, 0));
			}
			for (int x = 1; x <= states; x++) {
				adj.get(0).add(new Edge(x, 1));
				adj.get(x).add(new Edge(0, 0));
			}
			int[] guarantee = new int[candidates + 1];
			int[] possible = new int[candidates + 1];
			boolean[] willWin = new boolean[candidates + 1];
			boolean[] mightWin = new boolean[candidates + 1];
			for (int x = 1; x <= states; x++) {
				int n = readInt();
				if (n == 1) {
					int candidate = readInt();
					adj.get(x).add(
							new Edge(states + candidate, Integer.MAX_VALUE));
					adj.get(states + candidate).add(new Edge(x, 0));
					guarantee[candidate]++;
					possible[candidate]++;
				} else {
					for (; n > 0; n--) {
						int candidate = readInt();
						adj.get(x)
								.add(new Edge(states + candidate,
										Integer.MAX_VALUE));
						adj.get(states + candidate).add(new Edge(x, 0));
						possible[candidate]++;
					}
				}
			}
			main : for (int x = 1; x <= candidates; x++) {
				for (int y = 1; y <= candidates; y++) {
					if (x != y && possible[y] >= guarantee[x]) {
						continue main;
					}
				}
				willWin[x] = true;
			}
			// CHECK FOR POSSIBILITY OF WINNING
			for (int x = 1; x <= candidates; x++) {
				if (!willWin[x] && possible[x] >= 1) {
					curr = cloneList(adj);
					// RESET THE THE EDGE FROM SOURCE TO STATES
					for (int y = 1; y <= states; y++) {
						int index = curr.get(0).indexOf(new Edge(y, 0));
						curr.get(0).set(index, new Edge(y, 1));
					}
					// SET THE EDGES TO THE CANDIDATES TO 0
					for (int y = 1; y <= states; y++) {
						int index = curr.get(y)
								.indexOf(new Edge(states + x, 0));
						if (index != -1) {
							index = curr.get(0).indexOf(new Edge(y, 0));
							curr.get(0).set(index, new Edge(y, 0));
						}
					}
					// SET THE EDGES FROM THE CANDIDATES TO THE SINK TO POSSIBLE
					for (int y = states + 1; y <= states + candidates; y++) {

						int index = curr.get(y).indexOf(
								new Edge(states + candidates + 1, 0));
						curr.get(y).set(
								index,
								new Edge(states + candidates + 1,
										possible[x] - 1));
					}
					// REMOVE THE EDGE FROM THE CANDIDATE TO THE SINK
					int index = curr.get(x + states).indexOf(
							new Edge(states + candidates + 1, 0));
					curr.get(x + states).remove(index);

					boolean maxFlow = maxFlow();
					if (maxFlow)
						mightWin[x] = true;
				}
			}
			for (int x = 1; x <= candidates; x++) {
				if (willWin[x] && possible[x] > 0)
					System.out.println("Candidate " + x
							+ " will become president.");
				else if (mightWin[x] && possible[x] > 0)
					System.out.println("Candidate " + x
							+ " may become president.");
				else
					System.out.println("Candidate " + x
							+ " will not become president.");
			}
			states = readInt();
			candidates = readInt();
		}
	}

	@SuppressWarnings ("unchecked")
	private static ArrayList<ArrayList<Edge>> cloneList (
			ArrayList<ArrayList<Edge>> adj) {
		ArrayList<ArrayList<Edge>> t = new ArrayList<ArrayList<Edge>>();
		for (ArrayList<Edge> a : adj)
			t.add((ArrayList<Edge>) a.clone());
		return t;
	}

	private static boolean maxFlow () {

		int augment = bfs();
		while (augment != 0) {
			augment = bfs();
		}
		for (int x = 0; x < curr.get(0).size(); x++) {
			if (curr.get(0).get(x).cost != 0)
				return false;
		}
		return true;
	}

	private static int bfs () {
		int d = states + candidates + 1;
		Queue<State> moves = new LinkedList<State>();
		int[] max = new int[states + candidates + 2];
		int[] prev = new int[states + candidates + 2];
		for (int x = 0; x < states + candidates + 2; x++) {
			max[x] = Integer.MAX_VALUE;
			prev[x] = -2;
		}
		prev[0] = -1;
		max[0] = 0;
		moves.offer(new State(0, Integer.MAX_VALUE));
		while (!moves.isEmpty()) {
			State currState = moves.poll();
			if (currState.index == d)
				break;
			for (int x = 0; x < curr.get(currState.index).size(); x++) {
				Edge next = curr.get(currState.index).get(x);

				if (Math.min(currState.max, next.cost) >= max[next.dest]
						|| next.cost == 0)
					continue;
				max[next.dest] = Math.min(currState.max, next.cost);
				prev[next.dest] = currState.index;
				moves.offer(new State(next.dest, max[next.dest]));
			}
		}
		if (prev[states + candidates + 1] == -2)
			return 0;
		int neck = max[states + candidates + 1];
		int c = states + candidates + 1;
		while (prev[c] != -1) {
			int index = curr.get(prev[c]).indexOf(new Edge(c, 0));
			int index1 = curr.get(c).indexOf(new Edge(prev[c], 0));
			int cost = curr.get(prev[c]).get(index).cost;
			int cost1 = curr.get(c).get(index1).cost;

			curr.get(prev[c]).set(index, new Edge(c, cost - neck));
			curr.get(c).set(index1, new Edge(prev[c], cost1 + neck));
			c = prev[c];
		}
		return neck;
	}

	static class State {
		int max;
		int index;

		State (int index, int max) {
			this.index = index;
			this.max = max;
		}
	}

	static class Edge {
		int cost;
		int dest;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Edge) {
				Edge e = (Edge) o;
				return e.dest == dest;
			}
			return false;
		}

		@Override
		public String toString () {
			return "Dest: " + dest + "; Cost:" + cost;
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