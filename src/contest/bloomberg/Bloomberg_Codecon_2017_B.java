package contest.bloomberg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bloomberg_Codecon_2017_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static Card[][] c;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    c = new Card[N][5];
    String[] input = new String[17];
    for (int i = 0; i < 17; i++)
      input[i] = next();
    ArrayList<Integer> winners = new ArrayList<Integer>();
    int score = -1 << 30;
    for (int i = 0; i < N; i++) {
      int jokerIndex = -1;
      for (int j = 0; j < 5; j++) {
        String next = input[j * N + i];
        if (next.equals("Joker")) {
          jokerIndex = j;
        } else {
          int value = getValue(next.charAt(0));
          int suit = getSuit(next.charAt(1));
          c[i][j] = new Card(value, suit);
        }
      }
      int max = 0;
      if (jokerIndex != -1) {
        for (int value = 11; value <= 14; value++) {
          for (int suit = 0; suit <= 3; suit++) {
            c[i][jokerIndex] = new Card(value, suit);
            max = Math.max(max, getPoints(c[i]));
          }
        }
      } else {
        max = getPoints(c[i]);
      }

      if (max > score) {
        score = max;
        winners.clear();
        winners.add(i);
      } else if (max == score) {
        winners.add(i);
      }
    }
    if (winners.size() > 1)
      out.printf("tie %d%n", score);
    else
      out.printf("%d %d%n", winners.get(0) + 1, score);
    out.close();
  }

  static int getPoints(Card[] c) {
    int[] occ = new int[4];
    int[] occValue = new int[15];
    int maxOcc = 0;
    int maxValue = 0;
    for (int i = 0; i < 5; i++) {
      occValue[c[i].value]++;
      maxOcc = Math.max(maxOcc, ++occ[c[i].suit]);
    }
    for (int i = 11; i <= 14; i++)
      maxValue += i * occValue[i] * occValue[i];
    return maxValue * maxOcc;
  }

  static int getSuit(char c) {
    switch (c) {
      case 'h':
        return 0;
      case 'd':
        return 1;
      case 's':
        return 2;
      case 'c':
        return 3;
    }
    return -1;
  }

  static int getValue(char c) {
    switch (c) {
      case 'J':
        return 11;
      case 'Q':
        return 12;
      case 'K':
        return 13;
      case 'A':
        return 14;
    }
    return -1;
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Card {
    int value, suit;

    Card(int value, int suit) {
      this.value = value;
      this.suit = suit;
    }
  }
}
