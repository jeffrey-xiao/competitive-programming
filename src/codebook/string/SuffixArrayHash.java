/*
 * Suffix array construction using hashing.
 *
 * Time complexity: O(N (log N) ^ 2)
 */

package codebook.string;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class SuffixArrayHash {

  // constants
  static final long BASE = 137;
  static final long MOD = 1000000007;

  long[] pow;
  long[] hash;
  Integer[] sa;
  int len;
  char[] text;

  SuffixArrayHash (String text) {
    this.text = (" " + text).toCharArray();
    initialize();
  }

  public String getText () {
    return new String(text).substring(1);
  }

  public void setText (String text) {
    this.text = (" " + text).toCharArray();
    initialize();
  }

  public Integer[] getSuffixArray () {
    return sa;
  }

  private void initialize () {
    this.len = text.length;
    this.pow = new long[len];
    this.hash = new long[len];
    this.sa = new Integer[len];
    pow[0] = 1;
    for (int i = 1; i < len; i++) {
      pow[i] = (pow[i - 1] * BASE) % MOD;
      hash[i] = (hash[i - 1] * BASE + text[i]) % MOD;
      sa[i - 1] = i;
    }
    Arrays.sort(sa, new SuffixComparator());
    for (int i = 0; i < len - 1; i++)
      sa[i]--;
  }

  class SuffixComparator implements Comparator<Integer> {
    @Override
    public int compare (Integer i, Integer j) {
      // if the first character isn't the same, then we pick the one with the "lower" first character
      if (text[i] != text[j])
        return text[i] - text[j];
      int lo = 0;
      int hi = len - Math.max(i, j) - 1;
      // binary search for the place where the suffixes mismatch
      while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (getHash(i, i + mid) == getHash(j, j + mid))
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      // one suffix is a substring of the other, so we return the shorter one
      if (lo + Math.max(i, j) == len)
        return j - i;
      // return the one with the "lower" character when the suffixes mismatch
      return text[lo + i] - text[lo + j];
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
