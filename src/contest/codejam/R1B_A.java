package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class R1B_A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int q = 1; q <= t; q++) {
			long n = readLong();
			HashMap<Long, Integer> forward = new HashMap<Long, Integer>();
			HashMap<Long, Integer> reverse = new HashMap<Long, Integer>();
			Queue<State> qq = new LinkedList<State>();
			qq.offer(new State(1, 1, true));
			qq.offer(new State(n, 1, false));
			forward.put(1l, 1);
			reverse.put(n, 1);
			while (!qq.isEmpty()) {
				State i = qq.poll();
				if (forward.containsKey(i.index) && reverse.containsKey(i.index)) {
					pr.printf("Case #%d: %d\n", q, forward.get(i.index) + reverse.get(i.index) - 1);
					break;
				}
				long rev = reverse(i.index);
				if (i.isFor) {
					if (!forward.containsKey(rev)) {
						forward.put(rev, i.dist + 1);
						qq.offer(new State(rev, i.dist + 1, true));
					}
					if (!forward.containsKey(i.index + 1)) {
						forward.put(i.index + 1, i.dist + 1);
						qq.offer(new State(i.index + 1, i.dist + 1, true));
					}
				} else {

					if (i.index % 10 != 0 && !reverse.containsKey(rev)) {
						reverse.put(rev, i.dist + 1);
						qq.offer(new State(rev, i.dist + 1, false));
					}
					if (!reverse.containsKey(i.index - 1)) {
						reverse.put(i.index - 1, i.dist + 1);
						qq.offer(new State(i.index - 1, i.dist + 1, false));
					}
				}
			}
		}

		pr.close();
	}

	private static long reverse (long index) {
		String res = "";
		while (index != 0) {
			res += index % 10;
			index /= 10;
		}
		return Long.parseLong(res);
	}

	static class State {
		long index;
		int dist;
		boolean isFor;

		State (long index, int dist, boolean isFor) {
			this.index = index;
			this.dist = dist;
			this.isFor = isFor;
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
