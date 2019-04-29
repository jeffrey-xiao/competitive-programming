import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1B_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static char[] orgC, orgJ;
  static char[] C, J;
  static char[] bestC, bestJ;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      orgC = next().toCharArray();
      orgJ = next().toCharArray();
      C = Arrays.copyOf(orgC, orgC.length);
      J = Arrays.copyOf(orgJ, orgJ.length);
      bestC = null;
      bestJ = null;

      solve(0, 0);
      out.printf("Case #%d: %s %s%n", t, new String(bestC), new String(bestJ));
    }

    out.close();
  }

  static void setBest(char[] C, char[] J) {
    if (bestC == null) {
      bestC = Arrays.copyOf(C, C.length);
      bestJ = Arrays.copyOf(J, J.length);
    } else {
      String bestCS = new String(bestC);
      String bestJS = new String(bestJ);
      String CS = new String(C);
      String JS = new String(J);
      long best = Math.abs(Long.parseLong(bestCS) - Long.parseLong(bestJS));
      long curr = Math.abs(Long.parseLong(CS) - Long.parseLong(JS));
      if (curr < best
          || (curr == best && CS.compareTo(bestCS) < 0)
          || (curr == best && CS.equals(bestCS) && JS.compareTo(bestJS) < 0)) {
        bestC = Arrays.copyOf(C, C.length);
        bestJ = Arrays.copyOf(J, J.length);
      }
    }
  }

  static void solve(int i, int cmp) {
    if (i == C.length) {
      setBest(C, J);
      return;
    }

    if (orgC[i] == orgJ[i] && orgC[i] == '?') {
      if (cmp == 0) {
        C[i] = '0';
        J[i] = '0';
        solve(i + 1, 0);
        C[i] = '0';
        J[i] = '1';
        solve(i + 1, -1);
        C[i] = '1';
        J[i] = '0';
        solve(i + 1, 1);
      } else if (cmp <= -1) {
        C[i] = '9';
        J[i] = '0';
        solve(i + 1, -1);
      } else if (cmp >= 1) {
        C[i] = '0';
        J[i] = '9';
        solve(i + 1, 1);
      }
    } else if (orgC[i] == '?') {
      if (cmp == 0) {
        if (J[i] < '9') {
          C[i] = (char)(J[i] + 1);
          solve(i + 1, 1);
        }
        if (J[i] > '0') {
          C[i] = (char)(J[i] - 1);
          solve(i + 1, -1);
        }
        C[i] = J[i];
        solve(i + 1, 0);
      } else if (cmp <= -1) {
        C[i] = '9';
        solve(i + 1, -1);
      } else if (cmp >= 1) {
        C[i] = '0';
        solve(i + 1, 1);
      }
    } else if (orgJ[i] == '?') {
      if (cmp == 0) {
        if (C[i] < '9') {
          J[i] = (char)(C[i] + 1);
          solve(i + 1, -1);
        }
        if (C[i] > '0') {
          J[i] = (char)(C[i] - 1);
          solve(i + 1, 1);
        }
        J[i] = C[i];
        solve(i + 1, 0);
      } else if (cmp <= -1) {
        J[i] = '0';
        solve(i + 1, -1);
      } else if (cmp >= 1) {
        J[i] = '9';
        solve(i + 1, 1);
      }
    } else {
      if (cmp != 0) {
        solve(i + 1, cmp);
      } else {
        solve(i + 1, new Character(C[i]).compareTo(J[i]));
      }
    }
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
