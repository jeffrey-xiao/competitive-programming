package contest.ccc;

import java.util.Scanner;

public class CCC_2004_J2 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int start = scan.nextInt();
    int end = scan.nextInt();
    for (int x = start; x <= end; x++) {
      if ((start - x) % 60 == 0)
        System.out.println("All positions change in year " + x);
    }
  }
}
