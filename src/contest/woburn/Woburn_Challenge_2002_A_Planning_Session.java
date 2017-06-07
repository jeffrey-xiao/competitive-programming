package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_2002_A_Planning_Session {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    for (int t = scan.nextInt(); t > 0; t--) {
      String[] s = scan.next().split(":");
      int mins = (Integer.parseInt(s[0]) - 1) * 60 + Integer.parseInt(s[1]);
      int occ = scan.nextInt();
      if (occ > 23) {
        System.out.println("Infinite");
        continue;
      }
      int cnt = mins;
      int newMins = mins + 1;
      cnt++;
      if (newMins > 719)
        newMins -= 720;
      int sum = (newMins / 60 + 1) / 10 + (newMins / 60 + 1) % 10 + newMins % 60 / 10 + newMins % 60 % 10;
      while (sum != occ) {
        newMins++;
        cnt++;
        if (newMins > 719)
          newMins -= 720;
        sum = (newMins / 60 + 1) / 10 + (newMins / 60 + 1) % 10 + newMins % 60 / 10 + newMins % 60 % 10;
      }
      System.out.println(cnt - mins);
    }
  }
}
