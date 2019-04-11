package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2012_Tractor {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    int[][] hay = new int[1002][1002];
    int[][] min = new int[1002][1002];
    int n = readInt();
    int x = readInt();
    int y = readInt();
    for (int z = 0; z < n; z++) {
      int a = readInt();
      int b = readInt();
      hay[a][b] = 1;
    }
    Queue<Point> zeros = new LinkedList<Point>();
    Queue<Point> ones = new LinkedList<Point>();
    zeros.offer(new Point(x, y));
    min[x][y] = 1;
    Point curr = null;
    while (!zeros.isEmpty() || !ones.isEmpty()) {
      curr = null;
      if (zeros.isEmpty())
        while (!ones.isEmpty())
          zeros.offer(ones.poll());
      curr = zeros.poll();
      for (int z = 0; z < 4; z++) {
        int nx = curr.x + movex[z];
        int ny = curr.y + movey[z];
        if (nx < 0 || ny < 0 || nx >= 1002 || ny >= 1002 || (min[nx][ny] != 0 && hay[nx][ny] + min[curr.x][curr.y] >= min[nx][ny]))
          continue;
        min[nx][ny] = min[curr.x][curr.y] + hay[nx][ny];
        if (hay[nx][ny] == 0)
          zeros.offer(new Point(nx, ny));
        else
          ones.offer(new Point(nx, ny));
      }
    }
    System.out.println(min[0][0] - 1);
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

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
