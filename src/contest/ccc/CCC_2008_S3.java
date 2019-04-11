package contest.ccc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CCC_2008_S3 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int t = scan.nextInt(); t > 0; t--) {
      int r = scan.nextInt();
      int c = scan.nextInt();
      char[][] grid = new char[r][];
      boolean[][] visited = new boolean[r][c];
      for (int x = 0; x < grid.length; x++) {
        grid[x] = scan.next().toCharArray();
      }
      Queue<int[]> moves = new LinkedList<int[]>();
      // 0,1 = x,y, 2 is moves
      moves.add(new int[]{0, 0, 1});
      while (!moves.isEmpty()) {
        int[] curr = moves.poll();
        if (curr[0] < 0 || curr[0] >= r || curr[1] < 0 || curr[1] >= c || grid[curr[0]][curr[1]] == '*' || visited[curr[0]][curr[1]])
          continue;
        visited[curr[0]][curr[1]] = true;
        if (curr[0] == r - 1 && curr[1] == c - 1) {
          System.out.println(curr[2]);
          break;
        }

        if (grid[curr[0]][curr[1]] == '+') {
          moves.add(new int[]{curr[0] + 1, curr[1], curr[2] + 1});
          moves.add(new int[]{curr[0] - 1, curr[1], curr[2] + 1});
          moves.add(new int[]{curr[0], curr[1] + 1, curr[2] + 1});
          moves.add(new int[]{curr[0], curr[1] - 1, curr[2] + 1});
        } else if (grid[curr[0]][curr[1]] == '|') {
          moves.add(new int[]{curr[0] + 1, curr[1], curr[2] + 1});
          moves.add(new int[]{curr[0] - 1, curr[1], curr[2] + 1});
        } else if (grid[curr[0]][curr[1]] == '-') {
          moves.add(new int[]{curr[0], curr[1] + 1, curr[2] + 1});
          moves.add(new int[]{curr[0], curr[1] - 1, curr[2] + 1});
        }
      }
      if (!visited[r - 1][c - 1])
        System.out.println(-1);
    }
  }
}
