package contest.usaco;

import java.util.PriorityQueue;
import java.util.Scanner;

public class USACO_2013_Bessie_Slows_Down {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    PriorityQueue<Integer> d = new PriorityQueue<Integer>();
    PriorityQueue<Integer> t = new PriorityQueue<Integer>();
    int speed = 1;
    for (int x = scan.nextInt(); x > 0; x--) {
      String s = scan.next();
      if (s.equals("T"))
        t.offer(scan.nextInt());
      else
        d.offer(scan.nextInt());
    }
    double currentD = 0;
    double currentT = 0;
    while (!d.isEmpty() || !t.isEmpty()) {
      boolean slowTime = false;
      if (d.isEmpty())
        slowTime = true;
      else if (!d.isEmpty() && !t.isEmpty()) {
        if ((d.peek() - currentD) / (1.0 / speed) > t.peek() - currentT)
          slowTime = true;
        else
          slowTime = false;
      }
      if (slowTime) {
        int time = t.poll();
        currentD += (time - currentT) * (1.0 / speed);
        currentT = time;
        speed++;
      } else {
        int distance = d.poll();
        currentT += (distance - currentD) / (1.0 / speed);
        currentD = distance;
        speed++;
      }
    }
    if (currentD < 1000)
      currentT += (1000 - currentD) / (1.0 / speed);
    System.out.println((int) Math.round(currentT));
  }

}
