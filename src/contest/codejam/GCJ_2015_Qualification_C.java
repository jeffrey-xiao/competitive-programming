package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Qualification_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int[][] Q = {{0, 0, 0, 0, 0},
                      {0, 1, 2, 3, 4},
                      {0, 2, -1, 4, -3},
                      {0, 3, -4, -1, 2},
                      {0, 4, 3, -2, -1}};
  static char[] in;
  static int[] prefix;
  static int[] suffix;
  static int[] total;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      System.out.println(t);
      int L = readInt();
      long X = readLong();
      in = (" " + next()).toCharArray();
      prefix = new int[L + 2];
      suffix = new int[L + 2];
      total = new int[4];
      prefix[0] = prefix[L + 1] = suffix[0] = suffix[L + 1] = 1;
      for (int i = 1; i <= L; i++) {
        prefix[i] = multiply(prefix[i - 1], get(in[i]));
      }
      for (int i = L; i >= 1; i--) {
        suffix[i] = multiply(get(in[i]), suffix[i + 1]);
      }
      total[0] = 1;
      for (int i = 1; i < 4; i++) {
        total[i] = multiply(total[i - 1], prefix[L]);
      }

      boolean possible = false;
      // Ends are in one part.
    prefixLoop:
      for (int a = 0; a < 4; a++) {
        boolean foundPrefix = false;
        for (int i = 0; i <= L; i++) {
          int p = prefix[i];
          if (multiply(total[a], p) != 2) {
            continue;
          }
          foundPrefix = true;
        suffixLoop:
          for (int b = 0; b < 4; b++) {
            boolean foundSuffix = false;
            for (int j = i + 1; j <= L + 1; j++) {
              int s = suffix[j];
              if (multiply(s, total[b]) != 4) {
                continue;
              }
              foundSuffix = true;
              int m = seg(i + 1, j - 1);
              if (m != 3) {
                continue;
              }
              if ((a + b + 1) % 4 == X % 4 && a + b + 1 <= X) {
                possible = true;
              }
            }
            if (foundSuffix) {
              break suffixLoop;
            }
          }
        }
        if (foundPrefix) {
          break prefixLoop;
        }
      }

      // Ends are in two parts.
    prefixLoop:
      for (int b = 0; b < 4; b++) {
        boolean foundPrefix = false;
        for (int i = 0; i <= L; i++) {
          int p = prefix[i];
          if (multiply(total[b], p) != 2) {
            continue;
          }
          foundPrefix = true;
        suffixLoop:
          for (int c = 0; c < 4; c++) {
            boolean foundSuffix = false;
            for (int j = 1; j <= L + 1; j++) {
              int s = suffix[j];
              if (multiply(s, total[c]) != 4) {
                continue;
              }
              foundSuffix = true;
              for (int a = 0; a < 4; a++) {
                int m = multiply(suffix[i + 1], multiply(total[a], prefix[j - 1]));
                if (m != 3) {
                  continue;
                }
                if ((a + b + c + 2) % 4 == X % 4 && a + b + c + 2 <= X) {
                  possible = true;
                }
              }
            }
            if (foundSuffix) {
              break suffixLoop;
            }
          }
        }
        if (foundPrefix) {
          break prefixLoop;
        }
      }

      if (possible) {
        out.printf("Case #%d: YES%n", t);
      } else {
        out.printf("Case #%d: NO%n", t);
      }
    }
    out.close();
  }

  static int get(char i) {
    if (i == '1') {
      return 1;
    }
    if (i == 'i') {
      return 2;
    }
    if (i == 'j') {
      return 3;
    }
    assert i == 'k';
    return 4;
  }

  static int seg(int i, int j) {
    if (i < j) {
      return 1;
    } else {
      return multiply(multiply(prefix[i - 1], multiply(prefix[i - 1], prefix[i - 1])), prefix[j]);
    }
  }

  static int multiply(int i, int j) {
    int ret = Q[Math.abs(i)][Math.abs(j)];
    if (i * j * ret < 0) {
      return -Math.abs(ret);
    } else {
      return Math.abs(ret);
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
