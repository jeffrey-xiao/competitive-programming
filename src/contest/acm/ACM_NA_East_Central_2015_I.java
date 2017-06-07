package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACM_NA_East_Central_2015_I {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    char[][] g = new char[N][N];
    int index = 0;
    for (int i = 0; i < N; i++)
      g[i] = readLine().toCharArray();
    String s = readLine();

    char[][] ret = new char[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        ret[i][j] = '\u0000';

    boolean valid = true;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < N; j++)
        for (int k = 0; k < N; k++) {
          if (g[j][k] == '.') {
            if (index >= s.length())
              valid = false;
            else
              ret[j][k] = s.charAt(index++);
          }
        }
      rotate(g, N);
      rotate(g, N);
      rotate(g, N);
    }

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        if (ret[i][j] == '\u0000' || !valid) {
          out.println("invalid grille");
          out.close();
          return;
        }

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        out.print(ret[i][j]);
    out.println();
    out.close();
  }

  static void rotate (char[][] g, int N) {
    for (int i = 0; i < N / 2; i++)
      for (int j = 0; j < (N + 1) / 2; j++) {
        char temp = g[i][j];
        g[i][j] = g[j][N - 1 - i];
        g[j][N - 1 - i] = g[N - 1 - i][N - 1 - j];
        g[N - 1 - i][N - 1 - j] = g[N - 1 - j][i];
        g[N - 1 - j][i] = temp;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}