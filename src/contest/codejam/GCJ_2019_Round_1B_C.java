package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1B_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, K;
  static int[] C, D;
  static long ans;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      K = readInt();
      ans = 0;

      C = new int[N];
      D = new int[N];

      for (int i = 0; i < N; i++) {
        C[i] = readInt();
      }

      for (int i = 0; i < N; i++) {
        D[i] = readInt();
      }

      solve(0, N - 1);
      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static void solve(int l, int r) {
    if (l == r) {
      if (Math.abs(C[l] - D[l]) <= K) {
        ans++;
      }
      return;
    }

    int mid = (l + r) >> 1;
    solve(l, mid);
    solve(mid + 1, r);

    int leftSize = mid - l + 1;
    int rightSize = r - mid;
    int[] leftC = new int[leftSize], leftD = new int[leftSize];
    leftC[0] = C[mid];
    leftD[0] = D[mid];
    for (int i = mid - 1, j = 1; i >= l; i--, j++) {
      leftC[j] = Math.max(leftC[j - 1], C[i]);
      leftD[j] = Math.max(leftD[j - 1], D[i]);
    }

    int[] rightC = new int[rightSize], rightD = new int[rightSize];
    rightC[0] = C[mid + 1];
    rightD[0] = D[mid + 1];
    for (int i = mid + 2, j = 1; i <= r; i++, j++) {
      rightC[j] = Math.max(rightC[j - 1], C[i]);
      rightD[j] = Math.max(rightD[j - 1], D[i]);
    }

    // Left side
    for (int i = 0; i < leftSize; i++) {
      int x = bsearchFloor(rightC, 0, rightSize - 1, leftC[i]);
      int y = bsearchFloor(rightD, 0, rightSize - 1, leftD[i]);
      if (Math.abs(leftC[i] - leftD[i]) <= K) {
        ans += Math.min(x, y) + 1;
      }

      if (x < y) {
        ans += Math.max(bsearchFloor(rightC, x + 1, y, leftD[i] + K) - bsearchCeil(rightC, x + 1, y, leftD[i] - K) + 1, 0);
      } else if (x > y) {
        ans += Math.max(bsearchFloor(rightD, y + 1, x, leftC[i] + K) - bsearchCeil(rightD, y + 1, x, leftC[i] - K) + 1, 0);
      }
    }

    // Right size
    for (int i = 0; i < rightSize; i++) {
      int x = bsearchFloor(leftC, 0, leftSize - 1, rightC[i] - 1);
      int y = bsearchFloor(leftD, 0, leftSize - 1, rightD[i] - 1);
      if (Math.abs(rightC[i] - rightD[i]) <= K) {
        ans += Math.min(x, y) + 1;
      }
    }
  }

  public static int bsearchCeil(int[] a, int lo, int hi, int val) {
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >> 1);
      if (a[mid] < val)
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return lo;
  }

  public static int bsearchFloor(int[] a, int lo, int hi, int val) {
    while (lo <= hi) {
      int mid = lo + ((hi - lo) >> 1);
      if (a[mid] <= val)
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return hi;
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
