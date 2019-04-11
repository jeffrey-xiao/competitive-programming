package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2016_Round_2_Boomerang_Decoration {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int n = readInt();
      char[] c1 = (" " + next() + " ").toCharArray();
      char[] c2 = (" " + next() + " ").toCharArray();
      int[] prefixNextLetter = new int[n + 2];
      int[][] prefix = new int[n + 2][2];
      int[] suffixNextLetter = new int[n + 2];
      int[][] suffix = new int[n + 2][2];

      for (int i = 1; i <= n; i++) {
        if (i == 1)
          prefixNextLetter[i] = 0;
        else {
          if (c2[i] == c2[i - 1])
            prefixNextLetter[i] = prefixNextLetter[i - 1];
          else
            prefixNextLetter[i] = i - 1;
        }
      }
      for (int i = 1; i <= n; i++) {
        if (c1[i - 1] == c2[i - 1]) {
          prefix[i][0] = prefix[i - 1][0];
        } else {
          prefix[i][0] = prefix[i - 1][1];
        }
        prefix[i][1] = 1 + prefix[prefixNextLetter[i]][1];
      }

      for (int i = n; i >= 1; i--) {
        if (i == n)
          suffixNextLetter[i] = n + 1;
        else {
          if (c2[i] == c2[i + 1])
            suffixNextLetter[i] = suffixNextLetter[i + 1];
          else
            suffixNextLetter[i] = i + 1;
        }
      }
      for (int i = n; i >= 1; i--) {
        if (c1[i + 1] == c2[i + 1]) {
          suffix[i][0] = suffix[i + 1][0];
        } else {
          suffix[i][0] = suffix[i + 1][1];
        }
        suffix[i][1] = 1 + suffix[suffixNextLetter[i]][1];
      }
      int ans = 1 << 30;
      for (int i = 1; i < n; i++) {
        int val1 = c1[i] == c2[i] ? prefix[i][0] : prefix[i][1];
        int val2 = c1[i + 1] == c2[i + 1] ? suffix[i + 1][0] : suffix[i + 1][1];
        ans = Math.min(ans, Math.max(val1, val2));
      }
      if (n == 1) {
        if (c1[1] == c2[1])
          ans = 0;
        else
          ans = 1;
      }
      out.printf("Case #%d: %d\n", t, ans);
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
