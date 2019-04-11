package contest.coci;

import java.util.Scanner;

public class COCI_2008_PET {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int max = 0;
    int index = 0;
    for (int x = 1; x <= 5; x++) {
      int sum = scan.nextInt() + scan.nextInt() + scan.nextInt() + scan.nextInt();
      if (sum > max) {
        max = sum;
        index = x;
      }
    }
    System.out.println(index + " " + max);
  }
}
