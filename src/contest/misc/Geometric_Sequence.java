package contest.misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Geometric_Sequence {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		Double[] nums = new Double[scan.nextInt()];
		for (int x = 0; x < nums.length; x++)
			nums[x] = scan.nextDouble();
		Arrays.sort(nums, new Comparator<Double>() {

			@Override
			public int compare (Double o1, Double o2) {
				if (Math.abs(o1) < Math.abs(o2))
					return -1;
				else
					return 1;
			}
		});
		double maxRatio = 0;
		for (int x = 0; x < nums.length - 1; x++) {
			if (maxRatio == 0) {
				maxRatio = nums[x + 1] / nums[x];
				continue;
			}
			double log = Math.log(Math.abs(maxRatio)) / Math.log(Math.abs(nums[x + 1] / nums[x]));
			if (log < 1)
				log = 1.0 / log;
			if (log % 1.0 == 0 && Math.abs(nums[x + 1] / nums[x]) < Math.abs(maxRatio))
				maxRatio = nums[x + 1] / nums[x];
			else if (log % 1.0 != 0) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(maxRatio);
	}

}
