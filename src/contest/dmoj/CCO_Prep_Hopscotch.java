package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCO_Prep_Hopscotch {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, Q;
  static int blocks, interval;

  static int[] val, block, right, pos, cnt;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    interval = (int) (Math.sqrt(N));

    val = new int[N];
    block = new int[N];
    right = new int[N];
    pos = new int[N];
    cnt = new int[N];

    for (int i = 0; i < N; i++) {
      val[i] = readInt();
      block[i] = (i / interval);

      right[block[i]] = i;
      blocks = block[i];
    }
    int last = 0;
    for (int i = 0; i <= blocks; i++) {
      for (int j = right[i]; j >= last; j--) {
        if (j + val[j] > right[i]) {
          cnt[j] = 1;
          pos[j] = j + val[j];
        } else {
          cnt[j] = cnt[j + val[j]] + 1;
          pos[j] = pos[j + val[j]];
        }
      }
      last = right[i] + 1;
    }

    Q = readInt();
    for (int i = 0; i < Q; i++) {
      int type = readInt();
      if (type == 1) {
        int x = readInt();
        int ans = 0;
        while (x < N) {
          ans += cnt[x];
          x = pos[x];
        }
        out.println(ans);
      } else {
        int x = readInt();
        int y = readInt();
        val[x] = y;
        int currBlock = x / interval;
        last = 0;
        if (currBlock != 0)
          last = right[currBlock - 1] + 1;
        for (int j = x; j >= last; j--) {
          if (j + val[j] > right[currBlock]) {
            cnt[j] = 1;
            pos[j] = j + val[j];
          } else {
            cnt[j] = cnt[j + val[j]] + 1;
            pos[j] = pos[j + val[j]];
          }
        }

      }
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
