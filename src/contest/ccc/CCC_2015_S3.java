package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_2015_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int g = readInt();
    int p = readInt();

    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int x = 1; x <= g; x++)
      ts.add(x);
    int count = 0;
    boolean fail = false;
    for (int x = 0; x < p; x++) {
      int ng = readInt();
      Integer i = ts.floor(ng);
      if (i != null && !fail) {
        count++;
        ts.remove(i);
      } else {
        fail = true;
      }
    }
    System.out.println(count);

    ps.close();
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
