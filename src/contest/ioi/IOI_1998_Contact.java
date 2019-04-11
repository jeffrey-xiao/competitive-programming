package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class IOI_1998_Contact {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int len;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int a = readInt();
    int b = readInt();
    int n = readInt();
    String text = readLine();
    text = text.substring(0, text.length() - 1);
    len = text.length();

    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    for (int i = 0; i < len; i++) {
      for (int j = i + a; j < Math.min(text.length() + 1, i + b + 1); j++) {
        String add = text.substring(i, j);
        if (!hm.containsKey(add))
          hm.put(add, 0);
        hm.put(add, hm.get(add) + 1);

      }
    }
    ArrayList<State> list = new ArrayList<State>();
    for (Map.Entry<String, Integer> entry : hm.entrySet()) {
      list.add(new State(entry.getKey(), entry.getValue()));
    }
    Collections.sort(list);
    int prevOcc = 0;
    ArrayList<String> curr = new ArrayList<String>();
    int cnt = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).occ != prevOcc) {
        if (prevOcc != 0) {
          pr.print(prevOcc + " ");
          for (String s : curr)
            pr.print(s + " ");
          pr.println();
          cnt++;
          if (cnt >= n) {
            pr.close();
            return;
          }
        }
        prevOcc = list.get(i).occ;
        curr.clear();
      }
      curr.add(list.get(i).str);
    }
    pr.print(prevOcc + " ");
    for (String s : curr)
      pr.print(s + " ");
    pr.println();
    pr.close();
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

  static class State implements Comparable<State> {
    String str;
    int occ;

    State(String str, int occ) {
      this.str = str;
      this.occ = occ;
    }

    public int compareTo(State s) {
      if (occ == s.occ) {
        if (s.str.length() == str.length())
          return s.str.compareTo(str);
        return s.str.length() - str.length();
      }
      return s.occ - occ;
    }
  }
}