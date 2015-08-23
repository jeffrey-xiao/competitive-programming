package contest.coci;

import java.util.Scanner;

public class COCI_2007_SEMAFORI {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		int d = scan.nextInt();
		int[][] stoplights = new int[n][3];
		for (int x = 0; x < n; x++) {
			stoplights[x][0] = scan.nextInt();
			stoplights[x][1] = scan.nextInt();
			stoplights[x][2] = scan.nextInt();
		}
		int time = 0;

		for (int x = 0; x < stoplights.length; x++) {
			if (x == 0)
				time = stoplights[x][0];
			else
				time += stoplights[x][0] - stoplights[x - 1][0];
			int r = stoplights[x][1];
			int g = stoplights[x][2];
			if (time % (r + g) < r)
				time += r - time % (r + g);
		}
		System.out.println(time + d - stoplights[n - 1][0]);
	}

}
