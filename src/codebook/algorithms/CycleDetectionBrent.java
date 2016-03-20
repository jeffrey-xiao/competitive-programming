/* 
 * For a function f which maps a finite set S to itself and any initial value x0 in S, the sequence of iterated values:
 * x_0, x_1 = f(x_0), x_2 = f(x_1), ... x_i = f(x_(i-1)) must eventually use the same value twice: there must be some
 * i <> j such that xi = xj. Once this happens, the sequence must continue periodically, by repeating the same sequence
 * of values from x_i to x_(j-1). Cycle detection asks to find i and j, given the function f(x) and x_0.
 * 
 * Brent's cycle-detection algorithm searches for the smallest power of two that is larger than both the length of the cycle
 * and the first index of the cycle.
 * 
 * Time complexity: O(length of cycle * first index of cycle)
 * 
 */

package codebook.algorithms;

public class CycleDetectionBrent {

	static class State {
		int start, length;

		State (int start, int length) {
			this.start = start;
			this.length = length;
		}

		@Override
		public String toString () {
			return "Start: " + start + "; Length: " + length;
		}
	}

	static int f (int x) {
		return (125978 * x * x + 2630) % 6349;
	}

	static State getCycle (int x) {
		int power = 1;
		int length = 1;
		int tortoise = x;
		int hare = f(x);

		while (tortoise != hare) {
			if (power == length) {
				tortoise = hare;
				power *= 2;
				length = 0;
			}
			hare = f(hare);
			length++;
		}

		hare = x;
		for (int i = 0; i < length; i++)
			hare = f(hare);

		int start = 0;
		tortoise = x;

		while (tortoise != hare) {
			tortoise = f(tortoise);
			hare = f(hare);
			start++;
		}
		return new State(start, length);
	}

	public static void main (String[] args) {
		System.out.println(getCycle(0));
	}
}
