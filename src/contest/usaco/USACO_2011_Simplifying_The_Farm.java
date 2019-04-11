package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class USACO_2011_Simplifying_The_Farm {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    WeightedQuickUnion q = new WeightedQuickUnion(n);
    Edge[] edges = new Edge[m];
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      edges[x] = new Edge(a, b, c);
    }
    long counter = 1;
    int totalCost = 0;
    Arrays.sort(edges);
    for (int x = 0; x < m && q.count > 1; ) {
      int y = x;
      int count = 0;
      int total = 0;
      HashSet<Edge> e = new HashSet<Edge>();
      for (; y < m && edges[x].cost == edges[y].cost; y++) {
        Edge curr = edges[y];
        int roota = q.root(curr.source);
        int rootb = q.root(curr.dest);
        int a = Math.max(roota, rootb);
        int b = Math.min(roota, rootb);

        if (!q.find(a, b)) {
          e.add(new Edge(a, b, 0));
          total++;
        }
      }
      int size = e.size();
      for (; x < y; x++) {
        count += q.union(edges[x].source, edges[x].dest) ? 1 : 0;
      }
      totalCost += count * (x - 1 < 0 ? 0 : edges[x - 1].cost);
      if (total == 3) {
        // one or two works and there are three edges
        if (count == 1 || (count == 2 && size == 3))
          counter = (counter * 3) % 1000000007;
        // two works and there are three edges
        if (count == 2 && size == 2)
          counter = (counter * 2) % 1000000007;
      }
      // one works and there are two edges
      else if (total == 2 && count == 1)
        counter = (counter * 2) % 1000000007;
    }
    System.out.println(totalCost + " " + counter % 1000000007);
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

  static class Edge implements Comparable<Edge> {
    int source;
    int dest;
    int cost;

    Edge(int source, int dest, int cost) {
      this.source = source;
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Edge) {
        Edge e = (Edge) (o);
        return source == e.source && dest == e.dest;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return source * 100000 + dest;
    }
  }

  static class WeightedQuickUnion {
    private int[] id;
    private int[] size;
    private int count;

    public WeightedQuickUnion(int n) {
      id = new int[n];
      size = new int[n];
      count = n;
      for (int x = 0; x < n; x++) {
        id[x] = x;
        size[x] = 1;
      }
    }

    public int root(int i) {
      while (i != id[i]) {
        id[i] = id[id[i]];
        i = id[i];
      }
      return i;
    }

    public boolean find(int x, int y) {
      return root(x) == root(y);
    }

    public int count() {
      return count;
    }

    public boolean union(int x, int y) {

      int rootx = root(x);
      int rooty = root(y);
      if (rootx == rooty)
        return false;
      count--;
      if (size[rootx] < size[rooty]) {
        id[rootx] = id[rooty];
        size[rooty] += size[rootx];
      } else {
        id[rooty] = id[rootx];
        size[rootx] += size[rooty];
      }
      return true;
    }
  }
}
