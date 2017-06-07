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
TASK: cowjog_silver
 */
import java.util.StringTokenizer;

public class cowjog_silver {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("cowjog.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));

    int n = readInt();
    int t = readInt();

    Cow[] c = new Cow[n];

    for (int x = n - 1; x >= 0; x--)
      c[x] = new Cow(readDouble(), readDouble());

    int count = 0;
    for (int x = 0; x < n;) {
      int y;
      for (y = x + 1; y < n; y++) {
        double timeToCatch = (c[x].pos - c[y].pos) / (c[y].speed - c[x].speed);
        if (c[y].speed <= c[x].speed || timeToCatch > t)
          break;
      }
      x = y;
      count++;
    }
    pr.println(count);

    pr.close();
    System.exit(0);
  }

  static class Cow {
    double pos, speed;

    Cow (double pos, double speed) {
      this.pos = pos;
      this.speed = speed;
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
