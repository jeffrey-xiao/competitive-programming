package codebook.string;

public class KMP {
	private String pattern;
	private String text;
	private int[] LCP;
	KMP (String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
		buildLCP();
	}
	public String getPattern () {
		return pattern;
	}
	public void setPattern (String pattern) {
		this.pattern = pattern;
		buildLCP();
	}
	public String getText () {
		return text;
	}
	public void setText (String text) {
		this.text = text;
	}
	public int search () {
		int j = 0;
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j))
				j = LCP[j-1];
			if (text.charAt(i) == pattern.charAt(j))
				j++;
			if (j == pattern.length())
				return i - j + 1;
		}
		return -1;
	}
	private void buildLCP () {
		LCP = new int[pattern.length()];
		for (int i = 1; i < pattern.length(); i++) {
			int j = LCP[i-1];
			
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = LCP[j-1];
			
			if (pattern.charAt(i) == pattern.charAt(j))
				j++;
			
			LCP[i] = j;
		}
	}
}

