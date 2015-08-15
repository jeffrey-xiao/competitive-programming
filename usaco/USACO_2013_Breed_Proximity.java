package usaco;

import java.util.Scanner;

public class USACO_2013_Breed_Proximity {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		int k = scan.nextInt();
		int[] cows = new int[n];
		for (int x = 0; x < n; x++)
			cows[x] = scan.nextInt();
		int[] curr = new int[5000000];
		int max = 0;
		for (int x = 0; x < n; x++) {
			curr[cows[x]]++;
			if (x > k)
				curr[cows[x - k - 1]]--;
			if (curr[cows[x]] > 1 && cows[x] > max)
				max = cows[x];
		}
		System.out.println(max);
	}

}
