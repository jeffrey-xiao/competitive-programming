package contest.ccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CCC_2014_J5_S2 {

  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scan.nextInt();
    scan.nextLine();
    ArrayList<String> students1 = new ArrayList<String>(Arrays.asList(scan.nextLine().split(" ")));
    ArrayList<String> students2 = new ArrayList<String>(Arrays.asList(scan.nextLine().split(" ")));

    main:
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        if (y == x)
          continue;
        if (students1.get(x).equals(students2.get(y)) && students2.get(x).equals(students1.get(y)))
          continue main;
      }
      System.out.println("bad");
      System.exit(0);
    }
    System.out.println("good");
  }
}