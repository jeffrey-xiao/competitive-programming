package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1996_Plentiful_Paths {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int m = scan.nextInt();
    int n = scan.nextInt();
    int[][] table = new int[m][n];
    int a;
    while ((a = scan.nextInt()) != 0)
      table[m - a][scan.nextInt() - 1] = 1;
    scan.next();
    int solution[][] = new int[m][n];
    for (int x = 0; x < m; x++) {
      for (int y = n - 1; y >= 0; y--) {
        a = x - 1 < 0 ? 0 : solution[x - 1][y];
        int b = y + 1 > n - 1 ? 0 : solution[x][y + 1];
        solution[x][y] = table[x][y] + (a > b ? a : b);
      }
    }
    System.out.println(solution[m - 1][0]);
  }
}
