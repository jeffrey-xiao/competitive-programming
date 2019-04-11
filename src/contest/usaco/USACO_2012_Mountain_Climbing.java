package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2012_Mountain_Climbing {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>(n, new Comparator<int[]>() {
      @Override
      public int compare(int[] arg0, int[] arg1) {
        if (arg0[0] < arg0[1]) {
          if (arg1[0] < arg1[1])
            return arg0[0] - arg1[0];
          else
            return -1;
        } else {
          if (arg1[0] > arg1[1])
            return arg1[1] - arg0[1];
          else
            return 1;
        }
      }
    });
    for (int x = 0; x < n; x++) {
      pq.offer(new int[] {readInt(), readInt()});
    }
    Queue<int[]> d = new LinkedList<int[]>();
    int totalTimeUp = 0;
    while (!pq.isEmpty()) {

      int[] curr = pq.poll();
      totalTimeUp += curr[0];
      d.offer(new int[] {curr[1], totalTimeUp});
    }
    int currTimeDown = 0;
    while (!d.isEmpty()) {
      int[] curr = d.poll();
      if (curr[1] > currTimeDown)
        currTimeDown = curr[1] + curr[0];
      else
        currTimeDown += curr[0];
    }
    System.out.println(currTimeDown);
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
