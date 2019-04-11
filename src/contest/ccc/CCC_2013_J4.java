package contest.ccc;

import java.util.Arrays;
import java.util.Scanner;

public class CCC_2013_J4 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int time = scan.nextInt();
    int[] chores = new int[scan.nextInt()];
    for (int x = 0; x < chores.length; x++)
      chores[x] = scan.nextInt();
    Arrays.sort(chores);
    int count = 0;
    for (int x = 0; x < chores.length; x++) {
      if (time >= chores[x]) {
        time -= chores[x];
        count++;
      } else
        break;
    }
    System.out.println(count);
  }
}
