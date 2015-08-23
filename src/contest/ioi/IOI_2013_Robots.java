package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class IOI_2013_Robots {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

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
		Comparator<Toy> c1 = new Comparator<Toy>() {
			@Override
			public int compare (Toy o1, Toy o2) {
				if (o1.weight == o2.weight)
					return o1.size - o2.size;
				return o1.weight - o2.weight;
			}
		};
		Comparator<Toy> c2 = new Comparator<Toy>() {
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
			// System.out.println("weight");
			for (int y = 0; y < weak.length && toys.size() > 0; y++) {
				int i = getWeightIndex(toys, weak[y]);
				if (i != -1) {
					// System.out.println(weak[y] + " " + toys.get(i).weight);
					toys.remove(i);
					flag = true;
				}
			}
			Collections.sort(toys, c2);
			// System.out.println("size");
			for (int y = 0; y < small.length && toys.size() > 0; y++) {
				int i = getSizeIndex(toys, small[y]);
				if (i != -1) {
					// System.out.println(small[y] + " " + toys.get(i).weight);
					toys.remove(i);
					flag = true;
				}
			}
			time++;
			// System.out.println("Next iteration");
		}
		System.out.println(toys.size() != 0 ? -1 : time);
	}

	public static int getWeightIndex (ArrayList<Toy> t, int weak) {
		int lower = 0;
		int higher = t.size() - 1;
		while (higher - lower > 1) {
			int mid = (higher + lower) / 2;
			if (t.get(mid).weight < weak)
				lower = mid;
			else
				higher = mid;
		}
		if (t.get(higher).weight < weak)
			return higher;
		else if (t.get(lower).weight < weak)
			return lower;
		return -1;
	}

	public static int getSizeIndex (ArrayList<Toy> t, int small) {
		int lower = 0;
		int higher = t.size() - 1;
		while (higher - lower > 1) {
			int mid = (higher + lower) / 2;
			if (t.get(mid).size < small)
				lower = mid;
			else
				higher = mid;
		}
		if (t.get(higher).size < small)
			return higher;
		else if (t.get(lower).size < small)
			return lower;
		return -1;
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
