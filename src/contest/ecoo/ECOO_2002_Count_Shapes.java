package contest.ecoo;

import java.util.Scanner;

public class ECOO_2002_Count_Shapes {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[] shapes = new int[5];
    for (int x = 0; x < 5; x++) {
      scan.nextInt();
      int height = scan.nextInt();
      scan.nextLine();
      char[][] image = new char[height][];
      for (int y = 0; y < height; y++) {
        image[y] = scan.nextLine().toCharArray();
      }
      shapes[x] = getShapes(image);
    }
    for (int x = 0; x < shapes.length; x++)
      System.out.printf("In rectangle #%d are %d shapes%n", x + 1, shapes[x]);
  }

  private static int getShapes(char[][] image) {
    int numOfShapes = 0;
    for (int z = 0; z < image.length; z++) {
      for (int y = 0; y < image[z].length; y++) {
        if (image[z][y] == 'X') {
          numOfShapes++;
          removeShapes(image, z, y, 0);
        }
      }
    }
    return numOfShapes;
  }

  private static void removeShapes(char[][] image, int x, int y, int lastMove) {
    if (x < 0 || y < 0 || x >= image.length || y >= image[x].length || image[x][y] == '.')
      return;
    image[x][y] = '.';
    removeShapes(image, x + 1, y, -1);
    removeShapes(image, x - 1, y, 1);
    removeShapes(image, x, y + 1, -2);
    removeShapes(image, x, y - 1, 2);
  }
}
