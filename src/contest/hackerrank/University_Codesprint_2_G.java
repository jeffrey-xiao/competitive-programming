package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class University_Codesprint_2_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int G;
  static long N, M, S;
  static int ans;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    G = readInt();

    for (int g = 0; g < G; g++) {
      N = readLong();
      M = readLong();
      S = readLong();

      // number of edges from the last node
      long last = Math.max(1, M - (N - 2) * (N - 1) / 2);
      // total number of edges from all nodes, except last node
      long pre = Math.min(M - 1, (N - 2) * (N - 1) / 2);
      // value of edges from last node
      long val = S - N + 2;
      // current answer
      long ans = last * val + pre;

      if (last <= (float) (N - 1) / 2.0 || N == 2)
        out.println(ans);
      else {
        // added value to all nodes from 2 to N - 1
        long K = (val - 1) / (N - 1);
        // new answer
        ans -= K * (last * (N - 2) - pre);
        //current value in the last node
        val -= (N - 2) * K;
        if (val - K - 1 > 1) {
          // amount which can be decreased from the last node
          long rem = val - K - 2;
          long am = pre - ((N - rem - 1) * (N - rem - 2)) / 2;
          if (am <= rem * last)
            // new answer, in case we will decrease value in the last node again
            ans -= rem * last - am;
        }
        out.println(ans);
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
