package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2004_Stage_2_Orko {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[] isA;
	static byte[][] dp = new byte[1 << 20][2];

	public static void main (String[] args) throws IOException {
		String in = readLine();
		while (!in.equals("* * * * * * * * * *")) {
			String[] s = in.split(" ");
			isA = new boolean[20];
			for (int i = 0; i < (1 << 20); i++) {
				for (int j = 0; j < 2; j++) {
					dp[i][j] = -1;
				}
			}
			for (int i = 0; i < s.length; i++) {
				int num = 0;
				if (s[i].charAt(0) == 'R')
					num = 0;
				else if (s[i].charAt(0) == 'Y')
					num = 5;
				else if (s[i].charAt(0) == 'G')
					num = 10;
				else if (s[i].charAt(0) == 'B')
					num = 15;
				num += s[i].charAt(1) - '1';
				isA[num] = true;
			}
			// for (int i = 0; i < 20; i++) {
			// System.out.print(isA[i]?1:0);
			// }
			System.out.println(compute((1 << 20) - 1, true, (byte) 10));
			in = readLine();
		}
	}

	static int count = 0;

	private static byte compute (int i, boolean isATurn, byte size) {

		if (size == 0)
			return 0;
		if (dp[i][isATurn ? 1 : 0] != -1)
			return dp[i][isATurn ? 1 : 0];
		byte best = 0;
		// System.out.println(Integer.toString(i, 2) + " " + (count++) + " " +
		// size);
		for (int j = 0; j < 20; j++) {
			if ((i & 1 << j) != 0 && isA[j] == isATurn) {
				byte min = 10;
				i ^= 1 << j;
				// System.out.println(min);
				// playing same suit
				boolean valid = false;
				for (int k = 0; k < 5; k++) {
					int next = (j / 5) * 5 + k;
					if ((i & 1 << next) != 0 && isA[next] == !isATurn) {
						valid = true;
						i ^= 1 << next;
						// other player wins
						if (k > j % 5) {
							// System.out.println("LOST " + j + " " + next);
							min = (byte) Math.min(
									min,
									size
											- 1
											- compute(i, !isATurn,
													(byte) (size - 1)));
						} else {
							// System.out.println("WON " + j + " " + next);
							min = (byte) Math.min(min,
									1 + compute(i, isATurn, (byte) (size - 1)));
						}
						i ^= 1 << next;
					}
				}
				// playing different suit
				if (!valid) {
					// System.out.println("INVALID");
					// System.out.println(Integer.toString(i, 2) + " " + isATurn
					// + " " + j);
					for (int k = 0; k < 20; k++) {

						if ((i & 1 << k) != 0 && isA[k] != isATurn) {
							i ^= (1 << k);
							min = (byte) Math.min(min,
									1 + compute(i, isATurn, (byte) (size - 1)));
							i ^= (1 << k);
						}

					}
				}
				i ^= 1 << j;
				// System.out.println(min);
				best = (byte) Math.max(best, min);
			}
		}
		return dp[i][isATurn ? 1 : 0] = best;
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