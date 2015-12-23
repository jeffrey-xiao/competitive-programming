/* 
 * For a function f which maps a finite set S to itself and any
 * initial value x0 in S, the sequence of iterated values:
 * x_0, x_1 = f(x_0), x_2 = f(x_1), ... x_i = f(x_(i-1))
 * must eventually use the same value twice: there must be some
 * i <> j such that xi = xj. Once this happens, the sequence
 * must continue periodically, by repeating the same sequence
 * of values from x_i to x_(j-1). Cycle detection asks to find
 * i and j, given the function f(x) and x_0.
 * 
 * Floyd's cycle finding algorithm uses two points that move through the sequence at different speeds.
 * 
 * Time complexity: O(length of cycle * first index of cycle)
 * 
 */

package codebook.algorithms;

public class CycleDetectionFloyd {

	static class State {
		int start, length;
		State (int start, int length) {
			this.start = start;
			this.length = length;
		}
		@Override
		public String toString () {
			return "Start: "+start+"; Length: " + length;
		}
	}
	
	static int f (int x) {
		return (125978*x*x + 2630) % 6349;
	}
	
	static State getCycle (int x) {
		int tortoise = f(x), hare = f(f(x));
		while (tortoise != hare) {
			tortoise = f(tortoise);
			hare = f(f(hare));
		}
		int start = 0;
		tortoise = x;
		while (tortoise != hare) {
			tortoise = f(tortoise);
			hare = f(hare);
			start++;
		}
		int length = 1;
		hare = f(tortoise);
		while (tortoise != hare) {
			hare = f(hare);
			length++;
		}
		return new State(start, length);
	}
	
	public static void main (String[] args) {
		System.out.println(getCycle(0));
	}
}

