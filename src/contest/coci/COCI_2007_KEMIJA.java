package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_KEMIJA {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static long[] val, ans;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    val = new long[N];
    ans = new long[N];

    long total = 0;

    for (int i = 0; i < N; i++)
      total += (val[i] = readInt());

    total /= 3;
    if (N % 3 == 2) {
      long curr = 0;
      for (int i = 1; i + 1 < N; i += 3)
        curr += val[i];
      ans[0] = val[N - 1] - (total - curr);

      curr = 0;
      for (int i = 2; i + 1 < N; i += 3)
        curr += val[i];
      ans[1] = val[0] - (total - curr);

      for (int i = 2; i < N; i++) {
        ans[i] = val[i - 1] - ans[i - 1] - ans[i - 2];
      }

    } else if (N % 3 == 1) {
      long curr = 0;
      for (int i = 1; i + 1 < N; i += 3)
        curr += val[i];
      ans[N - 1] = total - curr;

      curr = 0;
      for (int i = 0; i + 1 < N; i += 3)
        curr += val[i];
      ans[N - 2] = total - curr;

      for (int i = N - 3; i >= 0; i--)
        ans[i] = val[i + 1] - ans[i + 1] - ans[i + 2];
    } else if (N % 3 == 0) {
      // assume ans[0] = 1 and ans[1] = 2

      ans[0] = 1;
      ans[1] = 2;

      long maxAddX = 0;
      long maxAddY = 0;

      for (int i = 2; i < N; i++) {
        ans[i] = val[i - 1] - ans[i - 1] - ans[i - 2];
        if (i % 3 == 0)
          maxAddX = Math.max(maxAddX, -(ans[i] - ans[0]));
        else if (i % 3 == 1)
          maxAddY = Math.max(maxAddY, -(ans[i] - ans[1]));
      }

      ans[0] += maxAddX;
      ans[1] += maxAddY;

      for (int i = 2; i < N; i++)
        ans[i] = val[i - 1] - ans[i - 1] - ans[i - 2];
    }

    for (int i = 0; i < N; i++)
      out.println(ans[i]);
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
