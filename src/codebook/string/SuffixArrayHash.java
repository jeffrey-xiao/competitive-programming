package codebook.string;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SuffixArrayHash {

	// constants
	static final long BASE = 137;
	static final long MOD = 10000007;

	long[] pow;
	long[] hash;
	Integer[] sa;
	int len;
	String text;

	SuffixArrayHash (String text) {
		this.text = " " + text;
		initialize();
	}

	public String getText () {
		return text;
	}

	public void setText (String text) {
		this.text = text;
		initialize();
	}

	public Integer[] getSuffixArray () {
		return sa;
	}

	private void initialize () {
		this.len = text.length();
		this.pow = new long[len];
		this.hash = new long[len];
		this.sa = new Integer[len];
		pow[0] = 1;
		for (int i = 1; i < len; i++) {
			pow[i] = (pow[i - 1] * BASE) % MOD;
			hash[i] = (hash[i - 1] * BASE + text.charAt(i)) % MOD;
			sa[i - 1] = i;
		}
		Arrays.sort(sa, new SuffixComparator());
	}

	class SuffixComparator implements Comparator<Integer> {
		@Override
		public int compare (Integer i, Integer j) {
			if (text.charAt(i) != text.charAt(j))
				return text.charAt(i) - text.charAt(j);
			int lo = 0;
			int hi = len - Math.max(i, j) - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (getHash(i, i + mid) == getHash(j, j + mid))
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			if (lo + Math.max(i, j) == len) {
				return j - i;
			}
			return text.charAt(lo + i) - text.charAt(lo + j);
		}

		private long getHash (int i, int j) {
			return ((hash[j] - hash[i - 1] * pow[j - (i - 1)] % MOD + MOD) % MOD);
		}
	}

	public static void main (String[] args) throws IOException {
		SuffixArraySort s = new SuffixArraySort("mississippi");
		Integer[] res = s.getSuffixArray();
		for (int i = 1; i < s.getString().length(); i++)
			System.out.println(s.getString().substring(res[i]));
	}

}
