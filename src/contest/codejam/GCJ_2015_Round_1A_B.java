package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1A_B {

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
      int B = readInt();
      int N = readInt();
      int[] m = new int[B];
      for (int i = 0; i < B; i++) {
        m[i] = readInt();
      }
      long lo = 0;
      long hi = (long)(1e15);
      while (lo <= hi) {
        long mid = (lo + hi) >> 1;
        long total = 0;
        for (int i = 0; i < B; i++) {
          total += mid / m[i] + 1;
        }
        if (total < N) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
      ArrayList<Integer> indexes = new ArrayList<Integer>();
      indexes.add(0);
      long total = lo / m[0] + 1;
      for (int i = 1; i < B; i++) {
        total += lo / m[i] + 1;
        if (lo % m[i] < lo % m[indexes.get(0)]) {
          indexes.clear();
          indexes.add(i);
        } else if (lo % m[i] == lo % m[indexes.get(0)]) {
          indexes.add(i);
        }
      }
      out.printf("Case #%d: %d%n", t, indexes.get((int)(indexes.size() - (total - N) - 1)) + 1);
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
