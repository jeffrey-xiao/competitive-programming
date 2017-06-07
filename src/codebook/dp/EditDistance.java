/*
 * Dynamic programming solution that computes the edit distance of two strings. The edit distance is the number of operations to transform the first string to the second string.
 * The possible operations are the insertion of a character, the modification of a character, and the deletion of a character.
 *
 * Time complexity: O(NM) where N is the length of the first string and M is the length of the second string.
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EditDistance {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    String s1 = " " + readLine();
    String s2 = " " + readLine();
    int[][] dp = new int[s1.length()][s2.length()];
    for (int i = 1; i < s1.length(); i++)
      for (int j = 1; j < s2.length(); j++)
        if (s1.charAt(i) == s2.charAt(j))
          dp[i][j] = dp[i - 1][j - 1] + 1;
        else
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
    out.println(dp[s1.length()][s2.length()]);
    out.close();
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
