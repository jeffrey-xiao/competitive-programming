package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IOI_2007_Flood {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static Point[] p;
  static int[] sum;
  static Edge[][] map; // 0 = north, 1 = east, 2 = south, 3 = west
  static int[][] dir = {{1, 0, 3, 2}, {2, 1, 0, 3}, {3, 2, 1, 0}, {0, 3, 2, 1}};
  static TreeSet<Point> active = new TreeSet<Point>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    map = new Edge[n][4];
    p = new Point[n];

    for (int i = 0; i < n; i++)
      active.add(p[i] = new Point(readInt(), readInt(), i));

    m = readInt();
    sum = new int[m];
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      // west-east relation
      if (p[a].x < p[b].x) {
        map[a][1] = new Edge(a, b, i);
        map[b][3] = new Edge(b, a, i);
      } else if (p[a].x > p[b].x) {
        map[a][3] = new Edge(a, b, i);
        map[b][1] = new Edge(b, a, i);
      }
      // north-south relation
      if (p[a].y > p[b].y) {
        map[a][2] = new Edge(a, b, i);
        map[b][0] = new Edge(b, a, i);
      } else if (p[a].y < p[b].y) {
        map[a][0] = new Edge(a, b, i);
        map[b][2] = new Edge(b, a, i);
      }
    }
    while (!active.isEmpty()) {
      int start = active.last().index;
      int currPos = active.last().index;
      int currDir = 0;
      HashSet<Edge> remove = new HashSet<Edge>();
      do {
        for (int i = 0; i < 4; i++) {
          if (map[currPos][dir[currDir][i]] != null) {
            remove.add(map[currPos][dir[currDir][i]]);
            currPos = map[currPos][dir[currDir][i]].b;
            currDir = dir[currDir][i];
            break;
          }
        }
      } while (currPos != start);
      for (Edge e : remove)
        remove(e);
      while (!active.isEmpty()) {
        int cnt = 0;
        cnt = 0;
        for (int i = 0; i < 4; i++)
          if (map[active.last().index][i] != null)
            cnt++;
        if (cnt != 0)
          break;
        active.remove(active.last());
      }
    }
    ArrayList<Integer> ans = new ArrayList<Integer>();
    for (int i = 0; i < m; i++)
      if (sum[i] == 2)
        ans.add(i + 1);
    out.println(ans.size());
    for (int x : ans)
      out.println(x);
    out.close();
  }

  static void remove(Edge e) {
    sum[e.index]++;
    for (int i = 0; i < 4; i++)
      if (map[e.a][i] != null && map[e.a][i].b == e.b)
        map[e.a][i] = null;
    for (int i = 0; i < 4; i++)
      if (map[e.b][i] != null && map[e.b][i].b == e.a)
        map[e.b][i] = null;
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

  static class Edge {
    int a, b, index;

    Edge(int a, int b, int index) {
      this.a = a;
      this.b = b;
      this.index = index;
    }
  }

  static class Point implements Comparable<Point> {
    int x, y, index;

    Point(int x, int y, int index) {
      this.x = x;
      this.y = y;
      this.index = index;
    }

    @Override
    public int compareTo(Point p) {
      if (x == p.x)
        return y - p.y;
      return x - p.x;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return p.x == x && p.y == y;
      }
      return false;
    }
  }
}
