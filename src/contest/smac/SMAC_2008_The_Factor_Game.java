package contest.smac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SMAC_2008_The_Factor_Game {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    System.out.flush();
    int n = readInt();

    while (n != 1) {
      ArrayList<Integer> primes = new ArrayList<Integer>();
      int newN = n;
      int counter = 2;
      while (newN != 1) {
        if (counter * counter > newN) {
          primes.add(newN);
          break;
        } else if (newN % counter == 0) {
          newN /= counter;
          primes.add(counter);
        } else
          counter++;
      }
      int num = 1;
      for (int x = 0; x < primes.size() / 2; x++) {
        num *= primes.get(x);
      }
      System.out.print(num + " " + n / num + "\n");
      System.out.flush();
      n = readInt();
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
    return Integer.parseInt(readLine());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
