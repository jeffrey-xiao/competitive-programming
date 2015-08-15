package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bus_Stop {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int s = readInt();
		int c = readInt();
		int[] stops = new int[s + 1];
		for (int x = 0; x < n; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			stops[a]++;
			stops[b]--;
		}
		int curr = 0;
		long total = 0;
		for (int x = 0; x < s + 1; x++) {
			curr += stops[x];
			stops[x] = curr;
			// System.out.println(stops[x]);
			if (stops[x] > c)
				total += (stops[x] - c) * 5 + c;
			else
				total += stops[x];
		}
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
