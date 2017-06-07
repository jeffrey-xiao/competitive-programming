package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2015_Qualification_Cooking_The_Books {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    for (int t = 1; t <= n; t++) {
      char[] curr = next().toCharArray();
      int max = Integer.parseInt(new String(curr));
      int min = max;
      for (int x = 0; x < curr.length; x++) {
        for (int y = x + 1; y < curr.length; y++) {
          if (curr[y] == '0' && x == 0)
            continue;
          swap(curr, x, y);
          Integer next = Integer.parseInt(new String(curr));
          max = Math.max(max, next);
          min = Math.min(min, next);
          swap(curr, x, y);
        }
      }
      out.printf("Case #%d: %d %d\n", t, min, max);
    }

    out.close();
  }

  private static void swap (char[] curr, int x, int y) {
    char temp = curr[x];
    curr[x] = curr[y];
    curr[y] = temp;
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
