package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: movie
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class movie {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int n;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("movie.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("movie.out")));

    n = readInt();
    int t = readInt();
    int[] times = new int[n];
    int[] v = new int[1 << 20];
    int[] count = new int[1 << 20];
    for (int x = 0; x < 1 << 20; x++) {
      count[x] = count(x);
      v[x] = -1;
    }
    ArrayList<TreeSet<Integer>> ts = new ArrayList<TreeSet<Integer>>();
    for (int x = 0; x < n; x++)
      ts.add(new TreeSet<Integer>());
    Queue<State> q = new LinkedList<State>();
    for (int x = 0; x < n; x++) {
      times[x] = readInt();
      for (int y = readInt(); y > 0; y--) {
        int a = readInt();
        ts.get(x).add(a);
        if (a == 0) {
          q.offer(new State(x, 0, 0));
        }
      }
    }

    int min = Integer.MAX_VALUE;
    while (!q.isEmpty()) {
      State curr = q.poll();
      int nv = curr.v | 1 << curr.movie;
      int nt = curr.index + times[curr.movie];
      if (nt >= t) {
        min = Math.min(min, count[nv]);
      }
      for (int x = 0; x < n; x++) {
        if (((nv)&1 << x) != 0)
          continue;
        Integer nextMovie = ts.get(x).floor(nt);
        if (nextMovie == null || v[nv] >= nextMovie || nextMovie + times[x] <= nt)
          continue;
        v[nv] = nextMovie;
        q.offer(new State(x, nextMovie, nv));
      }
    }

    if (min == Integer.MAX_VALUE)
      pr.println(-1);
    else
      pr.println(min);
    pr.close();
    System.exit(0);
  }

  private static int count(int nv) {
    int c = 0;
    for (int x = 0; x < n; x++) {
      if ((nv & 1 << x) != 0)
        c++;
    }
    return c;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class State {
    int v, index, movie;

    State(int movie, int index, int v) {
      this.movie = movie;
      this.index = index;
      this.v = v;
    }
  }
}
