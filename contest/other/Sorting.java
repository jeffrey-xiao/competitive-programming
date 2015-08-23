package contest.other;

import java.util.Arrays;
import java.util.Scanner;

public class Sorting {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int[] nums = new int[scan.nextInt()];
		for (int x = 0; x < nums.length; x++)
			nums[x] = scan.nextInt();
		Arrays.sort(nums);
		for (int x : nums)
			System.out.println(x);
	}
}
