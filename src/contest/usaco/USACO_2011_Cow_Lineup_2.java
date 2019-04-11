package contest.usaco;

import java.util.HashSet;
import java.util.Scanner;

public class USACO_2011_Cow_Lineup_2 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int numOfCows = scan.nextInt();
    int maxBreed = scan.nextInt();
    int[] cows = new int[numOfCows];
    for (int x = 0; x < cows.length; x++)
      cows[x] = scan.nextInt();
    int max = 1;
    for (int x = 0; x < cows.length; x++) {
      int curr = 1;
      HashSet<Integer> s = new HashSet<Integer>();
      for (int y = x + 1; y < cows.length; y++) {
        if (cows[y] != cows[x]) {
          s.add(cows[y]);
        }
        if (s.size() > maxBreed)
          break;
        if (cows[y] == cows[x])
          curr++;
        if (curr > max)
          max = curr;
      }
    }
    System.out.println(max);
  }
}