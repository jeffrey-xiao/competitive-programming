package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DMOPC_2014_Kittans_Dilemma_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<Integer> weak = new ArrayList<Integer>();
		ArrayList<Integer> strong = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int a = readInt();
			int b = readInt();
			if (b == 1)
				weak.add(a);
			else
				strong.add(a);
		}
		Collections.sort(weak);
		Collections.sort(strong);
		long[] sumW = new long[weak.size() + 1];
		long[] sumS = new long[strong.size() + 1];
		for (int i = 1; i <= weak.size(); i++) {
			sumW[i] = weak.get(i - 1) + sumW[i - 1];
			// System.out.print(sumW[i] + " ");
		}
		// System.out.println();

		for (int i = 1; i <= strong.size(); i++) {
			sumS[i] = strong.get(i - 1) + sumS[i - 1];
		}
		int max = 0;
		for (int i = 0; i <= strong.size(); i++) {
			long spaceLeft = m - sumS[i];
			if (spaceLeft < 0)
				break;
			int lo = 0;
			int hi = weak.size();
			while (lo <= hi) {

				int mid = lo + (hi - lo) / 2;
				// System.out.println(sumW[mid] + " " + spaceLeft);
				if (sumW[mid] <= spaceLeft) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			max = Math.max(i * 2 + hi, max);
		}
		System.out.println(max);
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
