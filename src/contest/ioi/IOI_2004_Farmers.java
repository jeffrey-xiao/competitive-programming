package contest.ioi;

import java.util.Arrays;
import java.util.Scanner;

public class IOI_2004_Farmers {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int trees = scan.nextInt();
    int field = scan.nextInt();
    int strip = scan.nextInt();
    int sum = 0;
    boolean[] check = new boolean[160000];
    int[] fields = new int[field];
    int[] strips = new int[strip];
    for (int x = 0; x < fields.length; x++) {
      fields[x] = scan.nextInt();
      sum += fields[x];
    }
    for (int x = 0; x < strips.length; x++) {
      strips[x] = scan.nextInt();
    }
    if (sum < trees) {
      Arrays.sort(strips);
      int x = 0;
      for (; x < strips.length && sum < trees; x++) {
        sum += strips[strips.length - x - 1];
      }
      sum = trees - x;
    } else if (sum > trees) {
      check[0] = true;
      Arrays.sort(fields);
      for (int y = 0, x = y; x < fields.length && !check[trees]; x++) {
        for (int z = y; z >= 0; z--) {
          if (check[z])
            check[z + fields[x]] = true;
        }
        if (y < trees)
          y += fields[x];
      }
      sum = trees - (check[trees] ? 0 : 1);
    }
    System.out.println(sum);
  }
}
