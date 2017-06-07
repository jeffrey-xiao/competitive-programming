package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IOI_2000_Car_Parking {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m, w;
  static int[] car;
  static int[] sorted;
  static ArrayList<ArrayDeque<Match>> matches = new ArrayList<ArrayDeque<Match>>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    w = readInt();

    car = new int[n];

    for (int i = 0; i < n; i++)
      car[i] = readInt() - 1;

    for (int i = 0; i < m; i++)
      matches.add(new ArrayDeque<Match>());

    sorted = Arrays.copyOf(car, n);
    Arrays.sort(sorted);

    int unmatched = 0;
    for (int i = 0; i < n; i++)
      if (car[i] != sorted[i]) {
        matches.get(sorted[i]).addFirst(new Match(i, sorted[i], car[i]));
        unmatched++;
      }
    StringBuilder ans = new StringBuilder();
    int iterations = 0;
    while (unmatched > 0) {
      iterations++;
      int workersLeft = w;
      StringBuilder res = new StringBuilder();
      int prevVal = 0;
      for (int i = 0; i < m && workersLeft > 1; i++) {
        while (!matches.get(i).isEmpty() && workersLeft > 1) {
          int cnt = 0;

          Match curr = matches.get(i).removeFirst();
          prevVal = car[curr.pos];
          unmatched--;
          int startPos = curr.pos;
          int startRegion = i;
          boolean finished = false;

          while (cnt < workersLeft - 1) {

            Match next = matches.get(curr.to).removeFirst();
            unmatched--;

            res.append(String.format("%d %d ", curr.pos + 1, next.pos + 1));
            int temp = prevVal;
            prevVal = car[next.pos];
            car[next.pos] = temp;

            curr = next;
            cnt++;

            if (next.to == startRegion) {
              finished = true;
              break;
            }
          }
          res.append(String.format("%d %d ", curr.pos + 1, startPos + 1));
          int temp = prevVal;
          prevVal = car[startPos];
          car[startPos] = temp;
          if (!finished) {
            unmatched++;
            matches.get(sorted[startPos]).add(new Match(startPos, sorted[startPos], car[startPos]));
          }

          workersLeft -= cnt + 1;
        }
      }
      ans.append(String.format("%d %s\n", w - workersLeft, res));
      out.flush();
    }
    out.println(iterations);
    out.println(ans);
    out.close();
  }

  static void swap (int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  static class Match {
    int pos, from, to;

    Match (int pos, int from, int to) {
      this.pos = pos;
      this.from = from;
      this.to = to;
    }
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
