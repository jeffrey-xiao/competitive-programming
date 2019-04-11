package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Spring_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
  static int N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    for (int i = 0; i < N; i++) {
      hm.clear();
      int initial = 0;
      int m = readInt();
      boolean isA = true;
      int score = 0;
      for (int j = 0; j < m; j++) {
        int a = readInt();
        int b = readInt();
        int index = toIndex(Math.min(a, b), Math.max(a, b));
        int count = isTriangle(initial, index);
        if (count == 0) {
          isA = !isA;
        } else {
          if (isA) {
            score += count;
          } else {
            score -= count;
          }
        }
        initial |= 1 << index;
      }

      if (!isA) {
        score = -score;
      }

      if ((isA && solve(initial) + score > 0) || (!isA && solve(initial) + score < 0)) {
        out.printf("Game %d: A wins.\n", i + 1);
      } else {
        out.printf("Game %d: B wins.\n", i + 1);
      }
    }

    out.close();
  }

  static int solve(int state) {
    if (hm.containsKey(state)) {
      return hm.get(state);
    }
    int val = -1 << 30;
    for (int i = 0; i < 18; i++) {
      if ((state & (1 << i)) == 0) {
        int count = isTriangle(state, i);
        if (count > 0) {
          val = Math.max(val, count + solve((state | (1 << i))));
        } else {
          val = Math.max(val, -solve((state | (1 << i)) ^ (1 << 18)));
        }
      }
    }
    if (val == -1 << 30) {
      val = 0;
    }
    hm.put(state, val);
    return val;
  }

  static int isTriangle(int state, int index) {
    // 0 1 2
    // 2 4 5
    // 3 4 7
    // 5 6 10
    // 8 9 15
    // 7 9 11
    // 11 12 16
    // 10 12 13
    // 13 14 17
    int count = 0;
    if (index == 0 && (state & (1 << 1)) > 0 && (state & 1 << 2) > 0) count++;

    if (index == 1 && (state & (1 << 0)) > 0 && (state & 1 << 2) > 0) count++;

    if (index == 2 && (state & (1 << 0)) > 0 && (state & 1 << 1) > 0) count++;
    if (index == 2 && (state & (1 << 4)) > 0 && (state & 1 << 5) > 0) count++;

    if (index == 3 && (state & (1 << 4)) > 0 && (state & 1 << 7) > 0) count++;

    if (index == 4 && (state & (1 << 2)) > 0 && (state & 1 << 5) > 0) count++;
    if (index == 4 && (state & (1 << 3)) > 0 && (state & 1 << 7) > 0) count++;

    if (index == 5 && (state & (1 << 2)) > 0 && (state & 1 << 4) > 0) count++;
    if (index == 5 && (state & (1 << 6)) > 0 && (state & 1 << 10) > 0) count++;

    if (index == 6 && (state & (1 << 5)) > 0 && (state & 1 << 10) > 0) count++;

    if (index == 7 && (state & (1 << 3)) > 0 && (state & 1 << 4) > 0) count++;
    if (index == 7 && (state & (1 << 9)) > 0 && (state & 1 << 11) > 0) count++;

    if (index == 8 && (state & (1 << 9)) > 0 && (state & 1 << 15) > 0) count++;

    if (index == 9 && (state & (1 << 7)) > 0 && (state & 1 << 11) > 0) count++;
    if (index == 9 && (state & (1 << 8)) > 0 && (state & 1 << 15) > 0) count++;

    if (index == 10 && (state & (1 << 5)) > 0 && (state & 1 << 6) > 0) count++;
    if (index == 10 && (state & (1 << 12)) > 0 && (state & 1 << 13) > 0) count++;

    if (index == 11 && (state & (1 << 7)) > 0 && (state & 1 << 9) > 0) count++;
    if (index == 11 && (state & (1 << 12)) > 0 && (state & 1 << 16) > 0) count++;

    if (index == 12 && (state & (1 << 10)) > 0 && (state & 1 << 13) > 0) count++;
    if (index == 12 && (state & (1 << 11)) > 0 && (state & 1 << 16) > 0) count++;

    if (index == 13 && (state & (1 << 10)) > 0 && (state & 1 << 12) > 0) count++;
    if (index == 13 && (state & (1 << 14)) > 0 && (state & 1 << 17) > 0) count++;

    if (index == 14 && (state & (1 << 13)) > 0 && (state & 1 << 17) > 0) count++;

    if (index == 15 && (state & (1 << 8)) > 0 && (state & 1 << 9) > 0) count++;

    if (index == 16 && (state & (1 << 11)) > 0 && (state & 1 << 12) > 0) count++;

    if (index == 17 && (state & (1 << 13)) > 0 && (state & 1 << 14) > 0) count++;

    return count;
  }

  static int toIndex(int a, int b) {
    if (a == 1 && b == 2) return 0;
    if (a == 1 && b == 3) return 1;
    if (a == 2 && b == 3) return 2;
    if (a == 2 && b == 4) return 3;
    if (a == 2 && b == 5) return 4;
    if (a == 3 && b == 5) return 5;
    if (a == 3 && b == 6) return 6;
    if (a == 4 && b == 5) return 7;
    if (a == 4 && b == 7) return 8;
    if (a == 4 && b == 8) return 9;
    if (a == 5 && b == 6) return 10;
    if (a == 5 && b == 8) return 11;
    if (a == 5 && b == 9) return 12;
    if (a == 6 && b == 9) return 13;
    if (a == 6 && b == 10) return 14;
    if (a == 7 && b == 8) return 15;
    if (a == 8 && b == 9) return 16;
    if (a == 9 && b == 10) return 17;
    assert false;
    return 0;
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
