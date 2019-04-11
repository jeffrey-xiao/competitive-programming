package contest.misc;

import java.util.Scanner;

public class Shortest_Path_Adjacency {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int size = scan.nextInt();
    int[][] table = new int[size][size];
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        table[x][y] = scan.nextInt();
      }
    }
    for (int z = 0; z < size; z++) {
      for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
          if (x != z && y != 0 && table[z][y] != 0 && table[x][z] != 0) {
            table[x][y] = Math.min(table[x][y] == 0 ? Integer.MAX_VALUE : table[x][y], table[z][y] + table[x][z]);
          }
        }
      }
    }
    System.out.println(table[0][size - 1]);
  }
}
