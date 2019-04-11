package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_J2 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    String s = "";
    int length = scan.nextInt();
    for (int x = 0; x <= length; x++)
      s += (length - x) % 2 == 0 ? new StringBuffer(scan.nextLine().trim()).reverse() : scan.nextLine().trim();
    System.out.println(new StringBuffer(s).reverse().toString());
  }
}
