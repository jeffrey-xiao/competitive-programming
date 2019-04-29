package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2016_Qualification_The_Price_Is_Correct {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int n = readInt();
      long k = readLong();
      long[] sum = new long[n + 1];
      for (int i = 1; i <= n; i++)
        sum[i] = sum[i - 1] + readLong();
      long ans = 0;
      for (int i = 1; i <= n; i++) {
        int lo = i;
        int hi = n;
        while (lo <= hi) {
          int mid = (hi + lo) >> 1;
          if (sum[mid] - sum[i - 1] <= k)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        ans += (lo - i);
      }
      out.printf("Case #%d: %d%n", t, ans);
    }
    out.close();
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
