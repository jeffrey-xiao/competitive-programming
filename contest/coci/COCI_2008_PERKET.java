package contest.coci;

import java.util.Scanner;

public class COCI_2008_PERKET {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int ingredients = scan.nextInt();
		int[][] items = new int[ingredients][2];
		for (int x = 0; x < items.length; x++) {
			items[x][0] = scan.nextInt();
			items[x][1] = scan.nextInt();
		}
		int min = minValue(items, 0, 1, 0);

		System.out.println(min);
	}

	private static int minValue (int[][] items, int i, int s, int b) {

		if (i == items.length - 1) {
			if (s == 1 && b == 0)
				return diff(items[i][0], items[i][1]);
			else {
				return Math.min(diff(s, b),
						diff(items[i][0] * s, items[i][1] + b));
			}
		}
		int d1 = minValue(items, i + 1, s, b);
		int d2 = minValue(items, i + 1, s * items[i][0], b + items[i][1]);
		return d1 < d2 ? d1 : d2;
	}

	private static int diff (int sour, int bitter) {
		return Math.abs(sour - bitter);
	}
}
