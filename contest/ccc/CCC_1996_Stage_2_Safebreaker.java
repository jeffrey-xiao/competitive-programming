package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1996_Stage_2_Safebreaker {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		main : for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			String[] nums = new String[n];
			int[][] guesses = new int[n][2];
			int count = 0;
			for (int x = 0; x < n; x++) {
				nums[x] = next();
				String s = next();
				guesses[x][0] = s.charAt(0) - 48;
				guesses[x][1] = s.charAt(2) - 48;

			}
			String code = "";
			// System.out.println(getMisplaced(new int[]{3,4,1,1},3383));

			for (int a = 0; a <= 9; a++) {
				for (int b = 0; b <= 9; b++) {
					for (int c = 0; c <= 9; c++) {
						inner : for (int d = 0; d <= 9; d++) {
							for (int x = 0; x < n; x++) {
								int correct = guesses[x][0];
								int misplace = guesses[x][1];
								int numCorrect = getCorrect(new int[] {a, b,
										c, d}, nums[x]);
								int numMisplaced = getMisplaced(new int[] {a,
										b, c, d}, nums[x]);
								/*
								 * if(a==3&&b==4&&c==1&&(d==0||d==1)){
								 * System.out.println(numCorrect + " " +
								 * numMisplaced); }
								 */
								if (correct != numCorrect
										|| misplace != numMisplaced
												- numCorrect) {
									continue inner;
								}
							}
							count++;
							code = ("" + a + b + c + d);
							if (count > 1) {
								System.out.println("indeterminate");
								continue main;
							}
						}
					}
				}
			}
			if (count == 0)
				System.out.println("impossible");
			else
				System.out.println(code);
		}
	}

	private static int getCorrect (int[] guess, String i) {
		int count = 0;
		StringBuilder s = new StringBuilder(i);
		for (int x = 0; x < 4; x++) {
			if (guess[x] == s.charAt(x) - 48) {
				count++;
			}

		}
		return count;
	}/*
	 * 6 9793 0/1 2384 0/2 6264 0/1 3383 1/0 2795 0/0 0218 1/0
	 */

	private static int getMisplaced (int[] guess, String i) {
		StringBuilder s = new StringBuilder(i);
		int count = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < s.length(); y++) {
				if (guess[x] == s.charAt(y) - 48) {
					count++;
					s.deleteCharAt(y);
					break;
				}
			}
		}
		return count;
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
