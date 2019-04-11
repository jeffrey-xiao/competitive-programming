package contest.woburn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2002_Old_MacDonalds_Farm {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int heads = readInt();
      int feet = readInt();
      int eyes = readInt();
      int tails = readInt();

      int apes = heads - tails;
      heads -= apes;
      feet -= 2 * apes;
      eyes -= 2 * apes;
      if ((2 * eyes - feet) % 6 != 0) {
        System.out.println("FUNNY FARM");
        continue;
      }
      int fish = (2 * eyes - feet) / 6;
      heads -= fish;
      eyes -= 3 * fish;
      if ((feet - 2 * heads) % 2 != 0) {
        System.out.println("FUNNY FARM");
        continue;
      }
      int bison = (feet - 2 * heads) / 2;

      int parrot = heads - bison;
      if (apes < 0 || bison < 0 || parrot < 0 || fish < 0) {
        System.out.println("FUNNY FARM");
        continue;
      }
      System.out.println(apes + " " + bison + " " + parrot + " " + fish);
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
