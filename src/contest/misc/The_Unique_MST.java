package contest.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class The_Unique_MST {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] size, id;

  public static void main(String[] args) throws IOException {
    int t = readInt();
    for (int k = 0; k < t; k++) {
      ArrayList<Edge> e = new ArrayList<Edge>();
      int n = readInt();
      int m = readInt();
      size = new int[n];
      id = new int[n];
      for (int x = 0; x < n; x++)
        id[x] = x;
      for (int x = 0; x < m; x++)
        e.add(new Edge(readInt() - 1, readInt() - 1, readInt()));
      Collections.sort(e);
      int total = 0;
      ArrayList<Integer> used = new ArrayList<Integer>();
      for (int x = 0; x < m; x++) {
        int rootx = find(e.get(x).src);
        int rooty = find(e.get(x).dest);
        if (rootx == rooty)
          continue;
        total += e.get(x).cost;
        used.add(x);
        union(rootx, rooty);
      }
      int minCost = Integer.MAX_VALUE;
      for (Integer x : used) {
        int cost = 0;
        for (int y = 0; y < n; y++) {
          id[y] = y;
          size[y] = 0;
        }
        for (int y = 0; y < m; y++) {
          if (x == y)
            continue;
          int rootx = find(e.get(y).src);
          int rooty = find(e.get(y).dest);
          if (rootx == rooty)
            continue;
          union(rootx, rooty);
          cost += e.get(y).cost;
        }
        minCost = Math.min(cost, minCost);
      }
      if (minCost == total) {
        System.out.println("Not Unique!");
      } else {
        System.out.println(total);
      }
    }
  }

  private static int find(int x) {
    while (id[x] != x)
      x = id[x];
    return x;
  }

  private static void union(int rootx, int rooty) {
    if (size[rootx] > size[rooty]) {
      size[rootx] += size[rooty];
      id[rooty] = id[rootx];
    } else {
      size[rooty] += size[rootx];
      id[rootx] = id[rooty];
    }
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

  static class Edge implements Comparable<Edge> {
    int src, dest, cost;

    Edge(int src, int dest, int cost) {
      this.src = src;
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return cost - o.cost;
    }
  }
}
