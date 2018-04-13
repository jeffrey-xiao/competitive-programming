package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Qualification_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));
    
    int T = readInt();

    outer : for (int t = 1; t <= T; t++) {
      ArrayList<Integer> left = new ArrayList<Integer>(), right = new ArrayList<Integer>();
      ArrayList<Integer> sorted = new ArrayList<Integer>();

      int N = readInt();
      for (int i = 0; i < N; i++) {
        if (i % 2 == 0) {
          left.add(readInt());
        } else {
          right.add(readInt());
        }
      }
      Collections.sort(left);
      Collections.sort(right);
      for (int i = 0; i < N; i++) {
        if (i % 2 == 0) {
          sorted.add(left.get(i / 2));
        } else {
          sorted.add(right.get(i / 2));
        }
      }
      for (int i = 0; i < N - 1; i++) {
        if (sorted.get(i) > sorted.get(i + 1)) {
          out.printf("Case #%d: %d\n", t, i);
          continue outer;
        }
      }
      out.printf("Case #%d: OK\n", t);
    }

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
