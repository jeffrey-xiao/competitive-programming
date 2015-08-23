package contest.ioi;

import java.util.Scanner;

public class IOI_2000_Palindrome {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int length = scan.nextInt();
		scan.nextLine();
		String s1 = scan.nextLine();
		int[][] dp = new int[2][length + 1];
		for (int x = 1; x <= length; x++) {
			for (int y = 1; y <= length; y++) {
				if (s1.charAt(length - y) == s1.charAt(x - 1))
					dp[x % 2][y] = dp[(x - 1) % 2][y - 1] + 1;
				else
					dp[x % 2][y] = Math.max(dp[(x - 1) % 2][y],
							dp[x % 2][y - 1]);
				// System.out.print(dp[x%2][y] + " ");
			}
			// System.out.println();
		}
		System.out.println(length - dp[length % 2][length]);
	}
}
