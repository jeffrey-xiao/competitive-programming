package codebook.string;

public class RabinKarp {
	private static final long MOD = 1000000007;
	private static final long R = 256;
	private long pow;
	private String pattern, text;
	private long patternHash;
	
	RabinKarp (String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
		patternHash = getHash(pattern, pattern.length());
		pow = (long) (Math.pow(R, pattern.length()-1) % MOD);
	}

	public int search () {
		if (pattern.length() > text.length())
			return -1;
		long currHash = getHash(text, pattern.length());
		if (currHash == patternHash)
			return 0;
		for (int i = pattern.length(); i < text.length(); i++) {
			currHash = (currHash + MOD - pow * text.charAt(i - pattern.length())%MOD)%MOD;
			currHash = (currHash*R + text.charAt(i))%MOD;
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
		patternHash = getHash(pattern, pattern.length());
		pow = (long) (Math.pow(R, pattern.length()-1) % MOD);
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}
	
	private long getHash (String s, int len) {
		long res = 0;
		for (int i = 0; i < len; i++)
			res = (R * res + s.charAt(i)) % MOD;
		return res;
	}
}

