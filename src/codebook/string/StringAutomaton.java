/*
 * Builds a DFA that finds the first occurrence of a pattern string in a text string.
 * 
 * Time complexity: O(R * len)
 * 
 */

package codebook.string;

import java.util.*;

public class StringAutomaton {
	private final int R = 256;
	private int[][] dfa;

	private char[] pat;
	private int len;

	public StringAutomaton (String pattern) {
		this(pattern.toCharArray());
	}

	public StringAutomaton (char[] pattern) {
		this.pat = Arrays.copyOf(pattern, pattern.length);
		this.len = this.pat.length;

		dfa = new int[R][len];
		for (int i = 0, j = 1; j < len; j++) {
			for (int k = 0; k < R; k++)
				dfa[k][j] = dfa[k][i]; // Copy mismatch cases
			dfa[pat[j]][j] = j + 1; // Initialize math case
			i = dfa[pat[j]][i]; // Update restart state
		}
	}

	public int search (String text) {
		return search(text.toCharArray());
	}

	public int search (char[] text) {
		int i, j;
		for (i = 0, j = 0; i < text.length && j < len; i++) {
			j = dfa[text[i]][j];
		}
		if (j == len)
			return i - len;
		return -1;
	}
}
