package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1C_A {

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
      int X = readInt();
      int Y = readInt();
      char[] moves = next().toCharArray();
      boolean valid = false;
      for (int i = 0; i < moves.length; i++) {
        if (moves[i] == 'N') {
          Y++;
        } else if (moves[i] == 'S') {
          Y--;
        } else if (moves[i] == 'E') {
          X++;
        } else if (moves[i] == 'W') {
          X--;
        }
        if (Math.abs(X) + Math.abs(Y) <= i + 1) {
          out.printf("Case #%d: %d%n", t, i + 1);
          valid = true;
          break;
        }
      }
      if (!valid) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
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
