package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TLE_Microwaves {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, T, M;
  static Event[] e;
  static int[] minIndex, maxIndex;

  public static void main (String[] args) throws Exception {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    T = readInt();

    e = new Event[M];

    for (int i = 0; i < M; i++)
      e[i] = new Event(readInt(), readInt());

    Arrays.sort(e);

    long[] microwave = new long[N];

    long ans = 1l << 60;

    for (int i = 0; i < M; i++) {
      int max = -1;
      for (int j = 0; j < N; j++) {
        if (microwave[j] <= e[i].arrival && (max == -1 || microwave[j] > microwave[max]))
          max = j;
      }
      int min = -1;
      for (int j = 0; j < N; j++)
        if (min == -1 || (microwave[j] < microwave[min]))
          min = j;

      if (e[i].arrival - microwave[min] >= T)
        ans = Math.min(ans, microwave[min]);

      if (max != -1) {
        microwave[max] = e[i].arrival + e[i].time;
      } else {
        microwave[min] += e[i].time;
      }
    }

    int min = -1;
    for (int j = 0; j < N; j++)
      if (min == -1 || (microwave[j] < microwave[min]))
        min = j;

    ans = Math.min(ans, microwave[min]);
    out.println(ans);
    out.close();
  }

  static class Event implements Comparable<Event> {
    int arrival, time;

    Event (int arrival, int time) {
      this.arrival = arrival;
      this.time = time;
    }

    @Override
    public int compareTo (Event o) {
      return arrival - o.arrival;
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}