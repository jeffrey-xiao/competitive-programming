package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Busy_Schedule {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int[][] adj;
  static int[] index;
  static int[] start;
  static int[] duration;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    adj = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adj[i][j] = 1 << 28;
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
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          adj[j][k] = Math.min(adj[j][k], adj[j][i] + adj[i][k]);
        }
      }
    }
    int k = readInt();
    index = new int[k];
    start = new int[k];
    duration = new int[k];
    for (int i = 0; i < k; i++) {
      int a = readInt() - 1;
      int b = readInt();
      int c = readInt();
      index[i] = a;
      start[i] = b;
      duration[i] = c;
    }

    int curr = 0;
    int time = 0;
    for (int i = 0; i < k; i++) {
      if (adj[curr][index[i]] + time <= start[i]) {
        time = start[i] + duration[i];
        curr = index[i];
        System.out.println(i + 1);
      }
    }

    pr.close();
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
