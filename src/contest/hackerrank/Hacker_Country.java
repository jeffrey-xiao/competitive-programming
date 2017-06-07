package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Hacker_Country {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int n, m;
  static boolean[] v;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int[][] dist = new int[n + 1][n];
    int[][] adj = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adj[i][j] = readInt();
      }
      adj[i][i] = 1 << 30;
    }
    for (int i = 1; i <= n; i++)
      for (int j = 0; j < n; j++)
        dist[i][j] = 1 << 29;

    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        for (int k = 0; k < n; k++)
          if (dist[i][k] + adj[k][j] < dist[i + 1][j])
            dist[i + 1][j] = dist[i][k] + adj[k][j];
    double min = 1 << 30;
    int top = 0;
    int bottom = 0;
    for (int i = 0; i < n; i++) {
      double best = ((double)(dist[n][i] - dist[0][i])) / n;
      int newTop = dist[n][i] - dist[0][i];
      int newBottom = n;
      for (int j = 1; j < n; j++) {
        double val = (dist[n][i] - dist[j][i]) / ((double)(n - j));
        if (val > best) {
          best = val;
          newTop = dist[n][i] - dist[j][i];
          newBottom = n - j;
        }
      }
      if (best < min) {
        min = best;
        top = newTop;
        bottom = newBottom;
      }
    }
    int gcf = gcf(top, bottom);
    top /= gcf;
    bottom /= gcf;
    out.println(top + "/" + bottom);
    out.close();
  }

  static int gcf (int x, int y) {
    return y == 0 ? x : gcf(y, x % y);
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