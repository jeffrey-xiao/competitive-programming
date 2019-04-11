package contest.usaco;

import java.util.Scanner;

public class USACO_2012_Cows_In_A_Row {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scan.nextInt();
    int[] cows = new int[n];
    int max = -1;
    for (int x = 0; x < n; x++)
      cows[x] = scan.nextInt();
    for (int x = 0; x < n; x++) {
      int numOfBreeds = 0;
      int count = 1;
      int id2 = -1;
      for (int y = x + 1; y < n && numOfBreeds < 2; y++) {
        if (cows[y] != id2 && cows[y] != cows[x]) {
          numOfBreeds++;
          id2 = cows[y];
        } else if (cows[y] == cows[x])
          count++;
      }
      max = Math.max(count, max);
    }
    System.out.println(max);
  }
}
