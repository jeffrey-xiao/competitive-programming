package contest.usaco;

import java.util.Arrays;
import java.util.Scanner;

public class USACO_2013_Record_Keeping {
  public static void main (String[] args) {
    Scanner scan = new Scanner(System.in);
    int hours = scan.nextInt();
    String[] n = new String[hours];
    for (int x = hours - 1; x >= 0; x--) {
      String[] names = new String[3];
      for (int y = 0; y < names.length; y++)
        names[y] = scan.next();
      Arrays.sort(names);
      n[x] = names[0] + " " + names[1] + " " + names[2];
    }
    Arrays.sort(n);
    int curr = 1;
    int max = -1;
    for (int x = 0; x < hours - 1; x++) {
      if (n[x].equals(n[x + 1]))
        curr++;
      else
        curr = 1;
      if (curr > max)
        max = curr;
    }
    System.out.println(max);
    scan.close();
  }
}
