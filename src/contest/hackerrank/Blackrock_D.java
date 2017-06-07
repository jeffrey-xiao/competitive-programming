package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Blackrock_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<ArrayList<Integer>> adj;
  static int[] val;
  static long[][] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    val = new int[N];
    dp = new long[N][2];
    adj = new ArrayList<ArrayList<Integer>>(N);

    Queue<Integer> q = new ArrayDeque<Integer>();
    int curr = -1;
    int cnt = 0;

    for (int i = 0; i < N;) {
      String c = next();
      if (!c.equals("#")) {
        adj.add(new ArrayList<Integer>());
        q.offer(i);

        if (curr != -1) {
          adj.get(curr).add(i);
        } else {
          curr = i;
          cnt = -1;
          q.poll();
        }
        cnt++;
        val[i] = Integer.parseInt(c);
        i++;
      } else {
        cnt++;
      }

      if (cnt == 2) {
        curr = q.poll();
        cnt = 0;
      }
    }

    solve(0);

    out.println(Math.max(dp[0][0], dp[0][1]));
    out.close();
  }

  static void solve (int u) {
    for (int v : adj.get(u))
      solve(v);

    long take = val[u];
    long noTake = 0;

    for (int v : adj.get(u)) {
      take += dp[v][0];
      noTake += Math.max(dp[v][0], dp[v][1]);
    }

    dp[u][0] = noTake;
    dp[u][1] = take;
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