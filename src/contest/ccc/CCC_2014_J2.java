package contest.ccc;

import java.util.Scanner;

public class CCC_2014_J2 {
  public static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int v = scan.nextInt();
    scan.nextLine();
    String s = scan.nextLine();
    int counter = 0;
    for (int x = 0; x < v; x++) {
      if (s.charAt(x) == 'A')
        counter++;
      else
        counter--;
    }
    if (counter == 0)
      System.out.println("Tie");
    else if (counter > 0)
      System.out.println("A");
    else
      System.out.println("B");
  }
}