package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_Scribe {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] sum1 = new int[10];
  static int[] sum2 = new int[10];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 0; t < T; t++) {
      int x = readInt();
      int y = readInt();
      for (int i = 0; i < 10; i++) {
        sum1[i] = 0;
        sum2[i] = 0;
      }
      x--;
      if (x > 0) {
        int pow = 0;
        while (pow(10, pow + 1) <= x)
          pow++;
        getCnt(x, pow, sum1, true);
      }
      int pow = 0;
      while (pow(10, pow + 1) <= y)
        pow++;
      getCnt(y, pow, sum2, true);
      for (int i = 0; i < 10; i++)
        out.printf(sum2[i] - sum1[i] + " ");
      out.println();
    }

    out.close();
  }

  static void getCnt(int n, int pow, int[] sum, boolean sub) {
    int temp = n / pow(10, pow);
    for (int i = 0; i < temp; i++)
      sum[i] += pow(10, pow);

    sum[temp] += n % pow(10, pow) + 1;

    if (sub)
      sum[0] -= pow(10, pow);

    if (pow == 0)
      return;
    for (int i = 0; i < 10; i++)
      sum[i] += temp * pow * pow(10, pow - 1);
    if (sub)
      sum[0] -= (pow(10, pow) - 1) / 9;
    getCnt(n % pow(10, pow), pow - 1, sum, false);
  }

  static int pow(int n, int m) {
    if (m == 0)
      return 1;
    if (m == 1)
      return n;
    if (m % 2 == 0)
      return pow(n * n, m / 2);
    else
      return n * pow(n * n, m / 2);
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
