package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Encryption {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    String s = next();
    int len = s.length();
    int width = (int)(Math.ceil(Math.sqrt(len)));
    int height = (width * (width - 1) >= len) ? width - 1 : width;
    char[][] g = new char[height][width];
    for (int i = 0, k = 0; i < height; i++)
      for (int j = 0; j < width; j++)
        if (k < len)
          g[i][j] = s.charAt(k++);
    StringBuilder res = new StringBuilder();
    for (int j = 0; j < width; j++, res.append(" "))
      for (int i = 0; i < height; i++)
        if (g[i][j] >= 'a' && g[i][j] <= 'z')
          res.append(g[i][j]);

    out.println(res.toString());

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