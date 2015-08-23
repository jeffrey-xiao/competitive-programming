package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class IOI_2013_Robots_Bad {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	/*
	 * 2 1 3 2 5 2 3 1 5 3 2 2
	 */
	public static void main (String[] args) throws IOException {
		int a = readInt();
		int b = readInt();
		int n = readInt();
		int[] weak = new int[a];
		int[] small = new int[b];
		for (int x = 0; x < a; x++)
			weak[x] = readInt();
		for (int x = 0; x < b; x++)
			small[x] = readInt();
		Arrays.sort(weak);
		Arrays.sort(small);
		ArrayList<Toy> toys = new ArrayList<Toy>();
		for (int x = 0; x < n; x++)
			toys.add(new Toy(readInt(), readInt()));
		Comparator<Toy> c2 = new Comparator<Toy>() {
			@Override
			public int compare (Toy o1, Toy o2) {
				if (o1.weight == o2.weight)
					return o1.size - o2.size;
				return o1.weight - o2.weight;
			}
		};
		Comparator<Toy> c1 = new Comparator<Toy>() {
			@Override
			public int compare (Toy o1, Toy o2) {
				if (o1.size == o2.size)
					return o1.weight - o2.weight;
				return o1.size - o2.size;
			}
		};
		int time = 0;
		boolean flag = true;
		while (toys.size() != 0 && flag) {
			Collections.sort(toys, c1);
			flag = false;
			for (int y = 0; y < weak.length; y++) {
				for (int x = toys.size() - 1; x >= 0; x--) {
					if (toys.get(x).weight < weak[y]) {
						toys.remove(x);
						flag = true;
						break;
					}
				}
			}
			Collections.sort(toys, c2);
			for (int y = 0; y < small.length; y++) {
				for (int x = toys.size() - 1; x >= 0; x--) {
					if (toys.get(x).size < small[y]) {
						toys.remove(x);
						flag = true;
						break;
					}
				}
			}
			time++;
		}
		System.out.println(toys.size() != 0 ? -1 : time);
	}

	static class Toy {
		int weight;
		int size;

		Toy (int weight, int size) {
			this.weight = weight;
			this.size = size;
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