package contest.other;

import java.util.Arrays;
import java.util.Scanner;

public class Presents {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		long[] children = new long[scan.nextInt()];
		for (int x = 0; x < children.length; x++)
			children[x] = scan.nextLong();
		long[] present = new long[scan.nextInt()];
		for (int x = 0; x < present.length; x++)
			present[x] = scan.nextLong();
		Arrays.sort(children);
		Arrays.sort(present);
		long sum = 0;
		int indexC = children.length - 1;
		int indexP = present.length - 1;
		while (indexC != -1 && indexP != -1) {
			if (present[indexP] <= children[indexC]) {
				sum += (children[indexC] - present[indexP]);
				indexC--;
				indexP--;
			} else {
				indexP--;
			}
		}
		System.out.println(indexC == -1 ? sum : -1);
	}
}
