package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Kinako_Bread_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static char[] a;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] v;
	static ArrayList<State> states;
	static int[] bit = new int[200001];
	
	static int lk, rk, lc, rc;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		
		lk = readInt();
		rk = readInt();
		lc = readInt();
		rc = readInt();
		
		states = new ArrayList<State>();
		adj = new ArrayList<ArrayList<Integer>>();
		v = new boolean[n];
		
		for (int i = 0; i < n; i++) 
			adj.add(new ArrayList<Integer>());
		
		a = readLine().toCharArray();
		
		for (int i = 0; i < n - 1; i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		
		long ans = 0;

		while (!q.isEmpty()) {
			states.clear();
			int curr = q.poll();
			
			int centroid = getCentroid(curr, -1, getSize(curr, -1));
			
			int cntC = 0, cntK = 0;
			
			if (a[centroid] == 'C')
				cntC++;
			else
				cntK++;

			states.add(new State(0, 0));

			v[centroid] = true;
			
			for (int next : adj.get(centroid))
				if (!v[next]) {
					q.offer(next);
					ArrayList<State> currStates = new ArrayList<State>();
					computeStates(next, centroid, 0, 0, currStates);
					
					Collections.sort(currStates);
					
					int left = 0;
					int right = -1;
					
					for (int i = 0; i < currStates.size(); i++) { // computing all valid paths with i
						while (left < i && currStates.get(i).cntC + currStates.get(left).cntC + cntC < lc) {
							update(currStates.get(left++).cntK, -1);
						}
						while (left - 1 >= 0 && currStates.get(i).cntC + currStates.get(left - 1).cntC + cntC >= lc) {
							update(currStates.get(--left).cntK, 1);
						}
						while (right + 1 < i && currStates.get(i).cntC + currStates.get(right+1).cntC + cntC <= rc) {
							update(currStates.get(++right).cntK, 1);
						}
						while (right >= 0 && currStates.get(i).cntC + currStates.get(right).cntC + cntC > rc) {
							update(currStates.get(right--).cntK, -1);
						}
						ans -= Math.max(0, (query(rk - currStates.get(i).cntK - cntK) - query(lk - currStates.get(i).cntK - cntK - 1)));
					}
					while (left - 1 >= 0)
						update(currStates.get(--left).cntK, 1);
					while (right >= 0)
						update(currStates.get(right--).cntK, -1);
				}
			Collections.sort(states);

			int left = 0;
			int right = -1;
			
			for (int i = 0; i < states.size(); i++) { // computing all valid paths with i
				while (left < i && states.get(i).cntC + states.get(left).cntC + cntC < lc) {
					update(states.get(left++).cntK, -1);
				}
				while (left - 1 >= 0 && states.get(i).cntC + states.get(left - 1).cntC + cntC >= lc) {
					update(states.get(--left).cntK, 1);
				}
				while (right + 1 < i && states.get(i).cntC + states.get(right+1).cntC + cntC <= rc) {
					update(states.get(++right).cntK, 1);
				}
				while (right >= 0 && states.get(i).cntC + states.get(right).cntC + cntC > rc) {
					update(states.get(right--).cntK, -1);
				}
				ans += Math.max(0, (query(rk - states.get(i).cntK - cntK) - query(lk - states.get(i).cntK - cntK - 1)));
			}
			while (left - 1 >= 0)
				update(states.get(--left).cntK, 1);
			while (right >= 0)
				update(states.get(right--).cntK, -1);
			if (lc <= cntC && cntC <= rc && lk <= cntK && cntK <= rk)
				ans++;
		}
		out.println(ans);
		out.close();
	}

	static int query (int idx) {
		idx++;
		int sum = 0;
		for (int x = idx; x > 0; x -= (x & -x))
			sum += bit[x];
		return sum;
	}
	
	static void update (int idx, int val) {
		idx++;
		for (int x = idx; x <= 200000; x += (x & -x))
			bit[x] += val;
	}
	
	static void computeStates (int curr, int par, int cntC, int cntK, ArrayList<State> currStates) {
		if (a[curr] == 'C')
			cntC++;
		else
			cntK++;
		currStates.add(new State(cntC, cntK));
		states.add(new State(cntC, cntK));
		
		for (int next : adj.get(curr)) {
			if (next == par || v[next])
				continue;
			computeStates(next, curr, cntC, cntK, currStates);
		}
	}
	
	static class State implements Comparable<State> {
		int cntC, cntK;
		
		State (int cntC, int cntK) {
			this.cntC = cntC;
			this.cntK = cntK;
		}
		
		@Override
		public int compareTo (State s) {
			if (cntC == s.cntC)
				return cntK - s.cntK;
			return cntC - s.cntC;
		}
	}
	
	static int getSize (int curr, int par) {
	    int sz = 1;
	    for (int next : adj.get(curr))
	        if (next != par && !v[next])
	            sz += getSize(next, curr);
	    return sz;
	}
	
	static int getCentroid (int curr, int par, int treeSize) {
		int n = treeSize;
		int sz = 1;
		boolean valid = true;
		for (int next : adj.get(curr)) {
			if (next == par || v[next])
				continue;
			int ret = getCentroid(next, curr, treeSize);
			if (ret >= 0)
				return ret;
			valid &= -ret <= n / 2;
			sz += -ret;
		}
		valid &= n - sz <= n / 2;
		return valid ? curr : -sz;
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

