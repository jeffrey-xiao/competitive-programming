package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_1995_Word_Chains {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    IOI_1995_Word_Chains.TrieNode tn = new IOI_1995_Word_Chains().new TrieNode('\u0000', false);
    String s = next();
    while (!s.equals(".")) {
      tn.addWord(s);
      s = next();
    }
    tn.printWords("");
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  class TrieNode {
    char letter;
    TrieNode[] links;
    boolean fullWord;
    int totalWords;

    TrieNode(char letter, boolean fullWord) {
      this.letter = letter;
      links = new TrieNode[26];
      this.fullWord = fullWord;
    }

    int addWord(String s) {
      if (s.length() == 0) {
        totalWords++;
        return totalWords;
      }
      int max = 0;
      for (int x = 0; x < links.length; x++) {
        if (links[x] != null && links[x].letter == s.charAt(0)) {
          int numOfWords = links[x].addWord(s.substring(1, s.length()));
          if (numOfWords > max)
            max = numOfWords;
          break;
        } else if (links[x] == null) {

          int numOfWords;

          if (s.length() == 1) {
            links[x] = new TrieNode(s.charAt(0), true);
            numOfWords = links[x].addWord(s.substring(1, s.length()));
            if (numOfWords > max)
              max = numOfWords;
          } else {
            links[x] = new TrieNode(s.charAt(0), false);
            numOfWords = links[x].addWord(s.substring(1, s.length()));
            if (numOfWords > max)
              max = numOfWords;
          }
          break;
        }
      }
      if (fullWord)
        max++;
      totalWords = Math.max(totalWords, max);
      return totalWords;
    }

    void printWords(String s) {
      int max = -1;
      int index = -1;
      for (int x = 0; x < links.length; x++) {
        if (links[x] == null)
          break;
        if (links[x].totalWords > max) {
          max = links[x].totalWords;
          index = x;
        }
      }
      if (fullWord)
        System.out.println(s + (letter == '\u0000' ? "" : letter));
      if (index != -1)
        links[index].printWords(s + (letter == '\u0000' ? "" : letter));

    }

    void debug(String s) {
      for (int x = 0; x < links.length; x++) {
        if (links[x] == null)
          break;
        links[x].debug(s + letter);
      }
      if (fullWord)
        System.out.println(s + letter + " " + totalWords);
    }
  }
}
