


import java.util.*;
import java.io.*;

public class SuffixArray {
	static final SuffixComparator C = new SuffixComparator();
	
	// attributes of input
	static char[] input = "MISSISSIPPI".toCharArray();
	static int len = input.length;
	
	// initializing the arrays
	static Integer[] res = new Integer[len];
	static Integer[] order = new Integer[len];
	static Integer[] newOrder = new Integer[len];
	
	static int sz = 0;
	public static void main (String[] args) throws IOException {
		// initializing suffix array, order and new order
		for (int i = 0; i < len; i++) {
			res[i] = i;
			order[i] = (int)(input[i]);
			newOrder[i] = 0;
		}
		// we sort the suffix array with steps of the powers of 2
		// we can notice that a suffix with length 2^(n+1) can be split into two strings each with length 2^n
		// since we already have the order of the first strings, the order changes only when two first strings are equivalent
		for (sz = 1; sz < len; sz <<= 1) {
			Arrays.sort(res, C);
			// checking if two first strings are equivalent
			for (int i = 0; i < len - 1; i++)
				newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i+1]) < 0 ? 1 : 0);
			for (int i = 0; i < len; i++)
				order[res[i]] = newOrder[i];
		}
		for (int i = 0; i < len; i++)
			System.out.println(new String(input).substring(res[i]));
	}
	// Comparator for suffixes
	static class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer o1, Integer o2) {
			if (order[o1] != order[o2])
				return order[o1] - order[o2];
			if ((o1 += sz) < len & (o2 += sz) < len)
				return order[o1] - order[o2];
			return o2 - o1;
		}
	}
}

