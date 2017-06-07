package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MEC_P6 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int C, R, M, Q;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    C = readInt();
    R = readInt();
    M = readInt();
    Q = readInt();

    long[][] mm = new long[R + 2][C + 2];

    for (int i = 1; i <= R; i++)
      for (int j = 1; j <= C; j++) {
        mm[i][j] = 1;
      }

    int[][] rowSum = new int[15][R + 2];
    int[][] colSum = new int[15][C + 2];

    for (int i = 0; i < M; i++) {
      char c = readCharacter();
      int x = readInt();
      int y = readInt();
      int f = readInt();
      if (c == 'c') {
        colSum[f + 7][x]++;
        colSum[f + 7][y + 1]--;
      } else {
        rowSum[f + 7][x]++;
        rowSum[f + 7][y + 1]--;
      }
    }

    for (int k = 0; k <= 14; k++) {
      for (int i = 1; i <= R; i++) {
        rowSum[k][i] += rowSum[k][i - 1];
        for (int j = 1; j <= C; j++) {
          mm[i][j] *= pow(k - 7, rowSum[k][i]);
        }
      }
      for (int i = 1; i <= C; i++) {
        colSum[k][i] += colSum[k][i - 1];
        for (int j = 1; j <= R; j++) {
          mm[j][i] *= pow(k - 7, colSum[k][i]);
        }
      }
    }

    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        mm[i][j] += mm[i - 1][j] + mm[i][j - 1] - mm[i - 1][j - 1];
      }
    }

    HashMap<Long, Long> hm = new HashMap<Long, Long>();
    int cnt = 0;
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= i; j++) {
        for (int k = 1; k <= C; k++) {
          for (int l = 1; l <= k; l++) {
            long sum = mm[i][k] - mm[j - 1][k] - mm[i][l - 1] + mm[j - 1][l - 1];
            if (hm.get(sum) == null)
              hm.put(sum, 0l);
            hm.put(sum, hm.get(sum) + 1);
            cnt++;
          }
        }
      }
    }

    for (int q = 0; q < Q; q++) {
      long B = readLong();
      long O = readLong();
      if (O == 0 && B == 0) {
        out.println(cnt);
      } else if (B == 0 || O % B != 0) {
        out.println(0);
      } else if (O % B == 0) {
        if (hm.get(O / B) == null)
          out.println(0);
        else
          out.println(hm.get(O / B));
      }
    }
    out.close();
  }

  static int pow (int base, int pow) {
    if (pow == 0)
      return 1;
    if (pow == 1)
      return base;
    if (pow % 2 == 0)
      return pow(base * base, pow / 2);
    return base * pow(base * base, pow / 2);
  }

  static class Interval implements Comparable<Interval> {
    int lo, hi;

    Interval (int lo, int hi) {
      this.lo = lo;
      this.hi = hi;
    }

    @Override
    public int compareTo (Interval o) {
      return lo - o.lo;
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
