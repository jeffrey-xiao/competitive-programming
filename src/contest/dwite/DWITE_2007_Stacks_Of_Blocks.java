package contest.dwite;

import java.util.Scanner;

class DWITE_2007_Stacks_Of_Blocks {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int height = scan.nextInt();
    int numOfBlocks = scan.nextInt();
    int[] blocks = new int[numOfBlocks];
    for (int x = 0; x < numOfBlocks; x++)
      blocks[x] = scan.nextInt();
    int[][] table = new int[height + 1][2];
    for (int x = 1; x < table.length; x++)
      table[x][0] = -101;
    for (int x = 0; x < numOfBlocks; x++) {
      for (int y = height; y >= blocks[x]; y--) {
        int b = table[y][0];
        int a = blocks[x] + table[y - blocks[x]][0];
        if (a > b) {
          table[y][0] = a;
          table[y][1] = table[y - blocks[x]][1] + 1;
        } else if (a == b)
          table[y][1] = table[y - blocks[x]][1] + 1 < table[y][1] ? table[y - blocks[x]][1] + 1 : table[y][1];
        if (table[y][0] < 0) {
          table[y][0] = -101;
          table[y][1] = 0;
        }
      }
      for (int[] z : table) {
        System.out.print(z[0] + " ");
      }
      System.out.println();
    }
    System.out.println(table[table.length - 1][0] < 0 ? 0 : table[table.length - 1][1]);
  }
}