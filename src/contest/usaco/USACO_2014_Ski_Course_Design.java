package contest.usaco;

import java.util.Scanner;

public class USACO_2014_Ski_Course_Design {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {

    int hills[] = new int[101];
    int max = -1;
    int min = 101;
    for (int x = scan.nextInt(); x > 0; x--) {
      int a = scan.nextInt();
      hills[a]++;
      if (a < min)
        min = a;
      if (a > max)
        max = a;
    }
    if (max - min <= 17)
      System.out.println(0);

    else {
      int minCost = Integer.MAX_VALUE;
      for (int lowerBound = max - 17; lowerBound >= min; max--, lowerBound--) {
        int cost = 0;
        for (int x = 0; x < lowerBound; x++)
          if (x < lowerBound)
            cost += (lowerBound - (x)) * (lowerBound - (x)) * hills[x];
        for (int x = 100; x > max; x--)
          if (x > max)
            cost += (x - max) * (x - max) * hills[x];

        if (cost < minCost)
          minCost = cost;
      }
      System.out.println(minCost);
    }

  }
}
