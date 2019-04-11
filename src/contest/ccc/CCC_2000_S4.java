package contest.ccc;

import java.util.Scanner;

public class CCC_2000_S4 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int dist = scan.nextInt();
    int numOfClubs = scan.nextInt();
    int[][] table = new int[dist + 1][2];
    for (int x = 1; x < table.length; x++)
      table[x][0] = -5000;
    int[] clubs = new int[numOfClubs + 1];
    for (int x = 1; x < clubs.length; x++)
      clubs[x] = scan.nextInt();

    for (int x = 1; x <= numOfClubs; x++) {
      for (int y = dist; y >= clubs[x]; y--) {
        for (int z = 1; z <= y / clubs[x]; z++) {
          int b = table[y][0];
          int a = clubs[x] * z + table[y - clubs[x] * z][0];
          if (a > b) {
            table[y][1] = table[y - clubs[x] * z][1] + z;
            table[y][0] = a;
          } else if (a == b)
            table[y][1] = min(table[y - clubs[x] * z][1] + z, table[y][1]);
          if (table[y][0] < 0) {
            table[y][0] = -5000;
            table[y][1] = 0;
          }
        }
      }
    }

    System.out.printf(table[dist][0] < 0 ? "Roberta acknowledges defeat." : "Roberta wins in %d strokes.", table[table.length - 1][1]);
  }

  public static int min(int a, int b) {
    return a < b ? a : b;
  }

  public static int max(int a, int b) {
    return a < b ? a : b;
  }
}
