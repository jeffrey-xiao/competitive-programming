package codebook.string;

public class ZAlgorithm {
	private String text;
	private int[] z;
	ZAlgorithm (String text) {
		this.text = text;
		compute();
	}
	public String getText () {
		return text;
	}
	public void setText (String text) {
		this.text = text;
		compute();
	}
	public int[] getPrefixSubstringLengths () {
		return z;
	}
	private void compute () {
		z = new int[text.length()];
		int l = 0, r = 0;
		for (int i = 1; i < text.length(); i++) {
			if (i > r) {
				l = r = i;
				while (r < text.length() && text.charAt(r) == text.charAt(r - l))
					r++;
				r--;
				z[i] = r - l + 1;
			} else {
				int j = i - l;
				if (z[j] < r - i + 1)
					z[i] = z[j];
				else {
					l = i;
					while (r < text.length() && text.charAt(r) == text.charAt(r - l))
						r++;
					r--;
					z[i] = r - l + 1;
				}
			}
		}
	}
}

