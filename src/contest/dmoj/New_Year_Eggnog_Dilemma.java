package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class New_Year_Eggnog_Dilemma {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws Exception {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int q = readInt();
    for (int i = 0; i < q; i++) {
      int query = readInt();
      int n = readInt();
      int m = readInt();
      if (query == 1)
        out.println(getDays(n, m));
      else
        for (int j = 0; ; j++)
          if (pow(m + 1, j) >= n)
            out.println(j);
    }

    out.close();
  }

  static int getDays(int n, int f) {
    if (n == 1)
      return 0;
    if (f >= 60)
      return 1;
    if (f == 1)
      return n - 1;
    for (int i = 2; ; i++)
      if (pow(i, f) >= n)
        return i - 1;
  }

  static long pow(long n, long k) {
    if (k == 0)
      return 1;
    if (k == 1)
      return n;
    if (k % 2 == 0)
      return pow(n * n, k / 2);
    else
      return n * pow(n * n, k / 2);
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