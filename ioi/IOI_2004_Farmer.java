package ioi;

import java.util.Scanner;

public class IOI_2004_Farmer {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int trees = scan.nextInt();
		int fields = scan.nextInt();
		int strips = scan.nextInt();
		int[] total = new int[fields + strips + 1];
		for (int x = 1; x < total.length; x++) {
			total[x] = scan.nextInt();
		}
		int[] dp = new int[trees + 1];
		for (int x = 1; x < total.length; x++) {
			for (int y = dp.length - 1; y >= 1; y--) {
				int max = -1;
				for (int z = y; z >= 1; z--) {
					int curr = 0;
					if (z < total[x])
						curr = z - 1;
					else if (x <= fields && z == total[x])
						curr = z;
					max = Math.max(curr + dp[y - z], max);
					if (max == y)
						break;
				}
				dp[y] = Math.max(max, dp[y]);
				// System.out.print(dp[y] + " ");
			}
			// System.out.println();
		}

		System.out.println(dp[trees]);
	}
}
