package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2004_Stage_2_Scribble {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int numOfLetters = readInt();
    int[] letters = new int[26];
    int[] values = new int[26];
    for (int x = 0; x < numOfLetters; x++) {
      int index = next().charAt(0) - 97;
      values[index] = readInt();
      letters[index] = readInt();
    }
    int numOfWords = readInt();
    int max = 0;
    String word = "";
    main : for (int x = 0; x < numOfWords && (word = br.readLine()) != null; x++) {
      int points = 0;
      for (int y = 97; y <= 122; y++) {
        int count = 0;
        String temp = word;
        count = word.length() - temp.replace("" + (char)y, "").length();
        if (letters[y - 97] >= count)
          points += values[y - 97] * count;
        else
          continue main;
      }
      if (points > max)
        max = points;
    }
    System.out.println(max);
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
