package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2015_J4 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int t = 0;
    int[] time = new int[101];
    int[] total = new int[101];
    boolean[] received = new boolean[101];
    boolean[] isExist = new boolean[101];
    for (int x = 0; x < n; x++, t++) {
      char c = readCharacter();
      int f = readInt();
      if (c == 'R') {
        received[f] = true;
        time[f] = t;
        isExist[f] = true;
      } else if (c == 'S') {
        received[f] = false;
        total[f] += t - time[f];
      } else if (c == 'W') {
        t += f - 2;
      }
    }
    for (int x = 1; x <= 100; x++) {
      if (isExist[x]) {
        if (received[x])
          System.out.println(x + " -1");
        else
          System.out.println(x + " " + total[x]);
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
