package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1B_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, A, B;
  static int[] sampleX = {
      (int)(-1e9 + 1e9 / 2),
      (int)(-1e9 + 1e9 / 2),
      (int)(1e9 / 2),
      (int)(1e9 / 2),
      0
  };
  static int[] sampleY = {
      (int)(-1e9 + 1e9 / 2),
      (int)(1e9 / 2),
      (int)(-1e9 + 1e9 / 2),
      (int)(1e9 / 2),
      0
  };
  static int MIN = (int)-1e9;
  static int MAX = (int)1e9;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    A = readInt();
    B = readInt();

  main:
    for (int t = 1; t <= T; t++) {
      int x = 0, y = 0;
      for (int i = 0; i < 5; i++) {
        write(sampleX[i], sampleY[i]);
        String res = readLine();
        if (res.equals("CENTER")) {
          continue main;
        } else if (res.equals("HIT")) {
          x = sampleX[i];
          y = sampleY[i];
          break;
        }
      }

      // Finding vertical chord.
      int lo = MIN, hi = y;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        write(x, mid);
        String res = readLine();
        if (res.equals("CENTER")) {
          continue main;
        } else if (res.equals("HIT")) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      int loChord = lo;
      lo = y;
      hi = MAX;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        write(x, mid);
        String res = readLine();
        if (res.equals("CENTER")) {
          continue main;
        } else if (res.equals("HIT")) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
      int hiChord = hi;
      y = (hiChord + loChord) >> 1;

      // Finding diameter.
      lo = MIN;
      hi = x;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        write(mid, y);
        String res = readLine();
        if (res.equals("CENTER")) {
          continue main;
        } else if (res.equals("HIT")) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      int left = lo;

      lo = x;
      hi = MAX;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        write(mid, y);
        String res = readLine();
        if (res.equals("CENTER")) {
          continue main;
        } else if (res.equals("HIT")) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
      int right = hi;
      x = (left + right) >> 1;

      write(x, y);
      String res = readLine();
      assert res.equals("CENTER");
    }

    out.close();
  }

  static void write(int x, int y) {
    out.printf("%d %d%n", x, y);
    out.flush();
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
