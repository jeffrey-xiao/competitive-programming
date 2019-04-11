/*
 * An adjacency matrix is a data structure that represents a graph. Good for dense graphs.
 */

package codebook.graph.representation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AdjacencyMatrix {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[][] adj;
  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    adj = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adj[i][j] = -1;
      }
      adj[i][i] = 0;
    }
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj[a][b] = adj[b][a] = c;
    }

    for (int i = 0; i < n; i++) {
      out.print(i + " IS CONNECTED TO: ");
      for (int j = 0; j < n; j++)
        if (adj[i][j] > 0)
          out.print(j + " ");
      out.println();
    }
    out.close();
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
}
