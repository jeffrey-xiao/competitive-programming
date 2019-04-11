package contest.misc;

import java.util.ArrayList;
import java.util.Scanner;

public class Shortest_Path {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scan.nextInt();
    ArrayList<ArrayList<int[]>> adjlist = new ArrayList<ArrayList<int[]>>();
    for (int x = 0; x < n; x++)
      adjlist.add(new ArrayList<int[]>());
    // 0-> source, 1-> destination, 2-> distance
    int edges = scan.nextInt();
    for (int x = 0; x < edges; x++)
      adjlist.get(scan.nextInt() - 1).add(new int[] {scan.nextInt() - 1, scan.nextInt()});

    int[] dist = new int[n];
    int[] pred = new int[n];

    for (int x = 1; x < dist.length; x++) {
      dist[x] = 100000;
      pred[x] = -1;
    }
    dist[0] = 0;
    for (int z = 0; z < dist.length - 1; z++) {
      for (int x = 0; x < dist.length; x++) {
        for (int y = 0; y < adjlist.get(x).size(); y++) {
          if (dist[x] + adjlist.get(x).get(y)[1] < dist[adjlist.get(x).get(y)[0]]) {
            dist[adjlist.get(x).get(y)[0]] = dist[x] + adjlist.get(x).get(y)[1];
          }
        }
      }
    }
    System.out.println(dist[n - 1]);
  }
}
