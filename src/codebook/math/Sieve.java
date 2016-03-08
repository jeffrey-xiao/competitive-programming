package codebook.math;

import java.util.ArrayList;
import java.util.Collections;

public class Sieve {

	static boolean isPrime (int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		if (n < 9)
			return true;
		if (n % 3 == 0)
			return false;

		int counter = 5;
		while ((counter * counter) <= n) {
			if (n % counter == 0)
				return false;
			if (n % (counter + 2) == 0)
				return false;
			counter += 6;
		}
		return true;
	}

	static void primeFactors (int n) {
		while (n % 2 == 0) {
			System.out.println("2 ");
			n = n / 2;
		}
		for (int i = 3; i * n <= n; i += 2) {
			while (n % i == 0) {
				System.out.println(i + " ");
				n = n / i;
			}
		}
		if (n > 2)
			System.out.println(n + " ");
	}

	static int countDivisors (int n) {
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

	static long fermat (long n) {
		if (n % 2 == 0)
			return 2;
		long x = (long) (Math.sqrt(n)), y = 0;
		long r = x * x - y * y - n;
		while (r != 0) {
			if (r < 0) {
				r += x + x + 1;
				x++;
			} else {
				r -= y + y + 1;
				y++;
			}
		}
		return x != y ? x - y : x + y;
	}

	static ArrayList<Integer> getDivisors (int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int d = 1; d * d <= n; d++) {
			if (n % d == 0) {
				ret.add(d);
				if (d * d != n)
					ret.add(n / d);
			}
		}
		Collections.sort(ret);
		return ret;
	}

	static int eulerPhiDirect (int n) {
		int result = n;
		for (int i = 2; i <= n; i++) {
			if (isPrime(i))
				result -= result / i;
		}
		return result;
	}

	static int[] numOfDivisors (int n) {
		int[] ret = new int[n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j += i)
				ret[j]++;
		return ret;
	}

	static int[] sumOfDivisors (int n) {
		int[] ret = new int[n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j += i)
				ret[j] += i;
		return ret;
	}

	static int[] eulerTotient (int n) {
		int[] ret = new int[n + 1];
		for (int i = 1; i <= n; i++)
			ret[i] = i;
		for (int i = 2; i <= n; i++)
			if (ret[i] == i)
				for (int j = i; j <= n; j += i)
					ret[j] -= ret[j] / i;
		return ret;
	}

	static int[] biggestPrimeDivisor (int n) {
		int[] ret = new int[n + 1];
		for (int i = 1; i <= n; i++)
			if (ret[i] == i)
				for (int j = i; j <= n; j += i)
					ret[i] = i;
		return ret;
	}
}
