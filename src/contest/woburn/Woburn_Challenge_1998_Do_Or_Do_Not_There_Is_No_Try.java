package contest.woburn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Woburn_Challenge_1998_Do_Or_Do_Not_There_Is_No_Try {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int r = scan.nextInt();
    int c = scan.nextInt();
    char[][] grid = new char[r][];
    int maxJump = scan.nextInt();
    for (int x = 0; x < grid.length; x++)
      grid[x] = scan.next().toCharArray();
    int y1 = scan.nextInt() - 1;
    int x1 = scan.nextInt() - 1;
    while (x1 != -2) {
      int y2 = scan.nextInt() - 1;
      int x2 = scan.nextInt() - 1;
      int[][] visited = new int[r][c];
      for (int x = 0; x < visited.length; x++)
        for (int y = 0; y < visited[0].length; y++)
          visited[x][y] = 5000005;
      Queue<int[]> q = new LinkedList<int[]>();
      q.offer(new int[] {x1, y1});
      visited[y1][x1] = 0;
      while (!q.isEmpty() && visited[y2][x2] == 5000005) {
        int[] p = q.poll();
        for (int x = -maxJump; x <= maxJump; x++) {
          for (int y = -maxJump; y <= maxJump; y++) {
            if (Math.sqrt(x * x + y * y) > maxJump)
              continue;
            if (p[0] + x >= 0 && p[0] + x < grid[0].length && p[1] + y >= 0 && p[1] + y < grid.length && visited[p[1] + y][p[0] + x] == 5000005 && grid[p[1] + y][p[0] + x] == '*') {
              visited[p[1] + y][p[0] + x] = visited[p[1]][p[0]] + 1;
              q.add(new int[] {p[0] + x, p[1] + y});
            }
          }
        }
      }
      System.out.println(visited[y2][x2] == 5000005 ? "THERE IS NO TRY" : visited[y2][x2]);
      y1 = scan.nextInt() - 1;
      x1 = scan.nextInt() - 1;
    }
  }

}
