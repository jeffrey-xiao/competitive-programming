package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Qualification_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      int N = readInt();
      Interval[] intervals = new Interval[N];
      for (int i = 0; i < N; i++) {
        intervals[i] = new Interval(i, readInt(), readInt());
      }
      Arrays.sort(intervals);
      int cEnd = 0, jEnd = 0;
      boolean possible = true;
      boolean[] ans = new boolean[N];
      for (int i = 0; i < N; i++) {
        if (intervals[i].start >= cEnd && (intervals[i].start < jEnd || cEnd >= jEnd)) {
          cEnd = intervals[i].end;
        } else if (intervals[i].start >= jEnd && (intervals[i].start < cEnd || jEnd >= cEnd)) {
          jEnd = intervals[i].end;
          ans[intervals[i].index] = true;
        } else {
          possible = false;
        }
      }
      out.printf("Case #%d: ", t);
      if (possible) {
        for (int i = 0; i < N; i++) {
          out.print(ans[i] ? "J" : "C");
        }
      } else {
        out.print("IMPOSSIBLE");
      }
      out.println();
    }
    out.close();
  }

  static class Interval implements Comparable<Interval> {
    int index, start, end;
    Interval (int index, int start, int end) {
      this.index = index;
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Interval i) {
      return end - i.end;
    }
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
