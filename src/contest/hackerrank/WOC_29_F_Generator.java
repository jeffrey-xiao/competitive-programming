package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class WOC_29_F_Generator {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int POINT_SIZE = 74;
  static final int SUM_SIZE = 16108764;
  static int[] x = new int[POINT_SIZE], y = new int[POINT_SIZE];
  static boolean found = false;
  static double start = 0;
  static TreeSet<State> ts = new TreeSet<State>();
  static int[] ansx = new int[12], ansy = new int[12];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int X = readInt();
    int Y = readInt();

    ansx[0] = X;
    ansy[0] = Y;

    int cnt = 0;
    start = dist(X, Y);
    start -= (int)start;

    for (int i = 1; i <= 12; i++) {
      for (int j = i; j <= 12; j++) {
        double dist = dist(i, j);
        if (dist != (int)dist) {
          x[cnt] = i;
          y[cnt] = j;
          cnt++;
        }
      }
    }

    cnt = 0;

    for (int a = 0; a < POINT_SIZE; a++)
      for (int b = a; b < POINT_SIZE; b++)
        for (int c = b + 1; c < POINT_SIZE; c++)
          for (int d = c + 1; d < POINT_SIZE; d++)
            for (int e = d + 1; e < POINT_SIZE; e++) {
              double dist = dist(x[a], y[a]) + dist(x[b], y[b]) + dist(x[c], y[c]) + dist(x[d], y[d]) + dist(x[e], y[e]);
              dist -= (int)(dist);
              State add = new State(dist);
              add.indexes = add.indexes * 100 + a;
              add.indexes = add.indexes * 100 + b;
              add.indexes = add.indexes * 100 + c;
              add.indexes = add.indexes * 100 + d;
              add.indexes = add.indexes * 100 + e;
              ts.add(add);
              cnt++;
            }

    compute(0, 0, new int[6]);

    for (int i = 0; i < 12; i++)
      out.printf("%d %d\n", ansx[i], ansy[i]);

    out.close();
  }

  static void compute (int i, int j, int[] indexes) {
    if (found)
      return;
    if (j == 6) {
      double dist = start;
      for (int k = 0; k < j; k++)
        dist += dist(x[indexes[k]], y[indexes[k]]);
      dist -= (int)dist;

      State candidate = ts.floor(new State(1.0 - dist));
      if (candidate != null) {
        double poss = dist + candidate.value;
        poss -= (int)poss;
        poss = 1.0 - poss;
        if (poss < 1e-12) {
          found = true;
          int cnt = 1;
          for (int k = 0; k < j; k++) {
            ansx[cnt] = x[indexes[k]];
            ansy[cnt] = y[indexes[k]];
            cnt++;
          }
          long curr = candidate.indexes;
          for (int k = 0; k < 5; k++) {
            ansx[cnt] = x[(int)(curr % 100)];
            ansy[cnt] = y[(int)(curr % 100)];
            curr /= 100;
            cnt++;
          }
        }
      }
      candidate = ts.ceiling(new State(1.0 - dist));
      if (candidate != null) {
        double poss = dist + candidate.value;
        poss -= (int)poss;
        if (poss < 1e-12) {
          found = true;
          int cnt = 1;
          for (int k = 0; k < j; k++) {
            ansx[cnt] = x[indexes[k]];
            ansy[cnt] = y[indexes[k]];
            cnt++;
          }
          long curr = candidate.indexes;
          for (int k = 0; k < 5; k++) {
            ansx[cnt] = x[(int)(curr % 100)];
            ansy[cnt] = y[(int)(curr % 100)];
            curr /= 100;
            cnt++;
          }
        }
      }
      return;
    }
    if (i == POINT_SIZE)
      return;
    indexes[j] = i;
    compute(i + 1, j + 1, indexes);
    compute(i + 1, j, indexes);
  }

  static class State implements Comparable<State> {
    Double value;
    long indexes;

    State (double value) {
      this.value = value;
    }

    @Override
    public int compareTo (State s) {
      return value.compareTo(s.value);
    }
  }

  static double dist (int x, int y) {
    return Math.sqrt(x * x + y * y);
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
