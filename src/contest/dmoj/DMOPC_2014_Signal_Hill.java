package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Signal_Hill {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int q = readInt();
    Beacon[] b = new Beacon[n];
    boolean[][] adj = new boolean[n][n];
    for (int i = 0; i < n; i++)
      b[i] = new Beacon(readInt(), readInt(), readInt());
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (sqr(b[i].x - b[j].x) + sqr(b[i].y - b[j].y) <= sqr(b[i].r))
          adj[i][j] = true;
    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          adj[i][j] |= adj[i][k] && adj[k][j];
    for (int i = 0; i < q; i++)
      out.println(adj[readInt() - 1][readInt() - 1] ? "YES" : "NO");
    out.close();
  }

  static int sqr(int x) {
    return x * x;
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

  static class Beacon {
    int x, y, r;

    Beacon(int x, int y, int r) {
      this.x = x;
      this.y = y;
      this.r = r;
    }
  }
}
