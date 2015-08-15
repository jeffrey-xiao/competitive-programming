package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2011_Hay_Bale {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfHaybales = readInt();
		int[] hayBales = new int[numOfHaybales];
		int sum = 0;
		for (int x = 0; x < numOfHaybales; x++) {
			hayBales[x] = readInt();
			sum += hayBales[x];
		}
		sum /= numOfHaybales;
		int total = 0;
		for (int i : hayBales)
			if (i < sum)
				total += sum - i;
		System.out.println(total);
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
