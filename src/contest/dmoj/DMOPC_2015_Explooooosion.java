package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DMOPC_2015_Explooooosion {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static long[] val;

  static final int MOD = (int)(1e9 + 7);

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    long smallest = 0;
    long largest = 0;

    N = readInt();

    val = new long[N];

    PriorityQueue<Long> pq = new PriorityQueue<Long>();

    int ones = 0;
    for (int i = 0; i < N; i++) {
      val[i] = readInt();
      if (val[i] != 1) {
        smallest += val[i];
        pq.offer(val[i]);
      } else {
        ones++;
      }
    }

    Arrays.sort(val);

    if (smallest == 0)
      smallest = val[0];

    while (ones > 0) {
      if (pq.size() >= 2 && ones >= 2) {
        long a = pq.poll();
        long b = pq.poll();

        if (a == 2 && b == 2) {
          a++;
          b++;
        } else {
          pq.offer(2l);
        }

        pq.offer(a);
        pq.offer(b);
        ones -= 2;
      } else if (pq.size() < 2 && ones >= 2) {
        pq.offer(2l);
        ones -= 2;
      } else if (pq.size() != 0) {
        pq.offer(pq.poll() + 1);
        ones -= 1;
      } else {
        pq.offer(1l);
        ones -= 1;
      }
    }

    while (pq.size() >= 2) {
      pq.offer(pq.poll() * pq.poll() % MOD);
    }

    largest = pq.poll();
    out.println(smallest);
    out.println(largest);
    if (largest == 10)
      out.println(Arrays.toString(val));
    out.close();
  }

  static long bfmin () {
    long[] dp = new long[1 << N];
    Arrays.fill(dp, 1 << 30);
    for (int i = 1; i < 1 << N; i++) {
      int sz = 0;
      int sum = 0;
      for (int j = 0; j < N; j++)
        if ((i & 1 << j) > 0) {
          sz++;
          sum += val[j];
        }
      if (sz == 1)
        dp[i] = sum;
      else {
        for (int j = 1; j < 1 << N; j++) {
          if ((i & j) == j && (i ^ j) != 0) {
            dp[i] = Math.min(dp[i], dp[j] * dp[i ^ j]);
            dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
          }
        }
      }
    }
    return dp[(1 << N) - 1];
  }

  static long bfmax () {
    long[] dp = new long[1 << N];
    for (int i = 1; i < 1 << N; i++) {
      int sz = 0;
      int sum = 0;
      for (int j = 0; j < N; j++)
        if ((i & 1 << j) > 0) {
          sz++;
          sum += val[j];
        }
      if (sz == 1)
        dp[i] = sum;
      else {
        for (int j = 1; j < 1 << N; j++) {
          if ((i & j) == j && (i ^ j) != 0) {
            dp[i] = Math.max(dp[i], dp[j] * dp[i ^ j]);
            dp[i] = Math.max(dp[i], dp[j] + dp[i ^ j]);
          }
        }
      }
    }
    return dp[(1 << N) - 1];
  }

  static class State {
    long val;
    boolean overflow;

    State (long val, boolean overflow) {
      this.val = val;
      this.overflow = overflow;
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
