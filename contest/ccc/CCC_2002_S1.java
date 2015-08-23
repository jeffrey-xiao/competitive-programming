package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2002_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int cost1 = readInt();
		int cost2 = readInt();
		int cost3 = readInt();
		int cost4 = readInt();
		int total = readInt();
		int min = 10000;
		int count = 0;
		for (int x = 0; x <= total / cost1; x++) {
			for (int y = 0; y <= total / cost2; y++) {
				for (int z = 0; z <= total / cost3; z++) {
					for (int i = 0; i <= total / cost4; i++) {
						if (x * cost1 + y * cost2 + z * cost3 + i * cost4 == total) {
							System.out
									.printf("# of PINK is %d # of GREEN is %d # of RED is %d # of ORANGE is %d\n",
											x, y, z, i);
							count++;
							if (x + y + z + i < min)
								min = x + y + z + i;
						}
					}
				}
			}
		}
		System.out.printf("Total combinations is %d.\n", count);
		System.out.printf("Minimum number of tickets to print is %d.\n", min);
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
