package codebook.math;

public class Sieve {
	
	static boolean isPrime (int n) {
	    if (n < 2)
	        return false;
	    for (int i = 2; i * i <= n; i++)
	        if (n % i == 0)
	            return false;
	    return true;
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
		int[] ret = new int[n+1];
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j += i)
				ret[j]++;
		return ret;
	}
	
	static int[] sumOfDivisors (int n) {
		int[] ret = new int[n+1];
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j += i)
				ret[j] += i;
		return ret;
	}
	
	static int[] eulerTotient (int n) {
		int[] ret = new int[n+1];
		for (int i = 1; i <= n; i++)
			ret[i] = i;
		for (int i = 2; i <= n; i++)
			if (ret[i] == i)
				for (int j = i; j <= n; j += i)
					ret[j] -= ret[j] / i; 
		return ret;
	}
	
	static int[] biggestPrimeDivisor (int n) {
		int[] ret = new int[n+1];
		for (int i = 1; i <= n; i++)
			if (ret[i] == i)
				for (int j = i; j <= n; j += i)
					ret[i] = i;
		return ret;
	}
}

