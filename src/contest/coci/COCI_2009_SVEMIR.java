package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2009_SVEMIR {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] union;
  static int[] size;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    union = new int[n];
    size = new int[n];
    Planet[] p = new Planet[n];
    ArrayList<Edge> adj = new ArrayList<Edge>();
    for (int x = 0; x < n; x++) {
      p[x] = new Planet(readInt(), readInt(), readInt(), x);
      union[x] = x;
      size[x] = 1;
    }

    // sorting by x-coordinates
    Arrays.sort(p, new Comparator<Planet>() {
      @Override
      public int compare(Planet o1, Planet o2) {
        return o1.x - o2.x;
      }
    });

    for (int x = 1; x < n; x++)
      adj.add(new Edge(p[x - 1].index, p[x].index, getDist(p[x], p[x - 1])));

    // sorting by y-coordinates
    Arrays.sort(p, new Comparator<Planet>() {
      @Override
      public int compare(Planet o1, Planet o2) {
        return o1.y - o2.y;
      }
    });

    for (int x = 1; x < n; x++)
      adj.add(new Edge(p[x - 1].index, p[x].index, getDist(p[x], p[x - 1])));

    // sorting by z-coordinates
    Arrays.sort(p, new Comparator<Planet>() {
      @Override
      public int compare(Planet o1, Planet o2) {
        return o1.z - o2.z;
      }
    });

    for (int x = 1; x < n; x++)
      adj.add(new Edge(p[x - 1].index, p[x].index, getDist(p[x], p[x - 1])));

    Collections.sort(adj);

    int cost = 0;
    for (int x = 0; x < adj.size(); x++) {
      int a = adj.get(x).x;
      int b = adj.get(x).y;
      if (find(union[a]) != find(union[b])) {
        cost += adj.get(x).cost;
        union(a, b);
      }
    }
    System.out.println(cost);
  }

  private static int find(int id) {
    while (id != union[id]) {
      id = union[id];
    }
    return id;
  }

  private static void union(int x, int y) {
    int findx = find(x);
    int findy = find(y);
    if (size[findx] >= size[findy]) {
      size[findx] += size[findy];
      union[findy] = findx;
    } else {
      size[findy] += size[findx];
      union[findx] = findy;
    }
  }

  private static int getDist(Planet p1, Planet p2) {
    int x = Math.abs(p1.x - p2.x);
    int y = Math.abs(p1.y - p2.y);
    int z = Math.abs(p1.z - p2.z);
    return Math.min(x, Math.min(y, z));
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

  static class Planet {
    int x, y, z, index;

    Planet(int x, int y, int z, int index) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.index = index;
    }
  }

  static class Edge implements Comparable<Edge> {
    int x, y, cost;

    Edge(int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o1) {
      return cost - o1.cost;
    }
  }
}
