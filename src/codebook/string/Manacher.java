/*
 * Manacher algorithm is a string algorithm that finds the largest palindrome of a string.
 *
 * Time complexity: O(N)
 */

package codebook.string;

public class Manacher {
	public String getLongestPalindrome (String s) {
		int len = s.length() * 2 - 1;
		char[] text = new char[len];
		for (int i = 0; i < len; i++)
			text[i] = '#';
		for (int i = 0; i < len; i += 2)
			text[i] = s.charAt(i / 2);
		int[] max = new int[len];

		// center of right most palindrome
		int c = 0;
		// right boundary of right most palindrome
		int r = 0;
		for (int i = 1; i < len; i++) {
			// the index of the mirror of i with respects to center c
			int j = (c - (i - c));

			// initializing the length of the palindrome centered at i
			max[i] = r > i ? Math.min(r - i, max[j]) : 0;
			// extending the palindrome at i 
			while (i + 1 + max[i] < len && i - 1 - max[i] >= 0 && text[i + 1 + max[i]] == text[i - 1 - max[i]])
				max[i]++;

			if (i + max[i] > r) {
				r = i + max[i];
				c = i;
			}
		}
		int maxLength = 0;
		int index = 0;
		for (int i = 1; i < len - 1; i++) {
			if (max[i] > maxLength) {
				maxLength = max[i];
				index = i;
			}
		}
		return s.substring((index - maxLength + 1) / 2, (index - maxLength) / 2 + maxLength + 1);
	}

	public static void main (String[] args) {
		Manacher m = new Manacher();
		System.out.println(m.getLongestPalindrome("BANANA"));
	}
}
