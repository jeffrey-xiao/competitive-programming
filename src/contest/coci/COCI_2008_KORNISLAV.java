package contest.coci;

import java.util.Arrays;
import java.util.Scanner;

public class COCI_2008_KORNISLAV {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int[] nums = new int[4];
    for (int x = 0; x < 4; x++)
      nums[x] = scan.nextInt();
    Arrays.sort(nums);
    System.out.println(nums[0] * nums[2]);
  }
}
