/*
 * O(N) time complexity to generate the LCP array of a string. Dependent on the suffix array.
 */
package codebook.string;

public class LCPArray {
	private String text;
	private int len;

	private int[] LCP;

	LCPArray (String text) {
		this.text = text;
		this.len = text.length();
		this.LCP = new int[len];
		computeLCP();
	}

	public String getString () {
		return text;
	}

	public void setString (String s) {
		this.text = s;
		this.len = text.length();
		this.LCP = new int[len];
		computeLCP();
	}

	private void computeLCP () {
		Integer[] sa = new SuffixArraySort(text).getSuffixArray();
		int[] rank = new int[len];
		for (int i = 0; i < len; i++)
			rank[sa[i]] = i;
		int k = 0;
		// at each iteration, if k > 0, then we decrement it by one, because the LCP of successive suffixes can decrease by at most 1
		// Proof: Let us say we have a suffix s1 and the next highest ranked suffix is s2
		// If the LCP of s1 and s2 is k and if we delete the first letter of s1 and s2,
		// the LCP of the new strings will be at least LCP - 1
		for (int i = 0; i < len; i++, k = k > 0 ? k - 1 : 0) {
			// exception is when we are at the suffix with the last rank -- it will always be 0
			if (rank[i] == len - 1) {
				k = 0;
				continue;
			}
			// attempting to extend from the next highest ranked suffix
			int j = sa[rank[i] + 1];
			while (j + k < len && i + k < len && text.charAt(j + k) == text.charAt(i + k))
				k++;
			LCP[rank[i]] = k;
		}
	}

	public int[] getLCP () {
		return LCP;
	}
}
