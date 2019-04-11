package contest.ccc;

import java.util.Scanner;

class CCC_2011_J2 {

  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int humidityFactor = scan.nextInt();
    int time = scan.nextInt();
    boolean inTime = false;
    int timeToLand = 0;
    for (int x = 1; x <= time; x++) {
      int altitude = (int)((humidityFactor * Math.pow(x, 3)) + (2 * Math.pow(x, 2)) + x - (6 * Math.pow(x, 4)));
      if (altitude <= 0) {
        inTime = true;
        timeToLand = x;
        break;
      }
    }
    System.out.println(inTime ? "The balloon first touches ground at hour:\n" + timeToLand : "The balloon does not touch ground in the given time.");
  }
}
