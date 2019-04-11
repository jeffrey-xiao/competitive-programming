package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Winter_E {

  static final int NULL = 0;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int leftSize, rightSize, n;
  static int[] pair, dist;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    while (br.ready()) {
      int N = readInt();
      leftSize = 0;
      rightSize = 0;
      adj.clear();

      int[] X = new int[N];
      int[] Y = new int[N];

      HashMap<Integer, Integer> left = new HashMap<Integer, Integer>();
      HashMap<Integer, Integer> right = new HashMap<Integer, Integer>();
      for (int i = 0; i < N; i++) {
        X[i] = readInt();
        Y[i] = readInt();
        if ((X[i] + Y[i]) % 2 == 0) {
          leftSize++;
          left.put(i, leftSize);
        } else {
          rightSize++;
          right.put(i, rightSize);
        }
      }

      for (int i = 0; i < leftSize + rightSize + 1; i++)
        adj.add(new ArrayList<Integer>());

      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          if (Math.abs(X[i] - X[j]) + Math.abs(Y[i] - Y[j]) == 1) {
            if ((X[i] + Y[i]) % 2 == 0) {
              adj.get(left.get(i)).add(leftSize + right.get(j));
            } else {
              adj.get(left.get(j)).add(leftSize + right.get(i));
            }
          }
        }
      }

      System.out.println(N - getMaxMatching());
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
