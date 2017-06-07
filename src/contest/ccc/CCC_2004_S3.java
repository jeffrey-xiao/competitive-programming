package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2004_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int[][] values = new int[10][9];
    String[][] mod = new String[10][9];
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 9; y++) {
        values[x][y] = 0;
        mod[x][y] = "";
        String next = next();
        if (isNumeric(next))
          values[x][y] = Integer.parseInt(next);
        else {
          StringTokenizer st = new StringTokenizer(next, "+");
          while (st.hasMoreTokens()) {
            String token = st.nextToken();

            int[] index = getIndex(token);
            if (index[0] > x || index[1] > y || mod[index[0]][index[1]].length() > 0) {
              mod[x][y] += index[0] + "" + index[1] + " ";
            } else {
              values[x][y] += values[index[0]][index[1]];
            }

          }
        }
      }
    }
    boolean change = false;
    do {
      change = false;
      for (int x = 0; x < 10; x++) {
        for (int y = 0; y < 9; y++) {

          StringTokenizer st = new StringTokenizer(mod[x][y], " ");
          StringBuilder sb = new StringBuilder(mod[x][y]);
          int count = -3;
          while (st.hasMoreTokens()) {
            count += 3;
            String n = st.nextToken();
            int x1 = n.charAt(0) - 48;
            int y1 = n.charAt(1) - 48;

            if (mod[x1][y1].length() == 0) {
              values[x][y] += values[x1][y1];
              sb.delete(count, count + 3);
              change = true;
              count -= 3;
            }
          }
          mod[x][y] = sb.toString();
        }
      }

    } while (change);
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 9; y++) {
        if (mod[x][y].length() == 0)
          System.out.print(values[x][y] + " ");
        else
          System.out.print("* ");
      }
      System.out.println();
    }
  }

  static int[] getIndex (String s) {
    int[] coordinate = new int[2];
    coordinate[0] = s.charAt(0) - 65;
    coordinate[1] = s.charAt(1) - 49;
    return coordinate;
  }

  static boolean isNumeric (String str) {
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c))
        return false;
    }
    return true;
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
