package codebook.string;

public class KMP {

	private String pattern;
	private int[] LCP;

	KMP (String pattern) {
		this.pattern = pattern;
		buildLCP();
	}

	public String getPattern () {
		return pattern;
	}

	public void setPattern (String pattern) {
		this.pattern = pattern;
		buildLCP();
	}

	public int search (String text) {
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			// loop until we find a prefix whose next character matches the current character are on
			while (j > 0 && text.charAt(i) != pattern.charAt(j))
				j = LCP[j - 1];
			if (text.charAt(i) == pattern.charAt(j))
				j++;
			// the entire pattern has been matched
			if (j == pattern.length())
				return i - j + 1;
		}
		return -1;
	}

	private void buildLCP () {
		LCP = new int[pattern.length()];
		for (int i = 1; i < pattern.length(); i++) {
			// attempt to build on the previous LCP
			int j = LCP[i - 1];
			// loop until we find a prefix whose next character matches the current character we are on
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = LCP[j - 1];

			if (pattern.charAt(i) == pattern.charAt(j))
				j++;

			LCP[i] = j;
		}
	}
}
