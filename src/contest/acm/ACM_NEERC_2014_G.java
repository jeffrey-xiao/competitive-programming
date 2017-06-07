package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class ACM_NEERC_2014_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] val;
  static int N, K;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();
    val = new int[N + 1];
    int min = 1 << 30;
    Stack<Integer> poss = new Stack<Integer>();
    for (int i = 1; i <= N; i++) {
      val[i] = readInt();
      min = Math.min(min, val[i]);
    }

    int total = 0;
    for (int i = 1; i < K; i++) {
      total += val[i];
      if (val[i] > min)
        poss.push(i);
    }
    int ans = 0;
    for (int i = K; i <= N; i++) {
      total -= val[i - K];
      total += val[i];
      if (val[i] > min)
        poss.push(i);
      while (total >= 0) {
        int sub = Math.min(total + 1, val[poss.peek()] - min);
        total -= sub;
        ans += sub;
        int next = poss.pop();
        val[next] -= sub;
        if (val[next] > min)
          poss.push(next);
      }
    }

    out.println(ans);
    for (int i = 1; i <= N; i++)
      out.print(val[i] + " ");
    out.println();
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
