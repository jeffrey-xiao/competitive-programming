/*
 * LCP Algorithm is a string algorithm that contructs the LCP array of a string using its suffix array.
 *
 * Time complexity: O(N)
 */
package codebook.string;

public class LcpArray {
  public static Integer[] getLcp (String s) {
    char[] text = s.toCharArray();
    int len = s.length();

    Integer[] sa = new SuffixArraySort(new String(text)).getSuffixArray();
    Integer[] lcp = new Integer[len];

    int[] rank = new int[len];
    for (int i = 0; i < len; i++)
      rank[sa[i]] = i;

    int k = 0;
    // at each iteration, if k > 0, then we decrement it by one, because the LCP of successive suffixes can decrease by at most 1
    // Proof: Let us say we have a suffix s1 and the next highest ranked suffix is s2
    // If the LCP of s1 and s2 is k and if we delete the first letter of s1 and s2,
    // the LCP of the new strings will be at least LCP - 1
    for (int i = 0; i < len; i++, k = k > 0 ? k - 1 : 0) {
      // exception is when we are at the suffix with the last rank -- it will always be 0
      if (rank[i] == len - 1) {
        k = 0;
        continue;
      }
      // attempting to extend from the next highest ranked suffix
      int j = sa[rank[i] + 1];
      while (j + k < len && i + k < len && text[j + k] == text[i + k])
        k++;
      lcp[rank[i]] = k;
    }
    return lcp;
  }
}
