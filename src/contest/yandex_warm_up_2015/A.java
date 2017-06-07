package contest.yandex_warm_up_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    int k = readInt();
    boolean[][] adj = new boolean[1000][1000];
    int[] counter = new int[n];
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      while (counter[i] < k) {
        int min = 1 << 30;
        int index = 0;
        for (int j = 0; j < n; j++) {
          if (i != j && !adj[i][j]) {
            if (counter[j] < min) {
              min = counter[j];
              index = j;
            }
          }
        }
        adj[i][index] = true;
        adj[index][i] = true;
        counter[index]++;
        counter[i]++;
        cnt++;
      }

    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n && cnt < m; j++) {
        if (i == j)
          continue;
        if (!adj[i][j]) {
          adj[i][j] = true;
          adj[j][i] = true;
          counter[i]++;
          counter[j]++;
          cnt++;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (adj[i][j])
          pr.println(i + 1 + " " + (j + 1));
      }
    }
    pr.close();
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
