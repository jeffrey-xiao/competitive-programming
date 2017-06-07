package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Almost_Sorted {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int[] a = new int[n];
    int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = readInt();
      b[i] = a[i];
    }
    Arrays.sort(b);
    int index1 = -1;
    int index2 = -1;
    boolean swap = true;
    for (int i = 0; i < n; i++) {
      if (a[i] != b[i]) {
        if (index1 == -1) {
          index1 = i;
          continue;
        } else if (index2 == -1) {
          index2 = i;
          continue;
        }
        swap = false;
      }
    }
    if (index1 == -1) {
      System.out.println("yes");
      return;
    }
    if (swap && index1 != -1 && index2 != -1 && a[index1] == b[index2] && a[index2] == b[index1]) {
      System.out.printf("yes\nswap %d %d\n", index1 + 1, index2 + 1);
      return;
    }
    int i = 0;
    while (i < n - 1 && a[i] < a[i + 1]) {
      i++;
    }
    int j = i;
    while (j < n - 1 && a[j] > a[j + 1]) {
      j++;
    }
    boolean reverse = true;
    for (int k = j + 1; k < n - 1; k++) {
      if (a[k] > a[k + 1])
        reverse = false;
    }
    if (reverse && (i == 0 || a[i - 1] < a[j]) && (j == n - 1 || a[j + 1] > a[i])) {
      System.out.println("yes");
      System.out.printf("reverse %d %d\n", i + 1, j + 1);
    } else {
      System.out.println("no");
    }
    out.close();
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
