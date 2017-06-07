package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2016_Round_1_Coding_Contest_Creation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int n = readInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = readInt();
      int ans = 0;
      int problems = 1;
      for (int i = 0; i < n - 1; i++) {
        if (a[i + 1] <= a[i]) {
          ans += (4 - problems) % 4;
          problems = (problems + 4 - problems + 1) % 4;
        } else if (Math.abs(a[i + 1] - a[i]) <= 10) {
          problems = (problems + 1) % 4;
        } else {
          int add = Math.min(Math.abs(a[i + 1] - a[i] - 1) / 10, (4 - problems) % 4);
          ans += add;
          problems = (problems + add + 1) % 4;
        }
      }
      ans += (4 - problems) % 4;
      out.printf("Case #%d: %d\n", t, ans);
    }
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
