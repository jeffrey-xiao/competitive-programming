package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1C_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      int[] count = new int[N];
      boolean[] used = new boolean[N];
      for (int k = 0; k < N; k++) {
        int flavors = readInt();
        TreeSet<Integer> likedFlavors = new TreeSet<Integer>();
        for (int i = 0; i < flavors; i++) {
          int flavor = readInt();
          likedFlavors.add(flavor);
          count[flavor]++;
        }
        ArrayList<Integer> best = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
          if (used[i] || !likedFlavors.contains(i)) {
            continue;
          }

          if (best.size() == 0) {
            best.add(i);
          } else if (count[best.get(0)] == count[i]) {
            best.add(i);
          } else if (count[best.get(0)] > count[i]) {
            best.clear();
            best.add(i);
          }
        }
        if (best.size() == 0) {
          out.println(-1);
        } else {
          int next = (int)(Math.random() * best.size());
          used[best.get(next)] = true;
          out.println(best.get(next));
        }
        out.flush();
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
