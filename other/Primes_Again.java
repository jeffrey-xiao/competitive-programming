package other;

import java.util.Scanner;

public class Primes_Again {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int start = scan.nextInt();
		int end = scan.nextInt();
		boolean[] primes = new boolean[end];
		primes[0] = true;
		for (int x = 1; x < primes.length; x += 2)
			if (x + 1 % 2 == 0)
				primes[x] = true;
		for (int x = 1; x * x < primes.length; x++)
			if (primes[x] == false)
				for (int y = (x + 1) * (x + 1) - 1; y < primes.length; y += (x + 1))
					primes[y] = true;
		for (int x = start; x <= end; x++) {
			if (!primes[x - 1])
				System.out.println(x);
		}
		scan.close();
	}

	int getPrimeFactors (int n) {
		int x = 2;
		int numOfFactors = 0;
		while (x * x <= n) {
			if (n % x == 0) {
				numOfFactors++;
				n /= x;
			} else
				x++;
		}
		if (x > 1)
			numOfFactors++;
		return numOfFactors;
	}

}

/*
 * private static boolean isPrime(int n) { if (n <= 1) return false; if (n == 2)
 * return true; if (n % 2 == 0) return false; if (n < 9) return true; if (n % 3
 * == 0) return false;
 * 
 * long counter = 5; while ((counter * counter) <= n) { if (n % counter == 0)
 * return false; if (n % (counter + 2) == 0) return false; counter += 6; }
 * 
 * return true; }
 */