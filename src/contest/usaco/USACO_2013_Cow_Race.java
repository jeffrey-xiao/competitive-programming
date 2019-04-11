package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2013_Cow_Race {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    int[] times = new int[1000005];
    int currTime = 1;
    for (int x = 0; x < n; x++) {
      int speed = readInt();
      int time = readInt();
      times[currTime] += speed;
      times[currTime + time] += -speed;
      currTime += time;
    }
    currTime = 1;
    for (int x = 0; x < m; x++) {
      int speed = readInt();
      int time = readInt();
      times[currTime] += -speed;
      times[currTime + time] += speed;
      currTime += time;
    }
    int curr = 0;
    byte state = 0;
    int count = 0;
    int distance = 0;
    for (int x = 1; x < currTime + 4; x++) {

      curr += times[x];
      times[x] = curr;
      distance += times[x];
      if (distance == 0)
        continue;
      if (state == 0) {

        count++;
        state = (byte) (distance < 0 ? -1 : 1);
      } else if (state == 1 && distance < 0) {
        count++;
        state = -1;
      } else if (state == -1 && distance > 0) {
        count++;
        state = 1;
      }
    }
    System.out.println((count - 1) == -1 ? 0 : count - 1);
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
