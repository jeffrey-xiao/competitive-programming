package codejam;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GCJ_2018_Round_2_C {

  static final int NULL = 0;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int T;
  static int N;
  static int[][] A;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int leftSize, rightSize;
  static int[] pair, dist;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {

      N = readInt();
      leftSize = N;
      rightSize = N;

      A = new int[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          A[i][j] = readInt();
        }
        adj.add(new ArrayList<Integer>());
      }
      adj.add(new ArrayList<Integer>());

      int ans = 0;

      for (int i = -N; i <= N; i++) {
        if (i == 0) {
          continue;
        }

        for (int j = 0; j <= N; j++) {
          adj.get(j).clear();
        }

        for (int j = 0; j < N; j++) {
          for (int k = 0; k < N; k++) {
            if (A[j][k] == i) {
              adj.get(j + 1).add(leftSize + k + 1);
            }
          }
        }

        ans += getMaxMatching();
      }

      out.printf("Case #%d: %d\n", t, N * N - ans);
    }

    out.close();
  }

  static int getMaxMatching() {
    pair = new int[leftSize + rightSize + 1];
    dist = new int[leftSize + rightSize + 1];
    int res = 0;
    while (bfs())
      for (int i = 1; i <= leftSize; i++)
        if (pair[i] == NULL)
          res += dfs(i) ? 1 : 0;
    return res;
  }

  static boolean bfs() {
    Queue<Integer> q = new ArrayDeque<Integer>();
    for (int i = 1; i <= leftSize; i++) {
      if (pair[i] == NULL) {
        dist[i] = 0;
        q.offer(i);
      } else {
        dist[i] = 1 << 30;
      }
    }

    dist[NULL] = 1 << 30;

    while (!q.isEmpty()) {
      Integer curr = q.poll();

      if (dist[curr] >= dist[NULL])
        continue;

      for (int next : adj.get(curr)) {
        if (dist[pair[next]] == 1 << 30) {
          dist[pair[next]] = dist[curr] + 1;
          q.offer(pair[next]);
        }
      }
    }

    return dist[NULL] != 1 << 30;
  }

  static boolean dfs(int i) {
    if (i == NULL)
      return true;

    for (int j : adj.get(i)) {
      if (dist[pair[j]] == dist[i] + 1) {
        if (dfs(pair[j])) {
          pair[j] = i;
          pair[i] = j;
          return true;
        }
      }
    }

    dist[i] = 1 << 30;
    return false;
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
