import java.io.*;
import java.util.*;

public class I {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, T, D;
  static long[][] adj;
  static int[] repairLocations;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    T = readInt();
    D = readInt();
    adj = new long[N][N];
    repairLocations = new int[T + 2];

    for (int i = 1; i <= T; i++) {
      repairLocations[i] = readInt() - 1;
    }
    repairLocations[T + 1] = N - 1;

    for (int i = 0; i < M; i++) {
      int x = readInt() - 1;
      int y = readInt() - 1;
      int d = readInt();
      adj[x][y] = adj[y][x] = d;
    }

    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (adj[i][k] != 0 && adj[k][j] != 0) {
            if (adj[i][j] == 0) {
              adj[i][j] = adj[i][k] + adj[k][j];
            } else {
              adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
            }
          }
        }
      }
    }

    long[] minDist = new long[T + 2];
    boolean[] vis = new boolean[T + 2];
    Arrays.fill(minDist, 1L << 60);
    minDist[0] = 0;
    for (int i = 0; i <= T; i++) {
      int minIndex = -1;
      for (int j = 0; j <= T + 1; j++) {
        if (!vis[j] && (minIndex == -1 || minDist[minIndex] > minDist[j])) {
          minIndex = j;
        }
      }
      vis[minIndex] = true;
      for (int j = 0; j <= T + 1; j++) {
        if (minIndex == j) {
          continue;
        }
        if (adj[repairLocations[minIndex]][repairLocations[j]] > D || adj[repairLocations[minIndex]][repairLocations[j]] == 0) {
          continue;
        }
        minDist[j] = Math.min(minDist[j], minDist[minIndex] + adj[repairLocations[minIndex]][repairLocations[j]]);
      }
    }
    if (minDist[T + 1] == 1L << 60) {
      out.println("stuck");
    } else {
      out.println(minDist[T + 1]);
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
