package contest.ccc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MockCCC_2014_S3 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int rows = scan.nextInt();
    int columns = scan.nextInt();
    int[][] sheet = new int[rows][columns];
    for (int x = 0; x < rows; x++)
      for (int y = 0; y < columns; y++)
        sheet[x][y] = scan.nextInt();
    for (int z = scan.nextInt(); z > 0; z--) {
      final int column = scan.nextInt() - 1;
      Arrays.sort(sheet, new Comparator<int[]>() {
        @Override
        public int compare(int[] arg0, int[] arg1) {
          if (arg0[column] == arg1[column])
            return 0;
          return arg0[column] < arg1[column] ? -1 : 1;
        }
      });
    }
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++)
        System.out.print(sheet[y][x] + " ");
      System.out.println();
    }
    System.out.println();
  }
}
