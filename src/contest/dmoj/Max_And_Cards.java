package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Max_And_Cards {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static long[] fact;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int q = readInt();

    fact = new long[20];
    fact[0] = 1;
    for (int i = 1; i <= 19; i++)
      fact[i] = fact[i - 1] * i;

    for (int i = 0; i < q; i++) {
      solve(n, readLong() + 1);
    }

    out.close();
  }

  static void solve (int n, long q) {
    int[] val = new int[n];
    for (int i = 1; i <= n; i++)
      val[i - 1] = i;
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 1; j <= i + 1; j++) {
        if (fact[i] * j >= q) {
          for (int k = j - 1; k < n - 1; k++)
            val[k] = val[k + 1];
          q -= fact[i] * (j - 1);
          break;
        }
      }
    }
    out.println();
    out.flush();
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
