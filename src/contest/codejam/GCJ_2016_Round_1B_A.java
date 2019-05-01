package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1B_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static String S;

  static final String[] DIGITS = {"SIX", "ZERO", "TWO", "FOUR", "ONE", "EIGHT", "THREE",  "FIVE",
    "SEVEN", "NINE"};
  static final int[] IDS = {6, 0, 2, 4, 1, 8, 3, 5, 7, 9};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d: ", t);
      S = next();
      ArrayList<Character> ans = new ArrayList<Character>();
      int[] cnt = new int[26];
      for (int i = 0; i < S.length(); i++) {
        cnt[S.charAt(i) - 'A']++;
      }
      boolean hasMore = true;
      while (hasMore) {
        hasMore = false;
        for (int i = 0; i < DIGITS.length; i++) {
          boolean valid = true;
          for (int j = 0; j < DIGITS[i].length(); j++) {
            cnt[DIGITS[i].charAt(j) - 'A']--;
          }
          for (int j = 0; j < 26; j++) {
            if (cnt[j] < 0) {
              valid = false;
            }
          }
          if (valid) {
            hasMore = true;
            ans.add((char)(IDS[i] + '0'));
            break;
          } else {
            for (int j = 0; j < DIGITS[i].length(); j++) {
              cnt[DIGITS[i].charAt(j) - 'A']++;
            }
          }
        }
      }
      Collections.sort(ans);
      StringBuilder sorted = new StringBuilder();
      for (Character c : ans) {
        sorted.append(c);
      }
      out.println(sorted);
    }

    out.close();
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
