package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_SKENER {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int zr = readInt();
    int zc = readInt();
    for (int x = 0; x < r; x++) {
      char[] s = next().toCharArray();
      for (int y = 0; y < zr; y++) {
        for (int z = 0; z < c * zc; z++) {
          System.out.print(s[z / zc]);
        }
        System.out.println();
      }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
