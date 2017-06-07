package contest.usaco;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class USACO_2013_Photo {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int n = scan.nextInt();
    int[][] pairs = new int[scan.nextInt()][2];
    for (int x = 0; x < pairs.length; x++) {
      int a = scan.nextInt() - 1;
      int b = scan.nextInt() - 1;
      pairs[x][0] = Math.min(a, b);
      pairs[x][1] = Math.max(a, b);
    }
    Arrays.sort(pairs, new Comparator<int[]>() {
      @Override
      public int compare (int[] arg0, int[] arg1) {
        return arg0[1] - arg1[1];
      }
    });
    int count = 0;
    for (int x = 0; x < n;) {
      int stop = n;
      for (int y = 0; y < pairs.length; y++) {

        if (pairs[y][1] > stop)
          break;
        if (pairs[y][0] < x)
          continue;
        if (pairs[y][1] < stop) {
          stop = pairs[y][1];
        }
      }
      x = stop;
      count++;
    }
    System.out.println(count);
  }
}
