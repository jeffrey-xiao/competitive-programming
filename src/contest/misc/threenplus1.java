package contest.misc;

import java.util.Scanner;

public class threenplus1 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int n = scan.nextInt();
    int num = 0;
    while (n != 1) {
      if (n % 2 == 1) {
        n = n * 3 + 1;
        num++;
      } else {
        n /= 2;
        num++;
      }
    }
    System.out.println(num);
  }
}
