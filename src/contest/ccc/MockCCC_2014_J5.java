package contest.ccc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MockCCC_2014_J5 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int r = scan.nextInt();
    int c = scan.nextInt();
    int t = scan.nextInt();
    scan.nextLine();
    int r1 = 0;
    int c1 = 0;
    char[][][] states = new char[t][r][];
    boolean[][][] visited = new boolean[t][r][c];
    for (int x = 0; x < t; x++) {
      for (int y = 0; y < r; y++) {
        String s = scan.nextLine();
        states[x][y] = s.toCharArray();
        if (s.indexOf('A') != -1) {
          r1 = y;
          c1 = s.indexOf('A');
        }
      }
    }
    // 0 = moves, 1 = rows, 2 = columns, 3 = dimension
    Queue<int[]> moves = new LinkedList<int[]>();

    moves.add(new int[] {0, r1, c1, 0});
    while (!moves.isEmpty()) {
      int[] curr = moves.poll();

      if (curr[1] < 0 || curr[1] >= r || curr[2] < 0 || curr[2] >= c || states[curr[3]][curr[1]][curr[2]] == 'X' || visited[curr[3]][curr[1]][curr[2]]) {
        continue;
      }

      if (states[curr[3]][curr[1]][curr[2]] == 'B') {
        System.out.println(curr[0]);
        return;
      }

      visited[curr[3]][curr[1]][curr[2]] = true;
      moves.add(new int[] {curr[0] + 1, curr[1] + 1, curr[2], curr[3]});
      moves.add(new int[] {curr[0] + 1, curr[1] - 1, curr[2], curr[3]});
      moves.add(new int[] {curr[0] + 1, curr[1], curr[2] + 1, curr[3]});
      moves.add(new int[] {curr[0] + 1, curr[1], curr[2] - 1, curr[3]});

      for (int x = 0; x < t; x++)
        if (x != curr[3])
          moves.add(new int[] {curr[0] + 1, curr[1], curr[2], x});

    }
    System.out.println(-1);
  }
}
