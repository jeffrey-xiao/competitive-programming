package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_Stage_2_Oil_Droplet {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		double d1 = Double.MAX_VALUE, d2 = Double.MAX_VALUE / 2;
		int n = readInt();
		double[] charges = new double[n];
		for (int x = 0; x < n; x++)
			charges[x] = readDouble();
		while (d1 != d2) {
			d1 = d2;
			for (int x = 0; x < n; x++) {
				double newCharge = Math.floor(charges[x] * 1.01d / d2);
				if (newCharge * d2 < charges[x] * 0.99)
					d2 = charges[x] * 1.01d / (newCharge + 1);
				System.out.println(d2);
			}

		}
		System.out.printf("%.4f", d2);
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
