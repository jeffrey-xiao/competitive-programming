package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Red_Sun_Simulator {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, Q;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    Q = readInt();

    int hi = 0;
    int lo = 0;
    for (int i = 0; i < N; i++) {
      int F = readInt();
      int M = readInt();
      boolean addHi = false;
      boolean addLo = true;
      for (int j = 0; j < M; j++) {
        int A = readInt();
        int B = readInt();
        if (A % F == 0 || (A / F != B / F))
          addHi = true;
        if (!(F == 1 || (A == B && A % F == 0)))
          addLo = false;
      }
      if (addLo) {
        lo++;
        hi++;
      } else if (addHi) {
        hi++;
      }
    }

    for (int i = 0; i < Q; i++) {
      int t = readInt();
      if (lo <= t && t <= hi)
        out.println("Y");
      else
        out.println("N");
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
