package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2013_Island_Travels_2 {

  static final char SHIFT = 'a';
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int count;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};
  static char[][] grid;
  static int r, c;
  static int[][] adj;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    r = readInt();
    c = readInt();
    grid = new char[r][];
    for (int x = 0; x < r; x++)
      grid[x] = readLine().toCharArray();
    for (int x = 0; x < r; x++)
      for (int y = 0; y < c; y++)
        if (grid[x][y] == 'X') {
          fill(x, y, count + SHIFT);
          count++;
        }
    adj = new int[count][count];
    for (int x = 0; x < count; x++)
      for (int y = 0; y < count; y++)
        adj[x][y] = Integer.MAX_VALUE;
    for (int x = 0; x < count; x++)
      bfs(x);

    int size = (int)(Math.pow(2, count));
    dp = new int[count][size];
    for (int y = 0; y < count; y++)
      for (int x = 0; x < size; x++)
        dp[y][x] = -1;
    boolean[] startState = new boolean[count];
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < count; x++)
      min = Math.min(compute(Arrays.copyOf(startState, startState.length), x), min);
    System.out.println(min);
  }

  private static int compute(boolean[] state, int end) {
    state[end] = true;
    int index = toIndex(state);

    if (index == Math.pow(2, count) - 1)
      return 0;
    if (dp[end][index] != -1)
      return dp[end][index];
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < state.length; x++) {
      if (!state[x]) {
        min = Math.min(min, adj[x][end] + compute(Arrays.copyOf(state, state.length), x));
      }
    }
    state[end] = false;
    dp[end][index] = min;
    return min;
  }

  private static int toIndex(boolean[] state) {
    int total = 0;
    int b = 1;
    for (int x = 0; x < state.length; x++, b *= 2)
      if (state[x])
        total += b;
    return total;
  }

  private static void bfs(int i) {
    Queue<Point> moves = new LinkedList<Point>();
    int[][] min = new int[r][c];
    for (int x = 0; x < r; x++)
      for (int y = 0; y < c; y++) {
        if (grid[x][y] == SHIFT + i) {
          moves.offer(new Point(x, y, 0));
          min[x][y] = 0;
        }
        min[x][y] = Integer.MAX_VALUE;
      }
    while (!moves.isEmpty()) {
      Point curr = moves.poll();
      char id = grid[curr.x][curr.y];
      if (id != '.' && id != 'S' && adj[i][id - SHIFT] > curr.moves) {
        adj[i][id - SHIFT] = curr.moves;
        adj[id - SHIFT][i] = curr.moves;
      }
      for (int z = 0; z < 4; z++) {
        int nx = curr.x + movex[z];
        int ny = curr.y + movey[z];
        if (nx < 0 || ny < 0 || nx >= r || ny >= c || grid[nx][ny] == '.')
          continue;
        if (grid[nx][ny] == 'S') {
          if (min[nx][ny] > curr.moves + 1) {
            min[nx][ny] = curr.moves + 1;
            moves.offer(new Point(nx, ny, curr.moves + 1));
          }
        } else {
          if (min[nx][ny] > curr.moves) {
            min[nx][ny] = curr.moves;
            moves.offer(new Point(nx, ny, curr.moves));
          }
        }
      }
    }
  }

  private static void fill(int x, int y, int id) {
    grid[x][y] = (char)id;
    for (int z = 0; z < 4; z++) {
      int nx = x + movex[z];
      int ny = y + movey[z];
      if (nx < 0 || ny < 0 || nx >= r || ny >= c || grid[nx][ny] != 'X')
        continue;
      fill(nx, ny, id);
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

  static class Point {
    int x, y, moves;

    Point(int x, int y, int moves) {
      this.x = x;
      this.y = y;
      this.moves = moves;
    }
  }
}
