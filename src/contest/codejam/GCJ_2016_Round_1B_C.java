import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1B_C {

  static final int NULL = 0;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static ArrayList<ArrayList<Integer>> adj;
  static int[] pair, dist;
  static HashMap<String, Integer> left, right;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      left = new HashMap<String, Integer>();
      right = new HashMap<String, Integer>();
      adj = new ArrayList<ArrayList<Integer>>();

      for (int i = 0; i < 2 * N; i++) {
        adj.add(new ArrayList<Integer>());
      }

      for (int i = 0; i < N; i++) {
        String leftToken = next();
        String rightToken = next();
        if (!left.containsKey(leftToken)) {
          left.put(leftToken, left.size() + 1);
        }
        if (!right.containsKey(rightToken)) {
          right.put(rightToken, right.size() + 1);
        }
        adj.get(left.get(leftToken)).add(N + right.get(rightToken));
      }
      out.printf("Case #%d: %d\n", t, N - (left.size() + right.size() - getMaxMatching()));
    }

    out.close();
  }

  static int getMaxMatching() {
    pair = new int[2 * N + 1];
    dist = new int[2 * N + 1];
    int res = 0;
    while (bfs())
      for (int i = 1; i <= N; i++)
        if (pair[i] == NULL)
          res += dfs(i) ? 1 : 0;
    return res;
  }

  static boolean bfs() {
    Queue<Integer> q = new ArrayDeque<Integer>();
    for (int i = 1; i <= N; i++) {
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
