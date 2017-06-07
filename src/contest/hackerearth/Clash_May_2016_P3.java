package contest.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Clash_May_2016_P3 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int W, H;
  static boolean[][] taken;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    W = readInt();
    H = readInt();
    taken = new boolean[W + 1][H + 1];

    if ((W == 1) ^ (H == 1))
      out.println("NO");
    else {
      out.println("YES");
      out.printf("%d %d\n", W, 1);
      taken[W][1] = true;
      out.printf("%d %d\n", 1, H);
      taken[1][H] = true;
      out.printf("%d %d\n", 1, 1);
      taken[1][1] = true;

      for (int i = 1; i <= W; i++)
        for (int j = 1; j <= H; j++)
          if (!taken[i][j])
            out.printf("%d %d\n", i, j);
    }

    out.close();
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
