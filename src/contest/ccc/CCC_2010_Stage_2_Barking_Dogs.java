package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Barking_Dogs {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] dogs = new int[n];
		ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++) {
			dogs[x] = readInt();
			adjlist.add(new ArrayList<Integer>());
		}
		int m = readInt();
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adjlist.get(a).add(b);
		}
		int time = readInt();
		int[] bark = new int[n];
		PriorityQueue<Dog> moves = new PriorityQueue<Dog>();
		moves.add(new Dog(0, 0));
		bark[0] = 0;
		int[] total = new int[n];
		while (!moves.isEmpty()) {
			Dog curr = moves.poll();
			if (curr.time > time)
				continue;
			bark[curr.id] = 0;
			total[curr.id]++;
			for (int x = 0; x < adjlist.get(curr.id).size(); x++) {
				int next = adjlist.get(curr.id).get(x);
				if ((bark[next] != 0 && curr.time + dogs[next] > bark[next]))
					continue;
				moves.remove(new Dog(next, curr.time + dogs[next]));
				bark[next] = curr.time + dogs[next];
				moves.offer(new Dog(next, curr.time + dogs[next]));
			}
		}
		for (int x : total)
			System.out.println(x);
	}

	static class Dog implements Comparable<Dog> {
		int id;
		int time;

		Dog (int id, int time) {
			this.id = id;
			this.time = time;
		}

		@Override
		public int compareTo (Dog o) {
			return this.time - o.time;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Dog) {
				Dog d = (Dog) o;
				return this.id == d.id;
			}
			return false;
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
