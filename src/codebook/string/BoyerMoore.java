/*
 * Boyer-Moore string search algorithm is an efficient string searching algorithm.
 * The algorithm preprocesses the string being searched for, but not the string being searched in.
 * It uses the information gathered in the preprocessing step to skip sections of the text.
 */

package codebook.string;

public class BoyerMoore {
	private static final int R = 256;
	private String pattern;
	private int[] rightOcc;

	BoyerMoore (String pattern) {
		this.pattern = pattern;
		initialize();
	}

	public String getPattern () {
		return pattern;
	}

	public void setPattern (String pattern) {
		this.pattern = pattern;
		initialize();
	}

	private void initialize () {
		rightOcc = new int[R];
		for (int i = 0; i < R; i++)
			rightOcc[i] = -1;
		for (int i = 0; i < pattern.length(); i++)
			rightOcc[pattern.charAt(i)] = i;
	}

	public int search (String text) {
		int skip = 0;
		for (int i = 0; i <= text.length() - pattern.length(); i += skip) {
			skip = 0;
			for (int j = pattern.length() - 1; j >= 0; j--) {
				// if there is a mismatch, then we shift left to the rightmost character we are looking for
				// if the rightmost character has already passed, then we shift by one
				if (pattern.charAt(j) != text.charAt(i + j)) {
					skip = Math.max(1, j - rightOcc[text.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0)
				return i;
		}
		return -1;
	}

}
