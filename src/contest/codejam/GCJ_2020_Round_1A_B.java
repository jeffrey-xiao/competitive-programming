package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1A_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d:%n", t);
      int N = readInt();
      int temp = N;
      int rowSum = 1;
      int targetRow = 1;
      while (temp > 1) {
        temp /= 2;
        rowSum *= 2;
        targetRow++;
      }
      // Adjust target row if it is not reachable.
      if (rowSum + targetRow - 1 > N) {
        targetRow--;
        rowSum /= 2;
      }
      // Determine rows to take.
      HashSet<Integer> rows = new HashSet<Integer>();
      temp = N;
      for (int r = targetRow; r > 0; r--) {
        if (temp - (r - 1) >= rowSum) {
          temp -= rowSum;
          rows.add(r);
        } else {
          temp--;
        }
        rowSum /= 2;
      }
      // Traverse rows.
      int r = 1;
      int c = 1;
      rowSum = 1;
      for (; r <= targetRow; r++) {
        if (rows.contains(r)) {
          if (c == 1) {
            for (int i = 1; i <= r; i++) {
              out.printf("%d %d%n", r, i);
            }
            c = r + 1;
          } else {
            for (int i = r; i >= 1; i--) {
              out.printf("%d %d%n", r, i);
            }
            c = 1;
          }
          N -= rowSum;
        } else {
          out.printf("%d %d%n", r, c);
          N--;
          if (c == 1) {
            c = 1;
          } else {
            c++;
          }
        }
        rowSum *= 2;
      }
      // Fill remainder.
      while (N > 0) {
        out.printf("%d %d%n", r, c);
        N--;
        if (c == 1) {
          c = 1;
        } else {
          c++;
        }
        r++;
      }
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
