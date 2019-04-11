package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2009_KAJAK {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  @SuppressWarnings("unused")
  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int[][] places = new int[9][2];
    int counter = 0;
    for (int x = 0; x < r; x++) {
      String s = readLine();
      for (int y = 1; y < s.length() - 1; y++) {
        if (s.charAt(y) != '.') {
          places[counter][0] = s.charAt(y) - 49;
          places[counter][1] = y;
          counter++;
          break;
        }
      }
    }
    Arrays.sort(places, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return b[1] - a[1];
      }

    });
    int[] standings = new int[9];
    int currPos = 0;
    int dist = 10000;
    for (int x = 0; x < 9; x++) {
      if (places[x][1] < dist)
        currPos++;
      dist = places[x][1];

      standings[places[x][0]] = currPos;

    }
    for (int i : standings)
      System.out.println(i);
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
