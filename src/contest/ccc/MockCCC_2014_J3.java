package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_J3 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    double h = scan.nextDouble();
    double m = scan.nextDouble() + h;
    double s = scan.nextDouble() + m;
    String parsed = "";

    if (Math.round(h / 30) == 0)
      parsed += "12:";
    else if (h / 30 < 10)
      parsed += "0" + (int)h / 30 + ":";
    else
      parsed += (int)h / 30 + ":";
    parsed += (((m / 6.0) % 60) < 10 ? "0" + (int)((m / 6.0) % 60) : (int)(m / 6.0) % 60) + ":";
    parsed += Math.round((s / 6.0) % 60) < 10 ? "0" + (int)Math.round((s / 6.0) % 60) : (int)Math.round((s / 6.0) % 60);

    System.out.println(parsed);

  }
}
