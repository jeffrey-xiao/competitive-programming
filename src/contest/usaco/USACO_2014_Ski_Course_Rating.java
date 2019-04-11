package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class USACO_2014_Ski_Course_Rating {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int r, c;
  private static int[] id;
  private static int[] size;
  private static int[] min;

  public static int find(int i) {
    while (i != id[i]) {
      i = id[i];
    }
    return i;
  }

  public static int getLocalMin(int i) {
    while (min[i] == -1)
      i = id[i];
    return min[i];
  }

  public static boolean connected(int x, int y) {
    return find(x) == find(y);
  }

  public static void union(int x, int y) {
    int rootx = find(x);
    int rooty = find(y);
    if (rootx == rooty)
      return;
    if (size[rootx] < size[rooty]) {
      id[rootx] = id[rooty];
      size[rooty] += size[rootx];
    } else {
      id[rooty] = id[rootx];
      size[rootx] += size[rooty];
    }
  }

  public static void main(String[] args) throws IOException {
    r = readInt();
    c = readInt();
    int k = readInt();
    int n = r * c;
    id = new int[n];
    size = new int[n];
    min = new int[n];
    int[][] grid = new int[r][c];
    ArrayList<Integer> startPoints = new ArrayList<Integer>();

    for (int x = 0; x < n; x++) {
      id[x] = x;
      size[x] = 1;
      min[x] = -1;
    }

    for (int x = 0; x < r; x++)
      for (int y = 0; y < c; y++)
        grid[x][y] = readInt();
    for (int x = 0; x < r; x++)
      for (int y = 0; y < c; y++) {
        int i = readInt();
        if (i == 1)
          startPoints.add(toIndex(x, y));
      }

    ArrayList<Edge> edges = new ArrayList<Edge>();
    for (int x = 0; x < r - 1; x++)
      for (int y = 0; y < c; y++)
        edges.add(new Edge(toIndex(x, y), toIndex(x + 1, y), Math.abs(grid[x][y] - grid[x + 1][y])));
    for (int x = 0; x < r; x++)
      for (int y = 0; y < c - 1; y++)
        edges.add(new Edge(toIndex(x, y), toIndex(x, y + 1), Math.abs(grid[x][y] - grid[x][y + 1])));
    Collections.sort(edges);
    long total = 0;
    for (int x = 0; x < edges.size(); x++) {
      int a = edges.get(x).a;
      int b = edges.get(x).b;
      int c = edges.get(x).cost;
      if (find(a) == find(b))
        continue;

      if (size[find(a)] >= k && size[find(b)] < k && min[find(b)] == -1)
        min[find(b)] = c;
      else if (size[find(b)] >= k && size[find(a)] < k && min[find(a)] == -1)
        min[find(a)] = c;

      union(a, b);
      if (size[find(a)] >= k && min[find(a)] == -1)
        min[find(a)] = c;
    }
    for (int x = 0; x < startPoints.size(); x++) {
      if (min[startPoints.get(x)] == -1)
        total += getLocalMin(startPoints.get(x));
      else
        total += getLocalMin(startPoints.get(x));
    }
    System.out.println(total);
  }

  static int toIndex(int x, int y) {
    return x * c + y;
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
    int a, b, cost;

    Edge(int a, int b, int cost) {
      this.a = a;
      this.b = b;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return cost - o.cost;
    }
  }
}
