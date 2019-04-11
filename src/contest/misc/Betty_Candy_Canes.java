package contest.misc;

import java.util.Scanner;

public class Betty_Candy_Canes {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[][] grid = {{25, 24, 23, 22, 21, 20, 19, 18, 17, -1}, {24, 23, 22, -1, 20, 19, 18, -1, 16, 17}, {25, -1, 21, 20, 19, 18, 17, 16, 15, 16}, {24, 23, 22, 21, 20, 19, 18, -1, 14, 15}, {-1, -1, -1, -1, -1, -1, -1, 12, 13, 14}, {6, 5, 6, 7, 8, 9, 10, 11, 12, 13}, {-1, 4, 5, 6, 7, 8, 9, 10, 11, 12}, {2, 3, -1, 5, 6, 7, 8, 9, -1, 11}, {1, 2, 3, 4, 5, 6, 7, -1, 9, 10}, {-1, 1, 2, 3, 4, 5, 6, 7, 8, -1}};
    int x = scan.nextInt();
    int y = scan.nextInt();
    System.out.printf("Betty will sweat %dmL to get to her candy cane", grid[10 - y][x - 1]);
  }
}
