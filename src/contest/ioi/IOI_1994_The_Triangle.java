package contest.ioi;

import java.util.Scanner;

public class IOI_1994_The_Triangle {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int rows = scan.nextInt();
    int[][] triangle = new int[rows][];
    for (int x = 0; x < triangle.length; x++) {
      triangle[x] = new int[x + 1];
      for (int y = 0; y < triangle[x].length; y++)
        triangle[x][y] = scan.nextInt();
    }
    for (int x = triangle.length - 2; x >= 0; x--)
      for (int y = 0; y < triangle[x].length; y++)
        triangle[x][y] += Math.max(triangle[x + 1][y], triangle[x + 1][y + 1]);
    System.out.println(triangle[0][0]);
  }
}
