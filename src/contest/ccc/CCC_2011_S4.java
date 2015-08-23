package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2011_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	// 0 1 2 3 4 5 6 7
	// O- O+ A- A+ B- B+ AB- AB+
	static int[] b = new int[8];
	static int[] p = new int[8];

	public static void main (String[] args) throws IOException {

		for (int x = 0; x < 8; x++)
			b[x] = readInt();
		for (int x = 0; x < 8; x++)
			p[x] = readInt();

		int total = 0, total1 = 0, total2 = 0;
		// O blood
		total += get(0, 0) + get(1, 1) + get(0, 1);

		// A- blood
		total += get(2, 2) + get(0, 2);
		// B- blood
		total += get(4, 4) + get(0, 4);
		int[] tb = Arrays.copyOf(b, b.length);
		int[] tp = Arrays.copyOf(p, p.length);
		// A/B+ blood
		total1 += get(3, 3) + get(2, 3);
		total1 += get(5, 5) + get(4, 5);
		total1 += get(0, 3) + get(1, 3);
		total1 += get(0, 5) + get(1, 5);
		// ALL THE BLOOD
		total1 += get(6, 6) + get(4, 6) + get(2, 6) + get(0, 6);
		total1 += get(7, 7) + get(6, 7) + get(5, 7) + get(4, 7) + get(3, 7)
				+ get(2, 7) + get(1, 7) + get(0, 7);

		p = tp;
		b = tb;
		// A/B+ blood
		// A/B+ blood
		total2 += get(3, 3) + get(1, 3);
		total2 += get(5, 5) + get(1, 5);
		total2 += get(2, 3) + get(0, 3);
		total2 += get(4, 5) + get(0, 5);
		// ALL THE BLOOD
		total2 += get(6, 6) + get(4, 6) + get(2, 6) + get(0, 6);
		total2 += get(7, 7) + get(6, 7) + get(5, 7) + get(4, 7) + get(3, 7)
				+ get(2, 7) + get(1, 7) + get(0, 7);

		System.out.println(total + Math.max(total1, total2));
	}

	public static int get (int x, int y) {
		int z = Math.min(b[x], p[y]);
		b[x] -= z;
		p[y] -= z;
		return z;
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