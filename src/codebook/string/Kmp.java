/*
 * The Knuth-Morris-Pratt string searching algorithm searches for occurrences of a word within a main text by employing the observation that when a
 * mismatch occurs, the word itself, embodies sufficient information to determine where the next match could begin, thus bypassing re-examination of
 * previously matched characters.
 *
 * Time complexity:
 *  - Preprocessing: O(N)
 *  - Searching: O(N)
 */

package codebook.string;

public class Kmp {

  private String pattern;
  private int[] LCP;

  Kmp(String pattern) {
    this.pattern = pattern;
    buildLCP();
  }

  public static void main(String[] args) {
    Kmp kmp = new Kmp("ISS");
    assert kmp.search("MISSISSIPPI") == 1;
    assert kmp.search("IS") == -1;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
    buildLCP();
  }

  public int search(String text) {
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

  private void buildLCP() {
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
