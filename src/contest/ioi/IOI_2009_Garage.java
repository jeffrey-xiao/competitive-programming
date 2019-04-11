package contest.ioi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class IOI_2009_Garage {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int[][] rate = new int[scan.nextInt()][2];// 0 is value, 1 is occ
    int[][] cars = new int[scan.nextInt()][2];// 0 is kg, 1 is place
    for (int x = 0; x < rate.length; x++)
      rate[x][0] = scan.nextInt();
    for (int x = 0; x < cars.length; x++)
      cars[x][0] = scan.nextInt();
    int total = 0;
    Queue<Integer> q = new LinkedList<Integer>();

    int counter = 0;
    int numOfSpaces = rate.length;

    while (counter < cars.length * 2) {
      int y = scan.nextInt();
      if (y > 0) {
        q.offer(y);
        counter++;

      } else {
        rate[cars[-y - 1][1]][1] = 0;
        numOfSpaces++;
        counter++;
      }
      while (!q.isEmpty() && numOfSpaces > 0) {
        int index = -1;
        y = q.poll();
        for (int z = 0; z < rate.length; z++) {
          if (rate[z][1] == 0) {
            index = z;
            rate[z][1] = -1;
            cars[y - 1][1] = index;
            numOfSpaces--;
            break;
          }
        }
        total += cars[y - 1][0] * rate[index][0];
      }
    }
    System.out.println(total);
  }
}
