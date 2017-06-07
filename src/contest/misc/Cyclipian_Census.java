package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cyclipian_Census {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int numberOfCyclops = readInt();
    int[] cyclops = new int[numberOfCyclops];
    for (int x = 0; x < numberOfCyclops; x++)
      cyclops[x] = readInt();
    Arrays.sort(cyclops);
    int numOfQueries = readInt();
    for (int x = 0; x < numOfQueries; x++)
      System.out.println(cyclops[readInt() - 1]);
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }

}
