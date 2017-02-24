package contest.ccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CCC_2007_S4 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int destination = scan.nextInt();
		ArrayList<List<Integer>> slides = new ArrayList<List<Integer>>();
		int a;
		while ((a = scan.nextInt()) != 0)
			slides.add(Arrays.asList(new Integer[] {a, scan.nextInt()}));
		scan.nextInt();
		Collections.sort(slides, new Comparator<Object>() {
			@Override
			@SuppressWarnings("unchecked")
			public int compare (Object arg0, Object arg1) {
				return ((List<Integer>)arg0).get(1) < ((List<Integer>)arg1).get(1) ? 1 : -1;
			}
		});
		int[] dp = new int[destination];
		dp[destination - 1] = 1;
		for (List<Integer> x : slides) {
			dp[x.get(0) - 1] += dp[x.get(1) - 1];
		}
		System.out.println(dp[0]);
	}
}
