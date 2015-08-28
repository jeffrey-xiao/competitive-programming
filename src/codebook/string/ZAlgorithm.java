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
		// [l, r] represents the largest prefix substring of the previous index
		int l = 0, r = 0;
		for (int i = 1; i < text.length(); i++) {
			// if the current index exceeds the right boundary of the previous prefix substring, then reset it
			if (i > r) {
				l = r = i;
				while (r < text.length() && text.charAt(r) == text.charAt(r - l))
					r++;
				r--;
				z[i] = r - l + 1;
			} else {
				// S[j] will be the same character as S[i]
				// if the size of the prefix substring at j is smaller than the boundary,
				// then z[i] = z[j]
				int j = i - l;
				if (z[j] < r - i + 1)
					z[i] = z[j];
				// if the size of the prefix substring at j is larger than the boundary, then we have to extend it
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
