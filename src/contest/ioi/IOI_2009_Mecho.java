package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_2009_Mecho {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int n = 0;
  static byte[] movex = {-1, 1, 0, 0};
  static byte[] movey = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    n = readInt();
    int speed = readInt();
    char[][] grid = new char[n][n];
    Queue<int[]> moves = new LinkedList<int[]>(); // x,y,moves
    int startx = 0;
    int starty = 0;
    for (int x = 0; x < n; x++) {
      String s = next();
      for (int y = 0; y < n; y++) {
        grid[x][y] = s.charAt(y);
        if (s.charAt(y) == 'H') {
          moves.offer(new int[] {x, y, speed});
        } else if (grid[x][y] == 'M') {
          startx = x;
          starty = y;
        }
      }
    }
    int[][] steps = new int[n][n];
    boolean[][] visited = new boolean[n][n];
    while (!moves.isEmpty()) {
      int[] curr = moves.poll();
      for (int z = 0; z < 4; z++) {
        int newX = curr[0] + movex[z];
        int newY = curr[1] + movey[z];
        if (newX < 0 || newY < 0 || newX >= n || newY >= n || visited[newX][newY] || grid[newX][newY] == 'T' || grid[newX][newY] == 'D')
          continue;
        visited[newX][newY] = true;
        steps[newX][newY] = curr[2];
        moves.add(new int[] {newX, newY, curr[2] + speed});
      }
    }
    int min = -1;
    int max = n * n;
    while (max - min > 1) {
      int mid = (min + max) >> 1;
      if (bfs(grid, steps, mid * speed, startx, starty)) {
        min = mid;
      } else {
        max = mid;
      }
    }
    System.out.println(min);
  }

  private static boolean bfs(char[][] grid, int[][] steps, int mid, int x, int y) {
    boolean[][] visited = new boolean[n][n];
    Queue<int[]> moves = new LinkedList<int[]>();
    moves.add(new int[] {x, y, mid});
    if (mid >= steps[x][y])
      return false;
    while (!moves.isEmpty()) {
      int[] curr = moves.poll();
      if (grid[curr[0]][curr[1]] == 'D')
        return true;
      for (int z = 0; z < 4; z++) {
        int newX = curr[0] + movex[z];
        int newY = curr[1] + movey[z];
        if (newX < 0 || newY < 0 || newX >= n || newY >= n || visited[newX][newY] || grid[newX][newY] == 'T' || (curr[2] + 1 >= steps[newX][newY] && grid[newX][newY] != 'D'))
          continue;
        visited[newX][newY] = true;
        moves.add(new int[] {newX, newY, curr[2] + 1});
      }
    }
    return false;
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
}
