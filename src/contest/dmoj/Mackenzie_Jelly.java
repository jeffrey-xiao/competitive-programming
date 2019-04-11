package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Mackenzie_Jelly {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] movex = {-1, 1, 0, 0, 0, 0};
  static int[] movey = {0, 0, -1, 1, 0, 0};
  static int[] movez = {0, 0, 0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int X = readInt();
    int Y = readInt();
    int Z = readInt();

    char[][][] grid = new char[Z][Y][X];
    int sx = 0, sy = 0, sz = 0;
    for (int i = 0; i < Z; i++)
      for (int j = 0; j < Y; j++) {
        String in = readLine();
        grid[i][j] = in.toCharArray();
        if (in.indexOf('J') != -1) {
          sz = i;
          sy = j;
          sx = in.indexOf('J');
        }
      }
    int[][][] cost = new int[Z][Y][X];
    for (int i = 0; i < Z; i++)
      for (int j = 0; j < Y; j++)
        for (int k = 0; k < X; k++)
          cost[i][j][k] = 1 << 30;
    cost[sz][sy][sx] = 0;

    PriorityQueue<Point> pq = new PriorityQueue<Point>();
    pq.offer(new Point(sx, sy, sz, 0));

    int ans = 1 << 30;

    while (!pq.isEmpty()) {
      Point curr = pq.poll();

      if (curr.z == 0 || curr.z == Z - 1 || curr.y == 0 || curr.y == Y - 1 || curr.x == 0 || curr.x == X - 1)
        ans = Math.min(ans, cost[curr.z][curr.y][curr.x]);

      for (int i = 0; i < 6; i++) {
        int nx = curr.x + movex[i];
        int ny = curr.y + movey[i];
        int nz = curr.z + movez[i];

        if (nx < 0 || ny < 0 || nz < 0 || nx >= X || ny >= Y || nz >= Z || cost[nz][ny][nx] <= curr.cost + grid[nz][ny][nx] - '0')
          continue;
        cost[nz][ny][nx] = curr.cost + grid[nz][ny][nx] - '0';
        pq.offer(new Point(nx, ny, nz, cost[nz][ny][nx]));
      }
    }
    out.println(ans);
    out.close();
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

  static class Point implements Comparable<Point> {
    int x, y, z, cost;

    Point(int x, int y, int z, int cost) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.cost = cost;
    }

    @Override
    public int compareTo(Point o) {
      return cost - o.cost;
    }
  }
}
