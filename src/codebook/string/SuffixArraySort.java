/*
 * O (N log N log N) construction of a suffix array. The time complexity can be shortened to O (N log N) if radix sort is used.
 * This implementation is very simple to code and it's only handful of lines. Can be used if the time limit isn't very tight.
 *
 * Time complexity: O(N (log N)^2)
 */
package codebook.string;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SuffixArraySort {
	final SuffixComparator C = new SuffixComparator();

	// attributes of input
	private char[] input;
	private int len;
	private Integer[] res;
	private Integer[] order;
	private Integer[] newOrder;
	private int sz;

	SuffixArraySort (String s) {
		input = s.toCharArray();
		initialize();
	}

	public void setString (String s) {
		input = s.toCharArray();
		initialize();
	}

	public String getString () {
		return new String(input);
	}

	private void initialize () {
		len = input.length;
		res = new Integer[len];
		order = new Integer[len];
		newOrder = new Integer[len];
		sz = 0;
		computeSuffixArray();
	}

	public Integer[] getSuffixArray () {
		return res;
	}

	private void computeSuffixArray () {
		// initializing suffix array, order and new order
		for (int i = 0; i < len; i++) {
			res[i] = i;
			order[i] = (int) (input[i]);
			newOrder[i] = 0;
		}
		// we sort the suffix array with steps of the powers of 2
		// we can notice that a suffix with length 2^(n+1) can be split into two
		// strings each with length 2^n
		// since we already have the order of the first strings, the order
		// changes only when two first strings are equivalent
		for (sz = 1;; sz <<= 1) {
			Arrays.sort(res, C);
			// checking if two first strings are equivalent
			for (int i = 0; i < len - 1; i++)
				newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i + 1]) < 0 ? 1 : 0);
			for (int i = 0; i < len; i++)
				order[res[i]] = newOrder[i];
			if (newOrder[len - 1] == len - 1)
				break;
		}
	}

	// Comparator for suffixes
	class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer o1, Integer o2) {
			if (order[o1] != order[o2])
				return order[o1] - order[o2];
			if ((o1 += sz) < len & (o2 += sz) < len)
				return order[o1] - order[o2];
			return o2 - o1;
		}
	}

	public static void main (String[] args) throws IOException {
		SuffixArraySort s = new SuffixArraySort("mississippi");
		Integer[] res = s.getSuffixArray();
		for (int i = 0; i < s.getString().length(); i++)
			System.out.println(s.getString().substring(res[i]));
	}

}
