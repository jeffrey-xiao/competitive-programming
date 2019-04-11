package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Waterloo_Local_2017_Fall_B {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Card[] hand = new Card[5];
  static Card c1, c2;
  static int A, B;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    A = readInt();
    B = readInt();
    int a1 = readInt();
    int b1 = readInt();
    int a2 = readInt();
    int b2 = readInt();

    c1 = new Card(a1, b1);
    c2 = new Card(a2, b2);


    hand[0] = c1;
    hand[1] = c2;

    ArrayList<Card> cards = new ArrayList<Card>();

    for (int i = 0; i < A; i++) {
      for (int j = 0; j < B; j++) {
        Card c = new Card(i, j);
        if (c.equals(c1))
          continue;
        if (c.equals(c2))
          continue;
        cards.add(c);
      }
    }
    int[] ret = new int[9];

    for (int i = 0; i < cards.size(); i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        for (int k = j + 1; k < cards.size(); k++) {
          hand[0] = c1;
          hand[1] = c2;
          hand[2] = cards.get(i);
          hand[3] = cards.get(j);
          hand[4] = cards.get(k);

          Arrays.sort(hand);
          if (isStraightFlush()) ret[0]++;
          else if (isQuad()) ret[1]++;
          else if (isFull()) ret[2]++;
          else if (isFlush()) ret[3]++;
          else if (isStraight()) ret[4]++;
          else if (isTriple()) ret[5]++;
          else if (isTwoPair()) ret[6]++;
          else if (isPair()) ret[7]++;
          else ret[8]++;
        }
      }
    }

    for (int i = 0; i < 9; i++)
      out.print(ret[i] + (i < 8 ? " " : ""));
    out.println();
    out.close();
  }

  static boolean isStraightFlush() {
    return isFlush() && isStraight();
  }

  static boolean isQuad() {
    int[] occ = new int[A];
    for (int i = 0; i < 5; i++) {
      occ[hand[i].rank]++;
    }
    for (int i = 0; i < A; i++)
      if (occ[i] == 4)
        return true;
    return false;
  }

  static boolean isFull() {
    int[] occ = new int[A];
    for (int i = 0; i < 5; i++) {
      occ[hand[i].rank]++;
    }
    boolean hasThree = false;
    boolean hasTwo = false;
    for (int i = 0; i < A; i++) {
      if (occ[i] == 3)
        hasThree = true;
      if (occ[i] == 2)
        hasTwo = true;
    }
    return hasThree && hasTwo;
  }

  static boolean isFlush() {
    for (int i = 0; i < 4; i++)
      if (hand[i + 1].suit != hand[i].suit)
        return false;
    return true;
  }

  static boolean isStraight() {
    for (int i = 0; i < 4; i++)
      if (hand[i + 1].rank - 1 != hand[i].rank)
        return false;
    return true;
  }

  static boolean isTriple() {
    int[] occ = new int[A];
    for (int i = 0; i < 5; i++) {
      occ[hand[i].rank]++;
    }
    for (int i = 0; i < A; i++)
      if (occ[i] == 3)
        return true;
    return false;
  }

  static boolean isTwoPair() {
    int[] occ = new int[A];
    for (int i = 0; i < 5; i++) {
      occ[hand[i].rank]++;
    }
    int ret = 0;
    for (int i = 0; i < A; i++)
      if (occ[i] == 2)
        ret++;
    return ret == 2;
  }

  static boolean isPair() {
    int[] occ = new int[A];
    for (int i = 0; i < 5; i++) {
      occ[hand[i].rank]++;
    }
    for (int i = 0; i < A; i++)
      if (occ[i] == 2)
        return true;
    return false;
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

  static class Card implements Comparable<Card> {
    int rank, suit;

    Card(int rank, int suit) {
      this.rank = rank;
      this.suit = suit;
    }

    public int compareTo(Card c) {
      return rank - c.rank;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Card) {
        Card c = (Card) o;
        return rank == c.rank && suit == c.suit;
      }
      return false;
    }
  }
}
