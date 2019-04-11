package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Christmas_Tree_Cutting {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int sx = 0, sy = 0, ex = 0, ey = 0;
    int[][] grid = new int[r][c];
    boolean[][] v = new boolean[r][c];
    int[][] min = new int[r][c];
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        min[x][y] = Integer.MAX_VALUE;
        char ch = readCharacter();
        if (ch == '.')
          ch = '0';
        if (ch == 'X') {
          sx = x;
          sy = y;
          ch = '0';
        }
        grid[x][y] = ch - '0';
      }
    }
    Queue<State> q = new LinkedList<State>();
    q.add(new State(sx, sy));
    int largest = 0;
    v[sx][sy] = true;
    while (!q.isEmpty()) {
      State curr = q.poll();
      if (grid[curr.x][curr.y] > largest) {
        largest = grid[curr.x][curr.y];
        ex = curr.x;
        ey = curr.y;
      }
      for (int z = 0; z < 4; z++) {
        int x = curr.x + movex[z];
        int y = curr.y + movey[z];
        if (x < 0 || y < 0 || x >= r || y >= c || v[x][y])
          continue;
        v[x][y] = true;
        q.offer(new State(x, y));
      }
    }
    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(sx, sy, 0, 0));
    min[sx][sy] = 0;
    while (!pq.isEmpty()) {
      State curr = pq.poll();
      if (curr.x == ex && curr.y == ey) {
        System.out.println(curr.trees - 1);
        return;
      }
      for (int z = 0; z < 4; z++) {
        int x = curr.x + movex[z];
        int y = curr.y + movey[z];
        if (x < 0 || y < 0 || x >= r || y >= c || min[x][y] <= curr.moves + grid[x][y])
          continue;
        min[x][y] = curr.moves + grid[x][y];
        pq.offer(new State(x, y, min[x][y], curr.trees + (grid[x][y] != 0 ? 1 : 0)));
      }
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
    int x, y, moves, trees;

    State(int x, int y) {
      this.x = x;
      this.y = y;
    }

    State(int x, int y, int moves, int trees) {
      this.x = x;
      this.y = y;
      this.moves = moves;
      this.trees = trees;
    }

    @Override
    public int compareTo(State o) {
      return moves - o.moves;
    }
  }
}
