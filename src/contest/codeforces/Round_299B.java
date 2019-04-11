package contest.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_299B {

  static final int MOD = 1000000007;
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    char[] c = next().toCharArray();
    int[] LCP = new int[c.length + 1];
    LCP[0] = LCP[1] = 0;
    for (int i = 2; i <= c.length; i++) {
      int y = LCP[i - 1];
      while (y > 0 && c[i - 1] != c[y]) {
        y = LCP[y];
      }
      if (c[y] == c[i - 1])
        y++;
      LCP[i] = y;
    }
    boolean[] valid = new boolean[c.length + 1];
    valid[0] = true;
    for (int i = c.length; i > 0; i = LCP[i]) {
      valid[i] = true;
    }
    int last = 0;
    long ans = 1;
    for (int i = 0; i < m; i++) {
      int j = readInt();
      if (j > last) {
        for (int k = 0; k < j - last - 1; k++) {
          ans = (ans * 26) % MOD;
        }
      } else if (!valid[last - j + 1]) {
        ans = 0;
      }
      last = j + c.length - 1;
    }
    while (last < n) {
      ans = (ans * 26) % MOD;
      last++;
    }
    System.out.println(ans);
    pr.close();
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
