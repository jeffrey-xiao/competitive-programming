package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GCJ_2017_Round_1B_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static char first;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    main:
    for (int t = 1; t <= T; t++) {
      int N = readInt();
      int R = readInt();
      int O = readInt();
      int Y = readInt();
      int G = readInt();
      int B = readInt();
      int V = readInt();

      // B must match with O;
      // R must match with G;
      // Y must match with V;

      // Cases when only color and its match exists
      if (O == B && R == 0 && G == 0 && Y == 0 && V == 0) {
        out.printf("Case #%d: ", t);
        for (int i = 0; i < O; i++)
          out.print("BO");
        out.println();
        continue main;
      }
      if (O == 0 && B == 0 && R == G && Y == 0 && V == 0) {
        out.printf("Case #%d: ", t);
        for (int i = 0; i < G; i++)
          out.print("RG");
        out.println();
        continue main;
      }
      if (O == 0 && B == 0 && R == 0 && G == 0 && Y == V) {
        out.printf("Case #%d: ", t);
        for (int i = 0; i < V; i++)
          out.print("YV");
        out.println();
        continue main;
      }

      // Cases when not possible to match secondary colors
      if (O > 0 && O + 1 > B) {
        out.printf("Case #%d: IMPOSSIBLE\n", t);
        continue main;
      } else {
        B -= O;
        N -= 2 * O;
      }
      if (G > 0 && G + 1 > R) {
        out.printf("Case #%d: IMPOSSIBLE\n", t);
        continue main;
      } else {
        R -= G;
        N -= 2 * G;
      }
      if (V > 0 && V + 1 > Y) {
        out.printf("Case #%d: IMPOSSIBLE\n", t);
        continue main;
      } else {
        Y -= V;
        N -= 2 * V;
      }

      // only consider R, Y, B
      char[] res = new char[N];
      State[] states = {new State('R', R), new State('Y', Y), new State('B', B)};
      char prev = 'a';
      first = 'R';
      for (int i = 0; i < N; i++) {
        Arrays.sort(states);
        boolean valid = false;
        for (int j = 0; j < 3; j++) {
          if (states[j].c != prev && states[j].occ > 0) {
            res[i] = states[j].c;
            states[j].occ--;
            valid = true;
            prev = states[j].c;
            if (i == 0)
              first = prev;
            break;
          }
        }
        if (!valid) {
          out.printf("Case #%d: IMPOSSIBLE\n", t);
          continue main;
        }
      }

      // Checking if first and last don't match
      if (res[0] != res[N - 1]) {
        // printing and "expanding" the secondary colors
        boolean expandO = O > 0 ? true : false;
        boolean expandG = G > 0 ? true : false;
        boolean expandV = V > 0 ? true : false;
        out.printf("Case #%d: ", t);
        for (int i = 0; i < N; i++) {
          out.print(res[i]);
          if (res[i] == 'B' && expandO) {
            for (int j = 0; j < O; j++)
              out.print("OB");
            expandO = false;
          }
          if (res[i] == 'R' && expandG) {
            for (int j = 0; j < G; j++)
              out.print("GR");
            expandG = false;
          }
          if (res[i] == 'Y' && expandV) {
            for (int j = 0; j < V; j++)
              out.print("VY");
            expandV = false;
          }
        }
        out.println();
      } else {
        out.printf("Case #%d: IMPOSSIBLE\n", t);
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

  static class State implements Comparable<State> {
    char c;
    int occ;

    State(char c, int occ) {
      this.c = c;
      this.occ = occ;
    }

    @Override
    public int compareTo(State o) {
      if (occ == o.occ) {
        if (c == o.c)
          return 0;
        if (first == c)
          return -1;
        if (first == o.c)
          return 1;
        return c - o.c;
      }
      return o.occ - occ;
    }
  }
}
