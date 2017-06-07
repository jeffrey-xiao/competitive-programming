package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class R1C_C {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int tt = readInt();
    for (int qq = 1; qq <= tt; qq++) {
      int c = readInt();
      int d = readInt();
      int v = readInt();
      int ans = 0;
      TreeSet<Integer> canMake = new TreeSet<Integer>();
      canMake.add(0);
      for (int i = 0; i < d; i++) {
        int n = readInt();
        TreeSet<Integer> curr = new TreeSet<Integer>();
        for (int j = 1; j <= c; j++) {
          for (int k : canMake)
            if (n * j + k <= v)
              curr.add(n * j + k);
        }
        canMake.addAll(curr);
      }

      pr.printf("Case #%d: %d\n", qq, ans);
    }

    pr.close();
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
