/*
 * The Rabin-Karp string searching algorithm searches for occurrences of a word within a main text using hashing.
 *
 * Time complexity:
 *  - Preprocessing: O(N)
 *  - Searching: O(N)
 */

package codebook.string;

public class RabinKarp {

	private static final long MOD = 1000000007;
	private static final long R = 256;

	private long pow;
	private String pattern;
	private long patternHash;

	RabinKarp (String pattern) {
		this.pattern = pattern;
		initialize();
	}

	public int search (String text) {
		if (pattern.length() > text.length())
			return -1;
		long currHash = getHash(text, pattern.length());
		if (currHash == patternHash)
			return 0;
		// use a rolling hash to compute the hashes of successive substrings in O(1) time
		for (int i = pattern.length(); i < text.length(); i++) {
			currHash = (currHash + MOD - pow * text.charAt(i - pattern.length()) % MOD) % MOD;
			currHash = (currHash * R + text.charAt(i)) % MOD;
			// assume that there is no overlap in hashes
			if (currHash == patternHash)
				return i - pattern.length() + 1;
		}
		return -1;
	}

	public String getPattern () {
		return pattern;
	}

	public void setPattern (String pattern) {
		this.pattern = pattern;
		initialize();
	}

	private void initialize () {
		patternHash = getHash(pattern, pattern.length());
		pow = (long) (Math.pow(R, pattern.length() - 1) % MOD);
	}

	private long getHash (String s, int len) {
		long res = 0;
		for (int i = 0; i < len; i++)
			res = (R * res + s.charAt(i)) % MOD;
		return res;
	}
}
