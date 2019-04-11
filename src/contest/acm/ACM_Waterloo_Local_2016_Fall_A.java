package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_Waterloo_Local_2016_Fall_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int NULL = 0;

  static int R, C, K;
  static int leftSize, rightSize;
  static boolean[][] dead;
  static boolean[] vis;
  static int[] pair, dist;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    C = readInt();
    R = readInt();

    leftSize = (R * C) / 2;
    rightSize = (R * C + 1) / 2;

    for (int i = 0; i < leftSize + rightSize + 1; i++)
      adj.add(new ArrayList<Integer>());

    K = readInt();
    dead = new boolean[R][C];
    for (int i = 0; i < K; i++) {
      int c = readInt();
      int r = readInt();
      dead[r][c] = true;
    }

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        boolean isLeft = (i + j) % 2 == 1;
        if (j != C - 1 && !dead[i][j] && !dead[i][j + 1]) {
          if (isLeft)
            adj.get((i * C + j) / 2 + 1).add(leftSize + (i * C + j + 1) / 2 + 1);
          else
            adj.get((i * C + j + 1) / 2 + 1).add(leftSize + (i * C + j) / 2 + 1);
        }
        if (i != R - 1 && isLeft && !dead[i][j] && !dead[i + 1][j]) {
          if (isLeft)
            adj.get((i * C + j) / 2 + 1).add(leftSize + ((i + 1) * C + j) / 2 + 1);
          else
            adj.get(((i + 1) * C + j) / 2 + 1).add(leftSize + (i * C + j) / 2 + 1);
        }
      }
    }
    int matched = getMaxMatching();
    out.println(matched);
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
      if (dist[pair[j]] == dist[i] + 1)
        if (dfs(pair[j])) {
          pair[j] = i;
          pair[i] = j;
          return true;
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
