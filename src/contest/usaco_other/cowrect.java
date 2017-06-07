package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: cowrect
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class cowrect {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("cowrect.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("cowrect.out")));

    int n = readInt();
    int[][] H = new int[1010][1010];
    int[][] G = new int[1010][1010];
    ArrayList<Cow> hs = new ArrayList<Cow>();
    for (int x = 0; x < n; x++) {
      int a = readInt() + 1;
      int b = readInt() + 1;
      boolean isH = next().equals("H");
      hs.add(new Cow(a, b, isH ? 1 : 0));
      if (isH) {
        H[a][b]++;
      } else
        G[a][b]++;

    }
    for (int x = 1; x <= 1001; x++) {
      for (int y = 1; y <= 1001; y++) {
        H[x][y] += H[x - 1][y] + H[x][y - 1] - H[x - 1][y - 1];
        G[x][y] += G[x - 1][y] + G[x][y - 1] - G[x - 1][y - 1];
      }
    }

    int max = 0;
    int area = Integer.MAX_VALUE;
    Collections.sort(hs);
    ArrayList<Cow> process = new ArrayList<Cow>();
    int currX = 0;
    for (int x = 0; x < hs.size(); x++) {
      int upperBound = Integer.MAX_VALUE;
      int lowerBound = Integer.MIN_VALUE;
      TreeSet<Integer> bounds = new TreeSet<Integer>();
      bounds.add(hs.get(x).y);
      if (hs.get(x).x != currX)
        process = new ArrayList<Cow>();
      if (hs.get(x).h == 0) {
        currX = hs.get(x).x;
        process.add(hs.get(x));
        continue;
      }
      for (int y = 0; y < process.size(); y++) {
        if (process.get(y).y == hs.get(x).y)
          break;
        else if (process.get(y).y < hs.get(x).y)
          lowerBound = Math.max(lowerBound, process.get(y).y);
        else
          upperBound = Math.min(upperBound, process.get(y).y);
      }
      for (int y = x + 1; y < hs.size(); y++) {
        if (hs.get(y).h == 0) {
          if (hs.get(y).y == hs.get(x).y)
            break;
          else if (hs.get(y).y < hs.get(x).y)
            lowerBound = Math.max(lowerBound, hs.get(y).y);
          else
            upperBound = Math.min(upperBound, hs.get(y).y);
        } else {
          bounds.add(hs.get(y).y);
          Integer ubound = bounds.lower(upperBound);
          Integer lbound = bounds.higher(lowerBound);
          if (lbound == null || ubound == null || lbound > ubound)
            continue;
          int a = (ubound - lbound) * (hs.get(y).x - hs.get(x).x);
          int x2 = hs.get(y).x;
          int y2 = ubound;
          int x1 = hs.get(x).x;
          int y1 = lbound;
          int sum = H[x2][y2] - H[x1 - 1][y2] - H[x2][y1 - 1] + H[x1 - 1][y1 - 1];
          if (sum > max || (sum == max && a < area)) {
            area = a;
            max = sum;
          }
        }
      }
    }
    if (max == 0)
      pr.println(1 + "\n" + 0);
    else
      pr.println(max + "\n" + area);
    pr.close();
    System.exit(0);
  }

  static class Cow implements Comparable<Cow> {
    int x, y;
    int h;

    Cow (int x, int y, int h) {
      this.x = x;
      this.y = y;
      this.h = h;
    }

    @Override
    public int compareTo (Cow o) {
      if (x == o.x)
        return h - o.h;
      return x - o.x;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
