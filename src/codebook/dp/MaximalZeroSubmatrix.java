/*
 * Given a rectangular matrix of 0's and 1's, determine the area of the largest submatrix which only contains 0's
 * 
 * Time complexity: O(rows * cols)
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class MaximalZeroSubmatrix {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();

    int[][] a = new int[n][m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        a[i][j] = readInt();

    out.println(getMaxZeroSubmatrix(a));
    out.println(bruteForce(a));
    out.close();
  }

  static int getMaxZeroSubmatrix (int[][] a) {
    int rows = a.length;
    int cols = a[0].length;
    int[][] height = new int[rows][cols];
    Stack<Integer> s = new Stack<Integer>();
    int ret = 0;
    for (int j = 0; j < cols; j++) {
      for (int i = rows - 1; i >= 0; i--) {
        if (a[i][j] == 1)
          height[i][j] = 0;
        else
          height[i][j] = 1 + (i == rows - 1 ? 0 : height[i + 1][j]);
      }
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        int minIndex = j;
        while (!s.isEmpty() && height[i][s.peek()] >= height[i][j]) {
          ret = Math.max(ret, (j - s.peek()) * (height[i][s.peek()]));
          minIndex = s.peek();
          height[i][minIndex] = height[i][j];
          s.pop();
        }
        s.push(minIndex);
      }
      while (!s.isEmpty()) {
        ret = Math.max(ret, (cols - s.peek()) * height[i][s.peek()]);
        s.pop();
      }
    }
    return ret;
  }

  static int bruteForce (int[][] a) {
    int rows = a.length;
    int cols = a[0].length;
    int res = 0;

    int[][] sum = new int[rows + 1][cols + 1];
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        sum[i][j] = a[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
      }
    }

    for (int i = 0; i <= rows; i++) {
      for (int j = 0; j < i; j++) {
        for (int k = 0; k <= cols; k++) {
          for (int l = 0; l < k; l++) {
            if (sum[i][k] - sum[j][k] - sum[i][l] + sum[j][l] == 0)
              res = Math.max(res, (i - j) * (k - l));
          }
        }
      }
    }

    return res;
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
