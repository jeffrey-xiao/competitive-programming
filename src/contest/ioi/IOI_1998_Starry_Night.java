package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class IOI_1998_Starry_Night {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int H, W;
  static char[][] g;

  static ArrayList<Cluster> clusters;
  static ArrayList<Point> curr;

  static int[] mover = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] movec = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    W = readInt();
    H = readInt();

    g = new char[H][W];
    clusters = new ArrayList<Cluster>();
    curr = new ArrayList<Point>();

    for (int i = 0; i < H; i++)
      g[i] = readLine().toCharArray();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (g[i][j] == '1') {
          curr = new ArrayList<Point>();
          dfs(i, j);

          int minR = 1 << 30, minC = 1 << 30, maxR = 0, maxC = 0;

          for (Point p : curr) {
            minR = Math.min(minR, p.x);
            maxR = Math.max(maxR, p.x);

            minC = Math.min(minC, p.y);
            maxC = Math.max(maxC, p.y);
          }

          Cluster c = new Cluster(maxR - minR + 1, maxC - minC + 1);
          for (Point p : curr)
            c.points.add(new Point(p.x - minR, p.y - minC));

          boolean exists = false;
          int index = clusters.size();

          for (int q = 0; q < clusters.size(); q++) {
            Cluster cluster = clusters.get(q);
            for (int k = 0; k < 2; k++) {
              for (int l = 0; l < 4; l++) {
                c.rotate();
                if (equals(c.points, cluster.points)) {
                  exists = true;
                  index = q;
                }
              }
              c.flip();
            }
          }

          for (Point p : curr) {
            g[p.x][p.y] = (char)('a' + index);
          }

          if (!exists) {
            clusters.add(c);
          }
        }
      }
    }

    for (int i = 0; i < H; i++)
      out.println(new String(g[i]));

    out.close();
  }

  static boolean equals (ArrayList<Point> p1, ArrayList<Point> p2) {
    Collections.sort(p1);
    Collections.sort(p2);
    if (p1.size() != p2.size())
      return false;

    for (int i = 0; i < p1.size(); i++)
      if (!p1.get(i).equals(p2.get(i)))
        return false;
    return true;
  }

  static void dfs (int r, int c) {
    g[r][c] = '0';
    curr.add(new Point(r, c));

    for (int i = 0; i < 8; i++) {
      int nr = r + mover[i];
      int nc = c + movec[i];

      if (nr < 0 || nr >= H || nc < 0 || nc >= W || g[nr][nc] != '1')
        continue;
      dfs(nr, nc);
    }
  }

  static class Cluster {
    int height, width;
    ArrayList<Point> points;

    Cluster (int height, int width) {
      this.height = height;
      this.width = width;
      this.points = new ArrayList<Point>();
    }

    void rotate () {
      int newHeight = width;
      int newWidth = height;

      for (int i = 0; i < points.size(); i++) {
        int newX = newHeight - points.get(i).y - 1;
        int newY = points.get(i).x;
        points.get(i).x = newX;
        points.get(i).y = newY;
      }
      width = newWidth;
      height = newHeight;
    }

    void flip () {
      for (int i = 0; i < points.size(); i++) {
        points.get(i).y = width - points.get(i).y - 1;
      }
    }

    public String toString () {
      StringBuilder ret = new StringBuilder();
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          boolean found = false;
          for (Point p : points) {
            if (p.x == i && p.y == j)
              found = true;
          }
          ret.append(found ? "1" : ".");
        }
        ret.append("\n");
      }
      return ret.toString();
    }
  }

  static class Point implements Comparable<Point> {
    int x, y;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo (Point p) {
      if (x == p.x)
        return y - p.y;
      return x - p.x;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return p.x == x && p.y == y;
      }
      return false;
    }

    @Override
    public String toString () {
      return String.format("(%d %d)", x, y);
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
