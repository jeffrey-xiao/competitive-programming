package contest.ccc;

import java.util.Scanner;

public class CCC_1996_Stage_2_Train_Swapping {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int t = scan.nextInt(); t > 0; t--) {
      int num = scan.nextInt();
      int[] nums = new int[num];
      for (int x = 0; x < num; x++)
        nums[x] = scan.nextInt();
      int passes = 0;
      for (int x = 0; x < nums.length; x++) {
        for (int y = x + 1; y < nums.length; y++) {
          if (nums[x] > nums[y]) {
            int temp = nums[y];
            nums[y] = nums[x];
            nums[x] = temp;
            passes++;
          }
        }
      }
      System.out.printf("Optimal train swapping takes %d swap(s).\n", passes);
    }
  }
}
