package contest.coci;

import java.util.Scanner;

public class COCI_2009_SPAVANAC {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int min = scan.nextInt() * 60 + scan.nextInt() - 45;
    if (min < 0)
      min += 1440;
    System.out.println(min / 60 + " " + min % 60);
  }
}
