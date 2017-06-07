package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_S1 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    StringBuilder s = new StringBuilder(scan.nextLine());
    String find = scan.nextLine();
    int index = s.reverse().indexOf(find);
    if (index == -1)
      System.out.println(-1);
    else
      System.out.println(s.length() - index);
  }
}
