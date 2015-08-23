package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Partitions {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] values;
	static int start;

	@SuppressWarnings ("unused")
	public static void main (String[] args) throws IOException {
		testcases : for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int index = readInt();
			int startIndex = index;
			start = n;
			// initial
			values = new int[n + 1];
			compute(values);
			values[n] = 1;
			for (int x = 0; x < values.length; x++)
				System.out.print(values[x] + " ");
			System.out.println();

			values[n] = 1;
			String s = "";

			int next = 0;
			int sum = 0;
			main : while (index != 0 || sum == n) {
				int currSum = 0;

				boolean flag = true;
				for (int x = 1; x < values.length; x++) {
					currSum += values[x];
					if (currSum >= index) {
						flag = false;
						if (index == 1)
							index = 0;

						else
							index = index - (currSum - values[x]);
						/*
						 * System.out.println(index + " " + flag); for(int y =
						 * 0; y < values.length; y++) System.out.print(values[y]
						 * + " "); System.out.println();
						 */
						next = x;
						sum += x;

						s += x + ",";
						if (sum == n)
							break main;
						// System.out.println(index);
						break;
					}

				}
				if (flag) {
					System.out.println("Too big");
					continue testcases;
				}
				values = new int[index + 2];
				compute(values);

			}
			while (n != sum) {
				s += "1,";
				sum++;
			}
			System.out.println("(" + s.substring(0, s.length() - 1) + ")");
			System.out.println(t);
		}
		System.out.println("WHY GO OUT");
	}

	private static void compute (int[] values) {
		int curr = 1;
		for (int x = 1; x < values.length - 1; x++) {
			values[x] = curr;
			if (x < (values.length - 1) / 2)
				curr *= 2;
			else if (x >= values.length / 2)
				curr /= 2;
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
/*
 * private static int compute(int n, int sum, int prev) { int total = 0; if(n ==
 * 0) return 1; if(prev < n){ if(n > start) return 0; values[n] = values[prev];
 * return values[prev]; } for(int x = Math.min(prev,n); x >= 1 ; x--){
 * if(values[n-x] != 0){ total+=values[n-x]; } else{ ; total+=compute(n-x, 0,
 * x); } }
 * 
 * values[n] = total; for(int x = 0; x < values.length; x++)
 * System.out.print(values[x] + " "); System.out.println(); return total; }
 */
