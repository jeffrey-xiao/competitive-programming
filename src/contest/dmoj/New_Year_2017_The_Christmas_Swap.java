package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class New_Year_2017_The_Christmas_Swap {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K, left, l;
  static char[] in;
  static ArrayList<ArrayList<Integer>> pos = new ArrayList<ArrayList<Integer>>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    in = readLine().toCharArray();

    pos.add(new ArrayList<Integer>());
    pos.add(new ArrayList<Integer>());

    for (int i = 0; i < N; i++) {
      if (in[i] == '0') {
        pos.get(0).add(i);
      } else {
        pos.get(1).add(i);
      }
    }

    out.printf("%d %d\n", compute(1), compute(0));
    out.close();
  }

  static int compute (int index) {
    int lo = 1, hi = count(in, (char)(index + '0'));

    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      // [l, left] are the indices of the positions in pos.get(index) that are left of their spots
      l = 0;
      left = -1;

      // computing initial state
      int bestAns = 0;
      int prevAns = 0;
      for (int i = 0; i < mid; i++) {
        prevAns += Math.abs(i - pos.get(index).get(l + i));
        if (pos.get(index).get(l + i) < i)
          left = i;
      }
      bestAns = prevAns;

      for (int i = 1; i + mid - 1 < N; i++) {
        // computing best for interval [i, i + mid - 1]

        // not changing the positions used
        int nextAns = computeNextAnswer(prevAns, i, mid, index, false, -1);

        // incrementing the positions used to the right
        if (l + mid < pos.get(index).size()) {
          l++;
          int lastLeft = left;
          int possAns = computeNextAnswer(nextAns, i, mid, index, true, lastLeft);
          int diff = Math.abs(i + mid - 1 - pos.get(index).get(l + mid - 1)) - Math.abs(i - pos.get(index).get(l - 1));
          possAns += diff;

          // reverting if answer is not better or equal
          if (possAns > nextAns) {
            l--;
            left = lastLeft;
          } else {
            nextAns = possAns;
          }
        }

        bestAns = Math.min(bestAns, nextAns);
        prevAns = nextAns;
        if (bestAns <= K)
          break;
      }

      if (bestAns <= K) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return hi;
  }

  static int computeNextAnswer (int prevAns, int i, int mid, int index, boolean inverse, int lastLeft) {
    int nextAns = prevAns;
    left = Math.max(left, l - 1);

    // adjusting left
    while (left + 1 <= l + mid - 1 && pos.get(index).get(left + 1) < i + left + 1 - l)
      left++;
    while (left >= l && pos.get(index).get(left) >= i + left - l)
      left--;

    if (inverse) {
      int benefit = (lastLeft - (l - 1) + 1) - 1;
      int negative = (mid - benefit - 1);
      nextAns -= benefit;
      nextAns += negative;
    } else {
      nextAns += left - l + 1 - (l + mid - 1 - left);
    }

    return nextAns;
  }

  static int count (char[] line, char c) {
    int ans = 0;
    for (int i = 0; i < line.length; i++)
      if (line[i] == c)
        ans++;
    return ans;
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
