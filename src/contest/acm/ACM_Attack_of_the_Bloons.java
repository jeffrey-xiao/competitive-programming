package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Attack_of_the_Bloons {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int l = readInt();
      int[] course = new int[l + 1];
      int n = readInt();
      for (int x = 0; x < n; x++) {
        int center = readInt();
        int radius = readInt();
        int damage = readInt();
        course[Math.max(0, center - radius - 1)] += damage;
        course[Math.min(l - 1, center + radius - 1) + 1] -= damage;
      }
      int curr = 0;
      for (int x = 0; x <= l; x++)
        course[x] = (curr += course[x]);

      int m = readInt();
    main:
      for (int x = 0; x < m; x++) {
        int next = readInt();
        for (int y = 0; y < l; y++) {
          next -= course[y];
          if (next <= 0) {
            System.out.println(y + 1);
            continue main;
          }
        }
        System.out.println("Bloon leakage");
      }
    }
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
}
