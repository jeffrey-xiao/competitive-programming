package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sherlock_And_Anagrams {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    for (int i = 0; i < n; i++) {
      String s = next();
      int ans = 0;
      for (int j = 0; j < s.length(); j++) {
        for (int k = j + 1; k <= s.length(); k++) {
          for (int l = j + 1; l < s.length(); l++) {
            if (l + (k - j) <= s.length()) {
              if (isAnagram(s.substring(j, k), s.substring(l, l + (k - j))))
                ans++;
            }
          }
        }
      }
      System.out.println(ans);
    }

    pr.close();
  }

  private static boolean isAnagram(String s1, String s2) {
    char[] c1 = s1.toCharArray();
    char[] c2 = s2.toCharArray();
    Arrays.sort(c1);
    Arrays.sort(c2);
    return new String(c1).equals(new String(c2));
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
