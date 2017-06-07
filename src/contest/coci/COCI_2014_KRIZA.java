package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_KRIZA {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int n = readInt();
    int k = readInt();
    int[] key = new int[n];
    int first = 0;
    for (int i = 0; i < n; i++) {
      int j = readInt() - 1;
      key[j] = i;
      if (i == 0)
        first = j;
    }
    long solution = 0;
    if (key[first] > key[0])
      solution += n - key[first] + key[0];
    else
      solution += key[0] - key[first];
    long[] ans = new long[n + 1];
    for (int i = 1; i <= n; i++) {
      int curr = key[i - 1];
      int prev = key[(i - 2 + n) % n];
      if (prev > curr)
        ans[i] = n - prev + curr;
      else
        ans[i] = curr - prev;
      ans[i] += ans[i - 1];
    }
    ps.println(solution + ans[n] * (k / n) + ans[k % n] - ans[1]);
    ps.close();
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