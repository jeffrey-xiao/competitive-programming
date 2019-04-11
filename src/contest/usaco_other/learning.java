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
TASK: learning
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class learning {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int a, b;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("learning.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("learning.out")));

    int n = readInt();
    a = readInt();
    b = readInt();
    if (b - a < 10000000) {
      TreeSet<Cow> c = new TreeSet<Cow>();
      for (int x = 0; x < n; x++) {
        c.add(new Cow(next().equals("S"), readInt()));
      }
      int count = 0;
      for (int x = a; x <= b; x++) {
        Cow floor = c.floor(new Cow(true, x));
        Cow ceil = c.ceiling(new Cow(true, x));
        if (floor == null) {
          if (ceil.spot)
            count++;
        } else if (ceil == null) {
          if (floor.spot)
            count++;
        } else {
          int diffA = Math.abs(x - floor.weight);
          int diffB = Math.abs(x - ceil.weight);
          if (diffA > diffB) {
            if (ceil.spot)
              count++;
          } else if (diffB > diffA) {
            if (floor.spot)
              count++;
          } else if (floor.spot || ceil.spot)
            count++;
        }
      }
      pr.println(count);
    } else {
      ArrayList<Cow> c = new ArrayList<Cow>();
      for (int x = 0; x < n; x++) {
        c.add(new Cow(next().equals("S"), readInt()));
      }
      Collections.sort(c);
      for (int x = c.size() - 1; x > 0; x--) {
        if (c.get(x).weight == c.get(x - 1).weight) {
          if (!c.get(x).spot)
            c.remove(x);
          else if (!c.get(x - 1).spot)
            c.remove(x - 1);
          else if (c.get(x).spot && c.get(x - 1).spot)
            c.remove(x);
        }
      }
      Cow[] cows = c.toArray(new Cow[c.size()]);
      int count = 0;
      if (cows.length == 1 && cows[0].spot) {
        pr.println(b - a + 1);
        return;
      }
      for (int x = 0; x < cows.length - 1; x++) {
        if (cows[x].spot && !cows[x + 1].spot) {
          int mid = bal((cows[x + 1].weight + cows[x].weight) / 2);
          int lo = bal(cows[x].weight);
          if (mid == lo)
            continue;
          if (cows[x].weight < a && cows[x + 1].weight != a) {
            count++;
          }
          count += mid - lo;
        } else if (!cows[x].spot && cows[x + 1].spot) {
          int mid = bal((cows[x + 1].weight + cows[x].weight + 1) / 2);
          int hi = bal(cows[x + 1].weight);
          if (mid == hi)
            continue;
          if (cows[x + 1].weight > b && cows[x].weight != b) {
            count++;
          }
          count += hi - mid;
        } else if (cows[x].spot && cows[x + 1].spot) {
          int lo = bal(cows[x].weight);
          int hi = bal(cows[x + 1].weight);
          if (lo == hi)
            continue;
          count += hi - lo - 1;
          if (cows[x].weight < a)
            count++;
          if (cows[x + 1].weight > b)
            count++;
        }
      }
      for (int x = 0; x < cows.length; x++) {
        if (cows[x].spot && cows[x].weight >= a && cows[x].weight <= b)
          count++;
      }
      if (cows[0].weight >= a && cows[0].spot) {
        if (cows[0].weight > b)
          count++;
        count += bal(cows[0].weight) - a;
      }

      if (cows[cows.length - 1].weight <= b && cows[cows.length - 1].spot) {
        if (cows[cows.length - 1].weight < a)
          count++;
        count += b - bal(cows[cows.length - 1].weight);
      }
      pr.println(count);
    }
    pr.close();
    System.exit(0);
  }

  private static int bal(int x) {
    return Math.min(Math.max(a, x), b);
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

  static class Cow implements Comparable<Cow> {
    boolean spot;
    int weight;

    Cow(boolean spot, int weight) {
      this.spot = spot;
      this.weight = weight;
    }

    @Override
    public int compareTo(Cow o) {
      return weight - o.weight;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Cow) {
        Cow c = (Cow) o;
        return weight == c.weight;
      }
      return false;
    }
  }
}
