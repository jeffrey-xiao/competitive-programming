package contest.usaco;

import java.util.Arrays;
import java.util.Scanner;

public class USACO_2012_Jan_Haybale_Stacking {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int n = scan.nextInt();
    int k = scan.nextInt();
    int[] input = new int[n + 1];
    for (; k > 0; k--) {
      input[scan.nextInt() - 1]++;
      input[scan.nextInt()]--;
    }
    int[] stacks = new int[n];
    int curr = 0;
    for (int x = 0; x < n; x++) {
      curr += input[x];
      stacks[x] = curr;
    }
    Arrays.sort(stacks);
    System.out.println(stacks[stacks.length / 2]);
  }
}
