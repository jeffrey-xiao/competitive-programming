package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_VAUVAU {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int a = readInt();
    int b = readInt();
    int c = readInt();
    int d = readInt();

    for (int x = 0; x < 3; x++) {
      int man = readInt();
      if (man % (a + b) <= a && man % (a + b) > 0 && man % (c + d) <= c && man % (c + d) > 0)
        System.out.println("both");
      else if ((man % (a + b) <= a && man % (a + b) > 0) || (man % (c + d) <= c && man % (c + d) > 0))
        System.out.println("one");
      else
        System.out.println("none");
    }
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }

  int gcf (int a, int b) {
    if (b == 0)
      return a;
    if (b == 1)
      return 1;
    return gcf(b, a % b);
  }

}
