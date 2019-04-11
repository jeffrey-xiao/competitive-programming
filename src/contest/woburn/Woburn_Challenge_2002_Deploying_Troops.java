package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_2002_Deploying_Troops {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[] cows = new int[scan.nextInt()];
    for (int x = 0; x < cows.length; x++)
      cows[x] = scan.nextInt();
    for (int z = scan.nextInt(); z > 0; z--) {
      int counter = 0;
      int targetSum = scan.nextInt();
      for (int x = 0; x < cows.length; x++) {
        int sum = 0;
        for (int y = x; y < cows.length; y++) {
          sum += cows[y];
          if (sum == targetSum)
            counter++;
          else if (sum > targetSum)
            break;
        }
      }
      System.out.print(counter + " ");
    }
  }
}
