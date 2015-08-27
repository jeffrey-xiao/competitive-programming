package contest.smac;

import java.util.*;
import java.io.*;

public class SMAC_2008_Daycare {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		long[] sz = new long[n];
		for (int i = 0; i < n; i++)
			sz[i] = readInt();
		Stack<State> s = new Stack<State>();
		s.push(new State(1l<<40, 1));
		s.push(new State(sz[n-1], 1));
		for (int i = n-2; i >= 0; i--) {
			if (s.peek().num == 0) {
				s.push(new State(0, s.pop().occ + 1));
			} else {
				s.push(new State(0, 1));
			}
			
			while (sz[i] > 0) {
				State curr = s.pop();
				State next = s.pop();
				if ((next.num - curr.num) * curr.occ <= sz[i]) {
					sz[i] -= (next.num - curr.num) * curr.occ;
					s.push(new State(next.num, next.occ + curr.occ));
				} else {
					long inc = sz[i] / (curr.occ);
					long leftover = sz[i] - (inc * curr.occ);
					if (curr.num + inc == next.num && leftover == 0) {
						next.occ += curr.occ;
						s.push(next);
					} else if (curr.num + inc + 1 == next.num) {
						next.occ += leftover;
						curr.occ -= leftover;
						curr.num += inc;
						s.push(next);
						s.push(curr);
					} else {
						s.push(next);
						if (leftover > 0) {
							State newS = new State(curr.num + inc + 1, leftover);
							s.push(newS);
						}
						if (leftover != curr.occ) {
							curr.occ -= leftover;
							curr.num += inc;
							s.push(curr);
						}
					}
					sz[i] = 0;
				}
			}
		}
		long ans = 0;
		for (State state : s) {
			ans += state.num*state.num*state.occ;
		}
		pr.println(ans);
		pr.close();
	}
	static class State {
		long num, occ;
		State (long num, long occ) {
			this.num = num;
			this.occ = occ;
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
