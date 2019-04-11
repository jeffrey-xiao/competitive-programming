package contest.ccc;

import java.util.Scanner;

public class CCC_2014_J1 {

  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int a = scan.nextInt();
    int b = scan.nextInt();
    int c = scan.nextInt();
    if (a + b + c != 180)
      System.out.println("Error");
    else if (a == 60 && b == 60)
      System.out.println("Equilateral");
    else if (a == b || b == c || c == a)
      System.out.println("Isosceles");
    else
      System.out.println("Scalene");
  }
}
