package contest.misc;

import java.util.HashSet;
import java.util.Scanner;

public class Factorial_Sum_List {
  static Scanner scan = new Scanner(System.in);
  static int[] f = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

  public static void main(String[] args) {
    String s = scan.next();
    while (!s.equals("0")) {
      int size = 0;
      HashSet<Integer> hs = new HashSet<Integer>();
      hs.add(Integer.parseInt(s));
      while (size != hs.size()) {

        int sum = 0;
        for (int x = 0; x < s.length(); x++) {
          sum += f[s.charAt(x) - 48];
        }
        size = hs.size();
        hs.add(sum);
        s = "" + sum;
      }
      System.out.println(hs.size() + 1);
      s = scan.next();
    }
  }
}
