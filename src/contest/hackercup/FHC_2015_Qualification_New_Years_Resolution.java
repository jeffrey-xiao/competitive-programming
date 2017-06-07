package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2015_Qualification_New_Years_Resolution {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    main : for (int z = 1; z <= t; z++) {
      int p = readInt();
      int c = readInt();
      int f = readInt();
      int n = readInt();
      State[] s = new State[n];
      for (int x = 0; x < n; x++)
        s[x] = new State(readInt(), readInt(), readInt());
      for (int x = 1; x < 1 << n; x++) {
        int totalP = 0;
        int totalC = 0;
        int totalF = 0;
        for (int y = 0; y < n; y++) {
          if ((x & (1 << y)) != 0) {
            totalP += s[y].p;
            totalC += s[y].c;
            totalF += s[y].f;

          }
        }
        if (totalP == p && totalC == c && totalF == f) {
          out.printf("Case #%d: yes\n", z);
          continue main;
        }
      }
      out.printf("Case #%d: no\n", z);
    }
    out.close();
  }

  static class State {
    int p, c, f;

    State (int p, int c, int f) {
      this.p = p;
      this.c = c;
      this.f = f;
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
