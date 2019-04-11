package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class WOC_29_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K;
  static int[][] adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    adj = new int[N][K];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < K; j++)
        adj[i][j] = (i * K + j) % N;

    // after K steps from node i the node will be im^k +
    // m^(k - 1) * j_(k-1) + m^(k - 2) * j_(k - 2) * ... * j_0 * m^0 mod n
    // we can cover all remainders modulo n with m^k >= n

    int diameter = 0;
    for (int i = 0; i < N; i++)
      diameter = Math.max(diameter, getLongestDistance(i));

    out.println(diameter);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < K; j++)
        out.print(adj[i][j] + " ");
      out.println();
    }

    out.close();
  }

  static int getLongestDistance(int u) {
    Queue<Integer> q = new ArrayDeque<Integer>();
    int[] dist = new int[N];
    Arrays.fill(dist, 1 << 30);
    dist[u] = 0;
    q.offer(u);

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int j = 0; j < adj[curr].length; j++) {
        int next = adj[curr][j];
        if (dist[next] != 1 << 30)
          continue;
        dist[next] = dist[curr] + 1;
        q.offer(next);
      }
    }
    int ret = 0;
    for (int i = 0; i < N; i++)
      ret = Math.max(ret, dist[i]);
    return ret;
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
