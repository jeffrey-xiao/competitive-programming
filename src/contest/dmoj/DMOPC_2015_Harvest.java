package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_Harvest {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int M = readInt();
    long K = readLong();
    long[] prefix = new long[N + 2];
    for (int i = 0; i < M; i++) {
      prefix[1] += 1;
      prefix[N + 1] += -1;
      int x = readInt();
      int y = readInt() + 1;
      prefix[x] -= 1;
      prefix[y] += 1;
    }

    for (int i = 1; i <= N + 1; i++) {
      prefix[i] += prefix[i - 1];
    }
    for (int i = 1; i < +N + 1; i++) {
      prefix[i] += prefix[i - 1];
    }

    int lo = 0;
    int hi = N;

    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      boolean poss = false;
      for (int i = mid; i <= N; i++)
        if (prefix[i] - prefix[i - mid] >= K)
          poss = true;
      if (poss)
        hi = mid - 1;
      else
        lo = mid + 1;
    }
    if (lo == N + 1)
      out.println(-1);
    else
      out.println(lo);
    out.close();
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
