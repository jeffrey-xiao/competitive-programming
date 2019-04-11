package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NOI_2002_Island_Of_Caveman {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  // (S1 + Ax) mod m = (S2 + By) mod m
  // ((S1 + Ax) - (S2 + By)) mod m = 0
  // (S1 - S2 + Ax - By) mod m = 0;
  // Ax - By = m*n - (S1 - S2)

  static int n, max;
  static int[] start, move, life;
  static int x1 = 0, y1 = 0, d = 0;

  public static void main(String[] args) throws IOException {
    n = readInt();
    start = new int[n];
    move = new int[n];
    life = new int[n];

    for (int x = 0; x < n; x++) {
      start[x] = readInt();
      move[x] = readInt();
      life[x] = readInt();
      max = Math.max(max, start[x]);
    }

    main:
    for (int b = max; ; b++) {
      for (int x = 0; x < n; x++) {
        for (int y = x + 1; y < n; y++) {
          int a = move[x] - move[y];
          int c = start[y] - start[x];

          extended(a, -b);

          if (c % d != 0) {
            continue;
          }
          int lcm = Math.abs(lcm(a, b));
          x1 = x1 * (c / d);
          y1 = y1 * (c / d);
          int amount = a < 0 ? (-lcm / a) : (lcm / a);
          while (x1 < 0) {
            x1 += amount;
          }
          while (x1 - amount > 0)
            x1 -= amount;
          if (x1 <= life[x] && x1 <= life[y])
            continue main;
        }
      }
      System.out.println(b);
      return;
    }
  }

  public static int gcf(int a, int b) {
    return b == 0 ? a : gcf(b, a % b);
  }

  public static int lcm(int a, int b) {
    return a * b / gcf(a, b);
  }

  public static void extended(int a, int b) {
    if (b == 0) {
      x1 = 1;
      y1 = 0;
      d = a;
      return;
    }
    extended(b, a % b);
    int x = y1;
    int y = x1 - (a / b) * y1;
    x1 = x;
    y1 = y;

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
