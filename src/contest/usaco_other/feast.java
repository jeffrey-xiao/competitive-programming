package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: feast
*/
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class feast {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("feast.in"));
    out = new PrintWriter(new FileWriter("feast.out"));
    //br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));

    int n = readInt();
    int a = readInt();
    int b = readInt();
    boolean[][] v = new boolean[n + 1][2];
    Queue<State> q = new ArrayDeque<State>();
    v[0][0] = true;
    q.offer(new State(0, 0));
    while (!q.isEmpty()) {
      State curr = q.poll();
      int next = 0;
      if (curr.full + a <= n) {
        next = curr.full + a;
        if (!v[next][curr.water]) {
          v[next][curr.water] = true;
          q.offer(new State(next, curr.water));
        }
      }
      if (curr.full + b <= n) {
        next = curr.full + b;
        if (!v[next][curr.water]) {
          v[next][curr.water] = true;
          q.offer(new State(next, curr.water));
        }
      }
      if (curr.water == 0) {
        next = curr.full / 2;
        if (!v[next][1]) {
          v[next][1] = true;
          q.offer(new State(next, 1));
        }
      }
    }
    int ans = 0;
    for (int i = 0; i <= n; i++)
      if (v[i][0] || v[i][1])
        ans = i;
    out.println(ans);

    out.close();
    System.exit(0);
  }

  static class State {
    int full, water;

    State (int full, int water) {
      this.full = full;
      this.water = water;
    }
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
