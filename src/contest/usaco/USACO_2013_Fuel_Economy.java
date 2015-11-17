package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class USACO_2013_Fuel_Economy {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int maxGas = readInt();
		int curr = readInt();
		int end = readInt();
		Station[] stations = new Station[n];
		for (int x = 0; x < n; x++)
			stations[x] = new Station(readInt(), readInt());
		Arrays.sort(stations);
		int[] nextSmall = new int[n];
		Stack<Integer> s = new Stack<Integer>();
		for (int x = n - 1; x >= 0; x--) {
			while (!s.isEmpty() && (stations[s.peek()].cost > stations[x].cost)) {
				s.pop();
			}
			if (s.isEmpty())
				nextSmall[x] = -1;
			else
				nextSmall[x] = s.peek();
			s.push(x);
		}
		curr -= stations[0].pos;
		long cost = 0;
		int currPos = 0;
		while (currPos != n - 1) {
			int nextPos = 0;
			int nextGas = 0;
			if (nextSmall[currPos] == -1 || stations[nextSmall[currPos]].pos - stations[currPos].pos > maxGas) {
				nextPos = currPos + 1;
				nextGas = maxGas;
			} else {
				nextPos = nextSmall[currPos];
				nextGas = stations[nextPos].pos - stations[currPos].pos;
			}

			if (nextGas > curr) {
				cost += (nextGas - curr) * stations[currPos].cost;
				curr = nextGas;
			}
			curr -= stations[nextPos].pos - stations[currPos].pos;
			currPos = nextPos;
		}
		if (end - stations[currPos].pos > maxGas)
			System.out.println("-1");
		else {
			cost += Math.max((end - stations[currPos].pos) - curr, 0) * stations[currPos].cost;
			System.out.println(cost);
		}
	}

	static class Station implements Comparable<Station> {
		int pos;
		int index;
		int cost;

		Station (int pos, int cost) {
			this.pos = pos;
			this.cost = cost;
		}

		@Override
		public int compareTo (Station o) {
			return pos - o.pos;
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
