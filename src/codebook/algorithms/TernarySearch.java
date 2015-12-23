/*
 * Ternary search finds the maximum or minimum of a unimodal function f(x)
 * 
 * Time complexity: O(log_3 N) where N is the size of the interval to be searched
 */

package codebook.algorithms;

public class TernarySearch {

	static final double EPS = 1e-8;
	
	static double f (double x) {
		return -3.160297 * x * x + 7.15015 * x - 1.12689;
	}
	
	static double ternarySearchMin (double lo, double hi) {
		while (hi - lo > EPS) {
			double lthird = lo + (hi - lo) / 3;
			double hthird = hi - (hi - lo) / 3;
			if (f(lthird) < f(hthird))
				hi = hthird;
			else
				lo = lthird;
		}
		return lo;
	}
	
	static double ternarySearchMax (double lo, double hi) {
		while (hi - lo > EPS) {
			double lthird = lo + (hi - lo) / 3;
			double hthird = hi - (hi - lo) / 3;
			if (f(lthird) > f(hthird))
				hi = hthird;
			else
				lo = lthird;
		}
		return lo;
	}
	public static void main (String[] args) {
		System.out.println(ternarySearchMax(-100, 100));
	}
}

