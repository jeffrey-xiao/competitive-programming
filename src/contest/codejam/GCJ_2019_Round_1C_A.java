package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1C_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, A;
  static char[][] programs;
  static LinkedList<Character> moves;
  static StringBuilder ans;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      A = readInt();

      programs = new char[A][];
      moves = new LinkedList<Character>();
      ans = new StringBuilder();
      ArrayList<Integer> remaining = new ArrayList<Integer>();
      for (int i = 0; i < A; i++) {
        programs[i] = next().toCharArray();
        remaining.add(i);
      }

      out.printf("Case #%d: ", t);
      if (check(0, remaining)) {
        out.println(ans);
      } else {
        out.println("IMPOSSIBLE");
      }
    }

    out.close();
  }

  static boolean check(int i, ArrayList<Integer> remaining) {
    ArrayList<Integer> nextRemaining = new ArrayList<Integer>();
    boolean[] v = new boolean[3];

    for (Integer j : remaining) {
      switch (programs[j][i % programs[j].length]) {
        case 'R':
          v[0] = true;
          break;
        case 'P':
          v[1] = true;
          break;
        case 'S':
          v[2] = true;
          break;
      }
    }

    if (v[0] && v[1] && v[2]) {
      return false;
    }
    if (!v[0] && !v[1] && !v[2]) {
      return true;
    }

    Character target, use;
    if (v[0] && !v[2]) {
      target = 'R';
      use = 'P';
    } else if (v[1] && !v[0]) {
      target = 'P';
      use = 'S';
    } else {
      target = 'S';
      use = 'R';
    }

    for (Integer j : remaining) {
      if (programs[j][i % programs[j].length] != target) {
        nextRemaining.add(j);
      }
    }
    ans.append(use);
    return check(i + 1, nextRemaining);
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
