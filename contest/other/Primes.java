package contest.other;

import java.util.Scanner;

public class Primes {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int end = 542; // where you want to end
		int num = scan.nextInt();// first how many primes
		boolean[] primes = new boolean[end];
		primes[0] = true;
		for (int x = 1; x < primes.length; x += 2)
			if (x + 1 % 2 == 0)
				primes[x] = true;
		for (int x = 1; x * x < primes.length; x++)
			if (primes[x] == false)
				for (int y = (x + 1) * (x + 1) - 1; y < primes.length; y += (x + 1))
					primes[y] = true;
		// printing
		int count = 0;
		for (int x = 1; x <= end && count < num; x++) {
			if (!primes[x - 1]) {
				System.out.println(x);
				count++;
			}
		}
		scan.close();
	}

}
