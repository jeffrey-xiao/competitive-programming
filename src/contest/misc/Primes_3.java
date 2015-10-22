package contest.misc;

import java.math.BigInteger;
import java.util.Scanner;

public class Primes_3 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int x = scan.nextInt(); x > 0; x--) {
			long n = scan.nextLong();
			System.out.println(isPrime(n, 100) ? "PRIME" : "NOT");
		}
	}

	public static boolean isPrime (long n, int k) {
		while (k > 0) {
			long x = (long) (Math.random() * (n - 2) + 1);
			BigInteger b = new BigInteger(Long.toString(x)).modPow(new BigInteger(Long.toString(n - 1)), new BigInteger(Long.toString(n)));
			if (!b.toString().equals("1"))
				return false;
			k--;
		}
		return true;
	}
}
