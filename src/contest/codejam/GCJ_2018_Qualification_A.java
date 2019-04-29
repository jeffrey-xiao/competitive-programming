package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Qualification_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

  outer:
    for (int t = 1; t <= T; t++) {
      int D = readInt();
      String s = next();
      ArrayList<Integer> shots = new ArrayList<Integer>();
      int currCharge = 0;
      int currDamage = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == 'C') {
          currCharge++;
        } else {
          shots.add(currCharge);
          currDamage += 1 << currCharge;
        }
      }
      int ans = 0;
      while (currDamage > D) {
        for (int i = shots.size() - 1; i >= 0; i--) {
          if (i == 0) {
            if (shots.get(i) == 0) {
              out.printf("Case #%d: IMPOSSIBLE%n", t);
              continue outer;
            } else {
              shots.set(i, shots.get(i) - 1);
              currDamage -= 1 << shots.get(i);
              ans++;
              break;
            }
          } else {
            if (shots.get(i) > shots.get(i - 1)) {
              shots.set(i, shots.get(i) - 1);
              currDamage -= 1 << shots.get(i);
              ans++;
              break;
            }
          }
        }
      }
      out.printf("Case #%d: %d%n", t, ans);
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
