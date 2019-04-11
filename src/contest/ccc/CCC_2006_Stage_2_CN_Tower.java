package contest.ccc;

import java.util.Arrays;
import java.util.Scanner;

public class CCC_2006_Stage_2_CN_Tower {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scan.nextInt();
    scan.nextLine();
    double[] locations = new double[n];
    for (n -= 1; n >= 0; n--)
      locations[n] = Double.parseDouble(scan.nextLine().split(" ")[1]);
    Arrays.sort(locations);
    double biggest = locations[0] + 360.0 - locations[locations.length - 1];
    for (int x = 0; x < locations.length - 1; x++)
      biggest = Math.max(biggest, locations[x + 1] - locations[x]);
    double answer = (360.0d - biggest) / 360.0d * 72.0d * 60.0d - (int)((360.0d - biggest) / 360.0d * 72.0d * 60.0d) < 0.0000001d ? (int)((360.0d - biggest) / 360.0d * 72.0d * 60.0d) : Math.ceil(Math.ceil((360.0d - biggest) / 360.0d * 72.0d * 60.0d));
    System.out.println((int)answer);
  }
}
