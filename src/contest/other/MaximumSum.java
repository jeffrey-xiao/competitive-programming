package contest.other;

import java.util.Scanner;

class MaximumSum {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int length = scan.nextInt();
		int[] nums = new int[length];
		for (int x = 0; x < length; x++)
			nums[x] = scan.nextInt();
		int[] table = new int[length];
		table[0] = nums[0];
		for (int x = 1; x < table.length; x++) {
			int a = x - 2 >= 0 ? table[x - 2] + nums[x] : nums[x];
			int b = x - 1 >= 0 ? table[x - 1] : 0;
			table[x] = max(a, b);
		}
		System.out.println(table[length - 1]);
	}

	public static int max (int a, int b) {
		return a > b ? a : b;
	}
}