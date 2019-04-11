package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IOI_1995_Shopping_Offers {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] prices;
  static HashMap<Integer, Integer> toIndex = new HashMap<Integer, Integer>();
  static HashMap<State, Integer> dp = new HashMap<State, Integer>();
  static Offer[] offers;
  static int s;
  static int b;

  public static void main(String[] args) throws IOException {
    s = readInt();
    offers = new Offer[s];
    for (int x = 0; x < s; x++) {
      int n = readInt();
      offers[x] = new Offer();
      offers[x].items = new ArrayList<Integer>();
      offers[x].num = new ArrayList<Integer>();
      for (int y = 0; y < n; y++) {
        offers[x].items.add(readInt());
        offers[x].num.add(readInt());
      }
      offers[x].price = readInt();
    }
    b = readInt();
    prices = new int[b];
    State start = new State(b);
    for (int x = 0; x < b; x++) {
      int id = readInt();
      toIndex.put(id, x);
      start.items[x] = readInt();
      prices[x] = readInt();
    }
    System.out.println(compute(start));
  }

  private static int compute(State start) {
    if (dp.get(start) != null)
      return dp.get(start);

    int next = Integer.MAX_VALUE;
    boolean end = true;

  main:
    for (int x = 0; x < s; x++) {
      State nextState = new State(b);
      nextState.items = Arrays.copyOf(start.items, start.items.length);
      for (int y = 0; y < offers[x].items.size(); y++) {
        int offerNum = offers[x].num.get(y);
        int i = toIndex.get(offers[x].items.get(y));
        int currNum = start.items[i];
        if (offerNum > currNum)
          continue main;
        nextState.items[i] = currNum - offerNum;
      }
      end = false;
      next = Math.min(next, offers[x].price + compute(nextState));
    }
    if (end) {
      next = 0;
      for (int x = 0; x < b; x++)
        next += start.items[x] * prices[x];
    }
    dp.put(start, next);
    return next;
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

  static class State {
    int[] items;

    State(int n) {
      items = new int[n];
    }

    @Override
    public int hashCode() {
      int s = 0;
      int curr = 1;
      for (int x = 0; x < items.length; x++) {
        s += items[x] * curr;
        curr *= 31;
      }
      return s;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof State) {
        State s = (State)o;
        for (int x = 0; x < items.length; x++)
          if (s.items[x] != items[x])
            return false;
        return true;
      }
      return false;
    }
  }

  static class Offer {
    ArrayList<Integer> items;
    ArrayList<Integer> num;
    int price;
  }
}
