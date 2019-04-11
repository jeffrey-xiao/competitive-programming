package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: beads
 */
import java.util.StringTokenizer;

public class beads {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("beads.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int n = readInt();
    String s = next();
    char last = ' ';
    int max = 1;

    for (int x = 0; x < n; x++) {

      last = s.charAt(x);
      int count = 1;
      int next = x + 1;

      if (next >= n)
        next = 0;
      while (next != x) {
        if (last == 'w' && s.charAt(next) != 'w')
          last = s.charAt(next);
        if (s.charAt(next) == last || s.charAt(next) == 'w') {
          count++;
        } else
          break;
        next++;
        if (next >= n)
          next = 0;
      }

      if (next != x) {
        next--;
        if (next < 0)
          next += n;
        last = s.charAt((x - 1) < 0 ? n - 1 : x - 1);
        count++;
        int prev = x - 2;
        if (prev < 0)
          prev += n;
        while (prev != next) {
          if (last == 'w' && s.charAt(prev) != 'w')
            last = s.charAt(prev);
          if (s.charAt(prev) == last || s.charAt(prev) == 'w')
            count++;
          else
            break;
          prev--;
          if (prev < 0)
            prev = n - 1;
        }
      }
      max = Math.max(max, count);
    }

    pr.println(max);
    pr.close();
    System.exit(0);
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
