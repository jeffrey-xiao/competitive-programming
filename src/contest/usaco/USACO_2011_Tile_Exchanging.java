package contest.usaco;

import java.util.Scanner;

public class USACO_2011_Tile_Exchanging {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int numOfTiles = scan.nextInt();
    int area = scan.nextInt();
    int[] tiles = new int[numOfTiles + 1];
    for (int x = 1; x < tiles.length; x++)
      tiles[x] = scan.nextInt();

    int[][] table = new int[numOfTiles + 1][area + 1];
    for (int x = 1; x < table[0].length; x++)
      table[0][x] = 50000;
    for (int x = 1; x < table.length; x++) {
      for (int y = 0; y < table[0].length; y++) {
        table[x][y] = 50000;
        for (int z = 1; z * z <= y; z++) {
          int cost = Math.abs(tiles[x] - z);
          table[x][y] = Math.min(table[x][y], table[x - 1][y - (z * z)] + cost * cost);
        }
      }
    }
    System.out.println(table[table.length - 1][table[0].length - 1] == 50000 ? -1 : table[table.length - 1][table[0].length - 1]);
  }
}
