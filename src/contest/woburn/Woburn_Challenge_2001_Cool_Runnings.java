package contest.woburn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Woburn_Challenge_2001_Cool_Runnings {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] movex = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] movey = {0, 1, 1, 1, 0, -1, -1, -1};

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int n = readInt();
      int[][] grid = new int[n][n];
      int[][][] max = new int[n][n][8];
      for (int x = 0; x < n; x++)
        for (int y = 0; y < n; y++) {
          grid[x][y] = readInt();
          for (int z = 0; z < 8; z++)
            max[x][y][z] = Integer.MIN_VALUE;
        }
      PriorityQueue<State> pq = new PriorityQueue<State>();
      for (int z = 0; z < 8; z++)
        pq.offer(new State(0, 0, 0, z));
      while (!pq.isEmpty()) {
        State curr = pq.poll();
        for (int z = 0; z < 8; z++) {
          int nx = curr.x + movex[z];
          int ny = curr.y + movey[z];
          if (nx < 0 || ny < 0 || nx >= n || ny >= n)
            continue;
          int np = (z == curr.dir ? curr.p : 0) + (grid[curr.x][curr.y] - grid[nx][ny]);
          if (np < 0 || max[nx][ny][z] >= np)
            continue;
          max[nx][ny][z] = np;
          pq.offer(new State(nx, ny, np, z));
        }
      }
      int maxV = Integer.MIN_VALUE;
      for (int z = 0; z < 4; z++) {
        maxV = Math.max(maxV, max[n - 1][n - 1][z]);
      }
      System.out.println(maxV == Integer.MIN_VALUE ? "Trapped!" : maxV);
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

  static class State implements Comparable<State> {
    int x, y, p, dir;

    State(int x, int y, int p, int dir) {
      this.x = x;
      this.y = y;
      this.p = p;
      this.dir = dir;
    }

    @Override
    public int compareTo(State o) {
      return p - o.p;
    }
  }
}
