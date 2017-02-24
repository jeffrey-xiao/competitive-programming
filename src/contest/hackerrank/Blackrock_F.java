package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Blackrock_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, K;

	static Stock[] s, sorted;
	static boolean[] used;
	static boolean[] usedK;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		K = readInt();

		sorted = new Stock[N];
		s = new Stock[N];
		used = new boolean[N];
		usedK = new boolean[N];

		for (int i = 0; i < N; i++) {
			long p = readInt();
			long c = readInt();
			s[i] = new Stock(p * c, 100 * p, i);
			sorted[i] = new Stock(p * c, 100 * p, i);
		}

		Arrays.sort(sorted, new Comparator<Stock>() {
			@Override
			public int compare (Stock s1, Stock s2) {
				if (s1.expected.equals(s2.expected))
					return s1.price.compareTo(s2.price);
				return s2.expected.compareTo(s1.expected);
			}
		});

		long ans = 0;

		PriorityQueue<State> pq = new PriorityQueue<State>();
		PriorityQueue<State> unusedVal = new PriorityQueue<State>();
		PriorityQueue<State> usedVal = new PriorityQueue<State>(1, new Comparator<State>() {
			@Override
			public int compare (State o1, State o2) {
				return o1.val.compareTo(o2.val);
			}
		});

		for (int i = 0; i < M; i++) {
			ans += sorted[i].expected;
			pq.offer(new State(sorted[i].price - sorted[i].expected, sorted[i].index));
			usedVal.offer(new State(sorted[i].expected, sorted[i].index));
			used[sorted[i].index] = true;
		}

		for (int i = M; i < N; i++) {
			unusedVal.offer(new State(sorted[i].price, sorted[i].index));
		}

		for (int i = 0; i < K; i++) {
			while (!pq.isEmpty() && !used[pq.peek().index])
				pq.poll();

			while (!usedVal.isEmpty() && usedK[usedVal.peek().index])
				usedVal.poll();

			if (unusedVal.isEmpty()) {
				State s = pq.poll();
				ans += s.val;
				usedK[s.index] = true;
			} else {
				if (unusedVal.peek().val - usedVal.peek().val > pq.peek().val) {
					State s1 = unusedVal.poll();
					State s2 = usedVal.poll();
					ans += s1.val - s2.val;
					used[s1.index] = true;
					used[s2.index] = false;
					unusedVal.offer(new State(s[s2.index].price, s2.index));
				} else {
					State s = pq.poll();
					ans += s.val;
					usedK[s.index] = true;
				}
			}
		}

		out.println(ans);
		out.close();
	}

	static class State implements Comparable<State> {
		Long val;
		int index;

		State (long val, int index) {
			this.val = val;
			this.index = index;
		}

		@Override
		public int compareTo (State s) {
			return s.val.compareTo(val);
		}

	}

	static class Stock {
		Long expected, price;
		int index;

		Stock (long expected, long price, int index) {
			this.expected = expected;
			this.price = price;
			this.index = index;
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
