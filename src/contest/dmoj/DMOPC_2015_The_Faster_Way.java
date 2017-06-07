package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_The_Faster_Way {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, R, M;

  static boolean[] isFast;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    R = readInt();
    M = readInt();

    isFast = new boolean[N + 1];

    for (int i = 0; i < R; i++)
      isFast[readInt()] = true;

    int normal = 0;
    int fast = 0;
    for (int i = 0; i < M; i++) {
      if (isFast[readInt()])
        fast++;
      else
        normal++;
    }

    if (fast >= normal) {
      fast = (M + 1) / 2;
      normal = M / 2;
    }
    out.println(getTime(fast) + getTime(normal));
    out.close();
  }

  static int getTime (int x) {
    return x * (x + 1) / 2;
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
