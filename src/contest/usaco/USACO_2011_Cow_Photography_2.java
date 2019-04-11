package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2011_Cow_Photography_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Cow[] order = new Cow[n];
    final int[][] photoToIndex = new int[5][n];
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < n; y++) {
        int cow = readInt();
        order[y] = new Cow(cow, y);
        photoToIndex[x][y] = y;
      }
    }
    Arrays.sort(order, new Comparator<Cow>() {

      @Override
      public int compare(Cow o1, Cow o2) {
        int total = 0;
        for (int x = 0; x < 5; x++)
          if (photoToIndex[x][o1.pos] < photoToIndex[x][o2.pos])
            total++;
        return total - 2;
      }

    });
    for (int x = 0; x < n; x++)
      ps.println(order[x].value);
    ps.close();
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

  static class Cow {
    int value, pos;

    Cow(int value, int pos) {
      this.value = value;
      this.pos = pos;
    }
  }
}
