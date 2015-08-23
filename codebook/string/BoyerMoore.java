package codebook.string;

public class BoyerMoore {
	private static final int R = 256;
	private String pattern, text;
	private int[] rightOcc;
	
	BoyerMoore (String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
		rightOcc = new int[R];
		for (int i = 0; i < R; i++)
			rightOcc[i] = -1;
		for (int i = 0; i < pattern.length(); i++)
			rightOcc[pattern.charAt(i)] = i;
	}

	public int search () {
		int skip = 0;
		for (int i = 0; i <= text.length() - pattern.length(); i += skip) {
			skip = 0;
			for (int j = pattern.length()-1; j >= 0; j--) {
				if (pattern.charAt(j) != text.charAt(i+j)) {
					skip = Math.max(1, j - rightOcc[text.charAt(i+j)]);
					break;
				}
			}
			if (skip == 0)
				return i;
		}
		return -1;
	}
	
	public String getPattern () {
		return pattern;
	}

	public void setPattern (String pattern) {
		this.pattern = pattern;
		for (int i = 0; i < R; i++)
			rightOcc[i] = -1;
		for (int i = 0; i < pattern.length(); i++)
			rightOcc[pattern.charAt(i)] = i;
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
	}
}

