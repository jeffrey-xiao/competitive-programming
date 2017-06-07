package contest.ccc;

import java.util.Scanner;

public class CCC_2007_S3 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int n = scan.nextInt();
    int[] friends = new int[9999];
    for (int x = 0; x < n; x++) {
      friends[scan.nextInt() - 1] = scan.nextInt() - 1;
    }
    int n1 = scan.nextInt() - 1;
    int n2 = scan.nextInt() - 1;
    while (n1 != -1) {
      boolean[] visited = new boolean[9999];
      int distance = -1;
      while (!visited[n1] && n1 != n2) {
        visited[n1] = true;
        distance++;
        n1 = friends[n1];
      }
      System.out.println(n1 == n2 ? "Yes " + distance : "No");
      n1 = scan.nextInt() - 1;
      n2 = scan.nextInt() - 1;
    }
  }

}
