package contest.ccc;

import java.util.ArrayList;
import java.util.Scanner;

public class CCC_2000_J4_S2 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    ArrayList<Float> a = new ArrayList<Float>();
    for (int x = scan.nextInt(); x > 0; x--)
      a.add((float)scan.nextInt());
    int x = scan.nextInt();
    while (x != 77) {
      if (x == 99) {
        int index = scan.nextInt() - 1;
        int percent = scan.nextInt();
        a.add(index + 1, (float)(a.get(index) * ((100.0 - percent)) / 100.0));
        a.set(index, (float)(a.get(index) * (percent / 100.0)));
      } else {
        int index = scan.nextInt() - 1;
        a.set(index, a.get(index) + a.get(index + 1));
        a.remove(index + 1);
      }
      x = scan.nextInt();
    }
    for (x = 0; x < a.size(); x++) {
      System.out.print(Math.round(a.get(x)) + " ");
    }
  }
}
