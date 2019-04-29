package contest.ccc;

import java.util.Scanner;

public class CCC_1996_A {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[][] nums = new int[scan.nextInt()][2];
    for (int x = 0; x < nums.length; x++) {
      int sum = 1;
      nums[x][1] = scan.nextInt();
      for (int y = 2; y <= Math.sqrt(nums[x][1]); y++) {
        if (nums[x][1] % y == 0 && nums[x][1] == y * y)
          sum += y;
        else if (nums[x][1] % y == 0) {
          sum += y + nums[x][1] / y;
        }
      }
      nums[x][0] = sum;
    }
    String s = "";
    for (int[] x : nums) {
      if (x[0] == x[1])
        s = "a perfect";
      else if (x[0] > x[1])
        s = "an abundant";
      else
        s = "a deficient";
      System.out.printf("%d is %s number.%n", x[1], s);
    }
  }
}
