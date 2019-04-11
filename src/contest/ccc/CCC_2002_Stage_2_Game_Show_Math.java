package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Game_Show_Math {

  static final int K = 44000;
  static final int S = 12000;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int n = readInt();
      boolean[][] poss = new boolean[2][K];
      short[][] prev = new short[n][K];
      byte[][] op = new byte[n][K];
      int[] nums = new int[n];
      for (int x = 0; x < n; x++)
        nums[x] = readInt();
      int target = readInt();
      poss[0][S + nums[0]] = true;
      prev[0][S + nums[0]] = -1;
      op[0][S + nums[0]] = -1;
      int next = 0;
      for (int x = 1; x < n; x++) {
        for (int y = 0; y < K; y++)
          poss[x % 2][y] = false;
        for (int y = 0; y < K; y++) {
          if (poss[(x - 1) % 2][y]) {
            next = (y - S) + nums[x] + S;
            if (next >= 0 && next < K) {
              poss[x % 2][next] = true;
              prev[x][next] = (short)(y - S);
              op[x][next] = 1;
            }
            next = (y - S) - nums[x] + S;
            if (next >= 0 && next < K) {
              poss[x % 2][next] = true;
              prev[x][next] = (short)(y - S);
              op[x][next] = 2;
            }
            next = (y - S) * nums[x] + S;
            if (next >= 0 && next < K) {
              poss[x % 2][next] = true;
              prev[x][next] = (short)(y - S);
              op[x][next] = 3;
            }
            next = (y - S) / nums[x] + S;
            if (next >= 0 && next < K && (y - S) % nums[x] == 0) {
              poss[x % 2][next] = true;
              prev[x][next] = (short)(y - S);
              op[x][next] = 4;
            }
          }
        }
      }
      if (!poss[(n - 1) % 2][target + S]) {
        System.out.println("NO EXPRESSION");
      } else {
        Stack<String> s = new Stack<String>();
        int curr = target + S;
        int i = n - 1;
        while (curr != -1 && i != -1) {
          s.add(nums[i] + "");
          if (op[i][curr] != ' ')
            s.add(getString(op[i][curr]));
          curr = prev[i--][curr] + S;
        }
        for (int x = s.size(); x > 0; x--)
          System.out.print(s.pop());
        System.out.println("=" + target);
      }
    }
  }

  private static String getString(byte b) {
    switch (b) {
      case 1:
        return "+";
      case 2:
        return "-";
      case 3:
        return "*";
      case 4:
        return "/";
    }
    return "";
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}