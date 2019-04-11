package contest.ccc;

import java.util.ArrayList;
import java.util.Scanner;

public class CCC_2005_J3 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<String[]> locations = new ArrayList<String[]>();
    String d = "";
    String loc = "";
    while (!loc.equals("SCHOOL")) {
      d = scan.next();
      loc = scan.next();
      locations.add(new String[]{d, loc});
    }
    for (int x = locations.size() - 1; x >= 0; x--)
      if (!locations.get(x)[1].equals("SCHOOL")) {
        System.out.printf("Turn %s %s\n", locations.get(x + 1)[0].equals("R") ? "LEFT" : "RIGHT", "onto " + locations.get(x)[1] + " street.");
      }
    System.out.println("Turn " + (locations.get(0)[0].equals("R") ? "LEFT" : "RIGHT") + " into your HOME.");
  }
}
