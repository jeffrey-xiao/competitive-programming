package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Qualification_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, B;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    B = readInt();
    for (int t = 1; t <= T; t++) {
      Integer sameIndex = null, diffIndex = null;
      boolean[] A = new boolean[B];

      // Initialization.
      out.println(1);
      out.flush();
      A[0] = next().equals("1");
      out.println(B);
      out.flush();
      A[B - 1] = next().equals("1");

      int i = 1;
      while (i != B / 2) {
        for (int j = i; j < Math.min(i + 4, B / 2); j++) {
          out.println(j + 1);
          out.flush();
          A[j] = next().equals("1");

          out.println(B - j);
          out.flush();
          A[B - j - 1] = next().equals("1");
        }
        for (int j = 0; j < i; j++) {
          if (A[j] == A[B - 1 - j] && sameIndex == null) {
            sameIndex = j;
          } else if (A[j] != A[B - 1 - j] && diffIndex == null) {
            diffIndex = j;
          }
        }

        if (i != 1) {
          boolean isReverse = false;
          boolean isFlip = false;
          if (sameIndex != null && diffIndex != null) {
            out.println(sameIndex + 1);
            out.flush();
            boolean same = next().equals("1");

            out.println(diffIndex + 1);
            out.flush();
            boolean diff = next().equals("1");

            isFlip = A[sameIndex] != same;
            isReverse = (A[diffIndex] != diff && A[sameIndex] == same) ||
                        (A[diffIndex] == diff && A[sameIndex] != same);
          } else if (sameIndex != null) {
            out.println(sameIndex + 1);
            out.flush();
            boolean same = next().equals("1");
            out.println(sameIndex + 1);
            out.flush();
            same = next().equals("1");
            isFlip = A[sameIndex] != same;
          } else if (diffIndex != null) {
            out.println(diffIndex + 1);
            out.flush();
            boolean diff = next().equals("1");
            out.println(diffIndex + 1);
            out.flush();
            diff = next().equals("1");
            isFlip = A[diffIndex] != diff;
          } else {
            assert false;
          }

          if (isReverse) {
            for (int j = 0; j < i; j++) {
              boolean temp = A[j];
              A[j] = A[B - j - 1];
              A[B - j - 1] = temp;
            }
          }
          if (isFlip) {
            for (int j = 0; j < i; j++) {
              A[j] ^= true;
              A[B - j - 1] ^= true;
            }
          }
        }
        i = Math.min(i + 4, B / 2);
      }
      for (int j = 0; j < B; j++) {
        out.print(A[j] ? "1" : "0");
      }
      out.println();
      out.flush();
      next();
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
