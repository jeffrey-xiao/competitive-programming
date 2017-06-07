package contest.ccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CCC_2007_J5 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int a = scan.nextInt();
    int b = scan.nextInt();
    int length = scan.nextInt();
    List<Integer> motels = new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 990, 1010, 1970, 2030, 2940, 3060, 3930, 4060, 4970, 5030, 5990, 6010, 7000}));
    int[] dp = new int[motels.size() + length];
    for (int x = 0; x < length; x++)
      motels.add(scan.nextInt());
    dp[0] = 1;
    Collections.sort(motels);
    for (int x = 0; x < motels.size(); x++)
      for (int y = x + 1; y < motels.size() && b >= motels.get(y) - motels.get(x); y++)
        if (a <= motels.get(y) - motels.get(x))
          dp[y] += dp[x];
    System.out.println(dp[dp.length - 1]);
  }
}
