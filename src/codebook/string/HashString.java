// 1-indexed hash string

package codebook.string;

public class HashString {
	
	static final int BASE = 137;
	static final int MOD = 1000000007;
	
	private String text;
	private long[] hash;
	private long[] pow;
	
	HashString (String text) {
		this.text = " " + text;
		initialize();
	}
	
	public void setText (String text) {
		this.text = text;
		initialize();
	}
	
	public String getText () {
		return text;
	}
	
	private void initialize () {
		hash = new long[text.length()];
		pow[0] = 1;
		hash[0] = 0;
		for (int i = 1; i < text.length(); i++) {
			pow[i] = (pow[i-1] * BASE) % MOD;
			hash[i] = (hash[i-1] * BASE + text.charAt(i)) % MOD;
		}
	}
	
	public long getHash () {
		return getHash(1, text.length()-1);
	}
	// hash[0, l] = S[0] * BASE ^ l + S[1] * BASE ^ (l-1) + ... + S[l-1] * BASE ^ 1 + S[l] * BASE ^ 0
	// hash[0, r] = S[0] * BASE ^ r + S[1] * BASE ^ (r-1) + ... + S[r-1] * BASE ^ 1 + S[r] * BASE ^ 0
	// Since we want the hash of [l, r], we must get rid of the hash of [0, l-1]
	// The hash of [0, l-1] in hash[0, r] is BASE^(r - (l - 1)) times greater than the hash of [0, l-1] in hash[0, r]
	public long getHash (int l, int r) {
		return (hash[r] - hash[l - 1] * pow[r - (l - 1)] % MOD + MOD) % MOD;
	}
}

