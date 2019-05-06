package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1C_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, F;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    F = readInt();
    for (int t = 1; t <= T; t++) {
      StringBuilder ans = new StringBuilder();
      ArrayList<Integer> remaining = new ArrayList<Integer>();
      for (int i = 0; i < 119; i++) {
        remaining.add(i);
      }
      int size = 24;
      boolean[] used = new boolean[5];

      for (int i = 0; i < 5; i++) {
        ArrayList<Character> figures = new ArrayList<Character>();
        int[] count = new int[5];
        for (Integer j : remaining) {
          out.println(j * 5 + i + 1);
          out.flush();
          char figure = readCharacter();
          figures.add(figure);
          count[figure - 'A']++;
        }

        ArrayList<Integer> nextRemaining = new ArrayList<Integer>();
        for (int j = 0; j < 5; j++) {
          if (!used[j] && count[j] != size) {
            ans.append((char)(j + 'A'));
            used[j] = true;
            for (int k = 0; k < remaining.size(); k++) {
              if (figures.get(k) == (char)(j + 'A')) {
                nextRemaining.add(remaining.get(k));
              }
            }
          }
        }
        remaining = nextRemaining;
        if (5 - i - 1 != 0) {
          size /= 5 - i - 1;
        }
      }

      out.println(ans);
      out.flush();
      if (next().equals("N")) {
        out.close();
        return;
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
