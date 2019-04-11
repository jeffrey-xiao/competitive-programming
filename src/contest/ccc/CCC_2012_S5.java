package contest.ccc;

import java.util.Scanner;

public class CCC_2012_S5 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[][] lab = new int[scan.nextInt()][scan.nextInt()];
    for (int mouses = scan.nextInt(); mouses > 0; mouses--)
      lab[scan.nextInt() - 1][scan.nextInt() - 1] = -1;
    lab[0][0] = 1;
    for (int x = 0; x < lab.length; x++) {
      for (int y = 0; y < lab[0].length; y++) {

        if (lab[x][y] != -1 && !(x == 0 && y == 0)) {
          int a = x - 1 < 0 || lab[x - 1][y] == -1 ? 0 : lab[x - 1][y];
          int b = y - 1 < 0 || lab[x][y - 1] == -1 ? 0 : lab[x][y - 1];
          lab[x][y] = a + b;
        }
      }
    }
    System.out.println(lab[lab.length - 1][lab[0].length - 1]);
  }
}
