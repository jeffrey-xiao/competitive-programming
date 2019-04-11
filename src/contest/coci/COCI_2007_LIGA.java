package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_LIGA {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    for (int k = 0; k < n; k++) {
      int a = input();
      int b = input();
      int c = input();
      int d = input();
      int e = input();
      int a1 = 0, a2 = 100;
      int b1 = 0, b2 = 100;
      int c1 = 0, c2 = 100;
      if (a != -1)
        a1 = (a2 = a);
      else if (b != -1 && c != -1 && d != -1)
        a1 = (a2 = b + c + d);
      if (b != -1)
        b1 = (b2 = b);
      else if (a != -1 && c != -1 && d != -1)
        b1 = (b2 = a - c - d);
      if (c != -1)
        c1 = (c2 = c);
      else if (b != -1 && a != -1 && d != -1)
        c1 = (c2 = a - b - d);
      main:
      for (int x = a1; x <= a2; x++) {
        for (int y = b1; y <= b2; y++) {
          for (int z = c1; z <= c2; z++) {
            int i = (x - y - z);
            if (d != -1)
              i = d;
            if (x == y + z + i && (e == -1 || y * 3 + z == e) && (x - y - z >= 0) && i <= 100) {
              System.out.printf("%d %d %d %d %d\n", x, y, z, i, y * 3 + z);
              break main;
            }
          }
        }
      }
    }

  }

  private static int input() throws IOException {
    String next = next().trim();
    if (next.equals("?"))
      return -1;
    return Integer.parseInt(next);
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
